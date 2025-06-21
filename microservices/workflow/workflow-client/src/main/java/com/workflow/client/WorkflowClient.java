package com.workflow.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "workflowClient", name = "workflow", path = "${workflow.contextPath:/workflow}", url = "${workflow.url:http://localhost:8083}")
public interface WorkflowClient {

	@GetMapping(path = "/api-v1/print-hello-world/{processDefinationKey}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void printHellowWorld(@PathVariable(name = "processDefinationKey") String processDefinationKey);

}
