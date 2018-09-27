package com.xoriant.demo.fortuneservice.client;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class FortuneServiceClient {
	private FortuneApi fortuneApi;

	public FortuneServiceClient(FortuneApi fortuneApi) {
		this.fortuneApi = fortuneApi;
	}

	/*
	 * this method fallback is overridden by class-based fallback!!!
	 */
	@HystrixCommand(fallbackMethod = "defaultFortune")
	public String getFortune() {
		Map<String, String> result = fortuneApi.getFortune();
		String fortune = result.get("fortune");
		return fortune;
	}

	public String defaultFortune() {
		return "Your future is uncertain - Fortune service down";
	}
	
	
	public String getFortune2() {
		Map<String, String> result = fortuneApi.getFortune();
		String fortune = result.get("fortune");
		return fortune;
	}
}
