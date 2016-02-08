package com.example;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.netflix.loadbalancer.IPing;

@Configuration
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class EurekaClientConfiguration {

	@Profile("ping.enabled")
	@Bean
	public IPing getPingInstance() {
		return new PingInstance();
	}
}
