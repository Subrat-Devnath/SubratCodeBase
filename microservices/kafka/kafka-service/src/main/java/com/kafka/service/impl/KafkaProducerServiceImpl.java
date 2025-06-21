package com.kafka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.service.KafkaProducerService;
import com.kafka.utils.Message;
import com.kafka.utils.ProducerUtils;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

	Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

	@Autowired
	private ProducerUtils producerUtils;

	@Override
	public void produceMessages(Message message) throws Exception {
		producerUtils.produceMessages(message);
	}

	@Override
	public void produceDlqMessage() throws Exception {
		producerUtils.processDlqTopics();
	}

}