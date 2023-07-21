package com.he.train.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GatewayApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(GatewayApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		
		Environment env = application.run(args).getEnvironment();
		LOGGER.info(">>>>> Start [GatewayApplication]");
		LOGGER.info(">>>>> Address: http://127.0.0.1:{}", env.getProperty("server.port"));
	}
}
