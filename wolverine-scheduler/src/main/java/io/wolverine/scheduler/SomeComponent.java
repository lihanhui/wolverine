package io.wolverine.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import io.distributed.unicorn.common.discovery.ServiceDiscoveryClient;
import io.wolverine.scheduler.controller.WolverineJobController;

@Component
public class SomeComponent {
	@Autowired
	private ServiceDiscoveryClient client;
	@Autowired
	private Environment env;
	@Autowired
	private WolverineJobController controller;
	public ServiceDiscoveryClient getClient() {
		return client;
	}
	public void setClient(ServiceDiscoveryClient client) {
		this.client = client;
	}
	public Environment getEnv() {
		return env;
	}
	public void setEnv(Environment env) {
		this.env = env;
	}
	public WolverineJobController getController() {
		return controller;
	}
	public void setController(WolverineJobController controller) {
		this.controller = controller;
	}
	public SomeComponent() {
		super();
	}
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(new com.mongodb.MongoClient("127.0.0.1:27017"), "wolverine");
	}
}
