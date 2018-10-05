package com.xoriant.demo.fortuneservice;

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

@RestController
@RefreshScope
public class FortuneController {
	private FortuneService fortuneService;

	@Value("${delay.ms:0}")
	private int delayMs = 0;

	public FortuneController(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@GetMapping("/delay")
	public int getDelay() {
		return delayMs;
	}
	
	@GetMapping("/")
	public Map<String, String> getFortune() {
		String fortune = fortuneService.getFortune();
		artificialDelay();

		Map<String, String> map = new HashMap<>();
		map.put("fortune", fortune);
		return map;
	}

	private void artificialDelay() {
		if (delayMs <= 0)
			return;

		try {
			Thread.sleep(delayMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
