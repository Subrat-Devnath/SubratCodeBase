package com.kafka.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kafka.consumer.ConsumerUtils;
import com.kafka.utils.KafkaData;

@Component
public class KafkaConsumerServiceImpl extends ConsumerUtils {

	Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

	@Override
	public void consumerBussinessLogic(ConsumerRecord<String, String> record) throws Exception {
		KafkaData readValue = objectMapper.readValue(record.value(), KafkaData.class);
		logger.info("Value consumed by kafka : {}", readValue);
	}

}