package com.kafka.utils;

import java.io.Serializable;

public class KafkaData implements Serializable {

	private static final long serialVersionUID = 5837520713172398277L;

	private String api;
	private int retryCount = 0;
	private Object data;

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "KafkaData [api=" + api + ", retryCount=" + retryCount + ", data=" + data + "]";
	}

}