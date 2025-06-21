package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.service.KafkaProducerService;
import com.kafka.utils.Message;

@RestController
@RequestMapping(path = "/api/v1", consumes = "application/json", produces = "application/json")
public class KafkaProducerController {

	@Autowired
	private KafkaProducerService kafkaProduce;

	@PostMapping(value = "/produce/message")
	public boolean produceMessage(@RequestBody Message message) throws Exception {
		kafkaProduce.produceMessages(message);
		return true;
	}

}
