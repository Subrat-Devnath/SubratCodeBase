package com.workflow.service.impl;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.workflow.service.WorkFlowService;

@Service
public class WorkflowServiceImpl implements WorkFlowService {

	@Value("${workflow.url}")
	private String url;

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public void printMessage(String processDefinationKey) {
		System.out.println("WORKFLOW URL: " + url);
		runtimeService.startProcessInstanceByKey(processDefinationKey);
	}

}
