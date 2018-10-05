package com.xoriant.demo.greetingservice;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AuthInfo {
	private OAuth2AuthenticationDetails oauth2AuthenticationDetails;
	
	public AuthInfo(OAuth2AuthenticationDetails oauthDetails) {
		oauth2AuthenticationDetails = oauthDetails;
	}

	public OAuth2AuthenticationDetails getOauth2AuthenticationDetails() {
		return oauth2AuthenticationDetails;
	}

	public void setOauth2AuthenticationDetails(OAuth2AuthenticationDetails oauth2AuthenticationDetails) {
		this.oauth2AuthenticationDetails = oauth2AuthenticationDetails;
	}
}
