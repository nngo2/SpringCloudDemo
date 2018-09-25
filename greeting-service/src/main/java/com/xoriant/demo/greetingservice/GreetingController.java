package com.xoriant.demo.greetingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {

	  @Value("${greeting.from}")
	  private String greetingFrom;
	  
	  @Value("${greeting.secret}")
	  private String secret;
	  
	  @GetMapping("/")
	  public String greeting() {
		  return "Greeting from: " + greetingFrom;
	  }
	  
	  @GetMapping("/secret")
	  public String secret() {
		  return "Secret: " + secret;
	  }
}
