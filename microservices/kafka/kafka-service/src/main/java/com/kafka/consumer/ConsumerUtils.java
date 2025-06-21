package com.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ConsumerUtils implements LocalKafkaConsumer {

	Logger logger = LoggerFactory.getLogger(ConsumerUtils.class);

	@Autowired
	public ObjectMapper objectMapper;

	private String dlqExtention = ".dlq";

	@Autowired
	private KafkaTemplate<String, String> kafkaProducer;

	public abstract void consumerBussinessLogic(ConsumerRecord<String, String> record) throws Exception;

	public void consume(ConsumerRecord<String, String> record) throws Exception {
		try {
			consumerBussinessLogic(record);
		} catch (Exception e) {
			logger.info("Sending data to dlq topic: {}", record.topic() + dlqExtention);
			kafkaProducer.send(record.topic() + dlqExtention, record.value());
		} finally {
			logger.info("Finally block executed for topic: {}", record.topic());
		}
	}

	public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) throws Exception {
		try {
			consumerBussinessLogic(record);
		} catch (Exception e) {
			kafkaProducer.send(record.topic() + dlqExtention, record.value());
		} finally {
			logger.info("Acknowledging topic: {}", record.topic());
			ack.acknowledge();
			logger.info("Acknowledged topic: {}", record.topic());
		}
	}

}