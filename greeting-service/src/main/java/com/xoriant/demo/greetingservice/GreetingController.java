package com.xoriant.demo.greetingservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
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
	
	@GetMapping("/future")
	public String getGreeting2() {
		
		if (greetingProperties.isDisplayFortune()) {
			return fortuneServiceClient.getFortune2();
		}

		return "Fortune service is not enabled!";
	}
	
	
	@GetMapping("/user")
    public Map<String, Object> getOAuthInfo() {
		Map<String, Object> user = new HashMap<>();
      
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		
		user.put("user", principal);
		
		if (principal instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) principal;
			Object details = authentication.getDetails();
			if (details instanceof OAuth2AuthenticationDetails) {
				OAuth2AuthenticationDetails oauthsDetails = (OAuth2AuthenticationDetails) details;
				String token = oauthsDetails.getTokenValue();
				user.put("access-token", token);
			}
		}
		
		return user;
		
	}
}
