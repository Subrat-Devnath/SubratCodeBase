package com.kafka.utils;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("producerUtils")
public class ProducerUtils {

	Logger logger = LoggerFactory.getLogger(ProducerUtils.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaProducer;

	@Autowired
	private AdminClient adminClient;

	@Autowired
	public ObjectMapper objectMapper;

	private String dlqExtention = ".dlq";

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootStrapServer;

	public void produceMessages(Message message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = objectMapper.writeValueAsString(message.getKafkaData());
		logger.info("Topic name for process: {}", message.getTopicName());
		ListenableFuture<SendResult<String, String>> result = kafkaProducer.send(message.getTopicName(), jsonData);
		logger.info("Number of Dependents", result.get());
	}

	public void processDlqTopics() throws Exception {
		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(getConsumerProperties());
		ListTopicsResult listTopics = adminClient.listTopics();
		KafkaFuture<Set<String>> topicNames = listTopics.names();
		Set<String> topicSet = topicNames.get();
		topicSet = topicSet.stream().filter(topic -> topic.contains(".dlq")).collect(Collectors.toSet());
		logger.info("DLQ topics for process: {}", topicSet);
		for (String topic : topicSet) {
			kafkaConsumer.subscribe(Arrays.asList(topic));
			ConsumerRecords<String, String> records = kafkaConsumer.poll(5000);

			records.forEach(record -> {
				String topicName = record.topic();
				if (topicName.contains(dlqExtention)) {
					try {
						KafkaData readValue = objectMapper.readValue(record.value(), KafkaData.class);
						readValue.setRetryCount(readValue.getRetryCount() + 1);
						String jsonData = objectMapper.writeValueAsString(readValue);
						kafkaProducer.send(StringUtils.delete(topicName, dlqExtention), jsonData);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}
	}

	public Properties getConsumerProperties() {
		java.util.Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "DLQ-Consumer");
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 1200000);
		properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 5000);
		properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1800000);
		properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 20);
		properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 1260000);
		properties.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, 50 * 1024 * 1024);
		return properties;
	}

}
