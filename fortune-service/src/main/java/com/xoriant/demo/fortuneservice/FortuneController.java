package com.xoriant.demo.fortuneservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

}
