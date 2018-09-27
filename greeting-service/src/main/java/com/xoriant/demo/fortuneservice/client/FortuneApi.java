package com.xoriant.demo.fortuneservice.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "fortune-service", fallbackFactory = FortuneApiFallbackFactory.class)
public interface FortuneApi {
	@RequestMapping("/")
	Map<String, String> getFortune();
}

@Component
class FortuneApiFallbackFactory implements FallbackFactory<FortuneApi> {

    @Override
    public FortuneApi create(Throwable throwable) {
        return new FortuneApiFallback(throwable);
    }

}

class FortuneApiFallback implements FortuneApi {
	private final Logger logger = LoggerFactory.getLogger(FortuneApiFallbackFactory.class);
	private final Throwable cause;

	public FortuneApiFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public Map<String, String> getFortune() {
		//if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.info("Fallback called: ", cause);
        //}

		Map<String, String> result = new HashMap<>();
		result.put("fortune", "Your future is uncertain - Fortune service down, used feign fallback class");
		return result;
	}
}
