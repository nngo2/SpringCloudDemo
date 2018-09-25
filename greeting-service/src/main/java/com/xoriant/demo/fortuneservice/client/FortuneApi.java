package com.xoriant.demo.fortuneservice.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("fortune-service")
public interface FortuneApi {
	@RequestMapping("/")
	Map<String, String> getFortune();
}
