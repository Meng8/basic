package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BasicApplication.class, args);
	}
	
	
	@Autowired
	private CounterService counterService;
	//监听器
	@Bean
	public ApplicationListener<ApplicationEvent> helloListener() {
		final String HELLO_URL = "/xyz";

		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("Welcomea to Taiji");
			}
		};
	} 
}
