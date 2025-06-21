package com.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.service.WorkFlowService;

@RestController
@RequestMapping(path = "/api-v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkflowController {

	@Autowired
	private WorkFlowService workFlowService;

	@GetMapping(path = "/print-hello-world/{processDefinationKey}")
	public void printHellowWorld(@PathVariable("processDefinationKey") String processDefinationKey) {
		workFlowService.printMessage(processDefinationKey);
	}
}
