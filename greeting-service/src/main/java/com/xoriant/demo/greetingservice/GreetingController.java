package com.xoriant.demo.greetingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.demo.fortuneservice.client.FortuneServiceClient;

@RestController
@RefreshScope
public class GreetingController {

	@Value("${greeting.from}")
	private String greetingFrom;

	@Value("${greeting.secret}")
	private String secret;

	private final GreetingProperties greetingProperties;
	private final FortuneServiceClient fortuneServiceClient;

	public GreetingController(GreetingProperties greetingProperties, FortuneServiceClient fortuneServiceClient) {
		this.greetingProperties = greetingProperties;
		this.fortuneServiceClient = fortuneServiceClient;
	}

	@GetMapping("/")
	public String greeting() {
		return "Greeting from: " + greetingFrom;
	}

	@GetMapping("/secret")
	public String secret() {
		return "Secret: " + secret;
	}

	@GetMapping("/fortune")
	public String getGreeting() {
		
		if (greetingProperties.isDisplayFortune()) {
			return fortuneServiceClient.getFortune();
		}

		return "Fortune service is not enabled!";
	}

}
