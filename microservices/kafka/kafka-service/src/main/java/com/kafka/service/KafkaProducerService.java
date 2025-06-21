package com.kafka.service;

import com.kafka.utils.Message;

public interface KafkaProducerService {

	public void produceMessages(Message message) throws Exception;

	public void produceDlqMessage() throws Exception;

}
