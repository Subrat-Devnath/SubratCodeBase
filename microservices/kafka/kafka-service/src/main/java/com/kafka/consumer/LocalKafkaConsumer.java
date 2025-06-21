package com.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface LocalKafkaConsumer {

	public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) throws Exception;

}