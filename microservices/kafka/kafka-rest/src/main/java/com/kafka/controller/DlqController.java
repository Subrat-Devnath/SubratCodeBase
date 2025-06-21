package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.service.KafkaProducerService;

@RestController
public class DlqController {

	@Autowired
	private KafkaProducerService kafkaProduce;

	// @Scheduled(fixedDelay = 60000)
	@GetMapping(value = "/produce/dlq/message")
	public void produceDlqMessage() throws Exception {
		kafkaProduce.produceDlqMessage();
	}

}
