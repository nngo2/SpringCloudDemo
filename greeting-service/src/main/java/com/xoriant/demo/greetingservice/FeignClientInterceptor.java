package com.xoriant.demo.greetingservice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientInterceptor implements RequestInterceptor {
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";

	@Override
	public void apply(RequestTemplate template) {
		
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		
		if (principal instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) principal;
			Object details = authentication.getDetails();
			if (details instanceof OAuth2AuthenticationDetails) {
				OAuth2AuthenticationDetails oauthsDetails = (OAuth2AuthenticationDetails) details;
				template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, 
						oauthsDetails.getTokenValue()));
			}
		}
	}
}
