package com.kafka.utils;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 8411752016258284622L;

	private String topicName;
	private KafkaData kafkaData;

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public KafkaData getKafkaData() {
		return kafkaData;
	}

	public void setKafkaData(KafkaData kafkaData) {
		this.kafkaData = kafkaData;
	}

	@Override
	public String toString() {
		return "Message [topicName=" + topicName + ", kafkaData=" + kafkaData + "]";
	}

}