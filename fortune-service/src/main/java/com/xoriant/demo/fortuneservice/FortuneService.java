package com.xoriant.demo.fortuneservice;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class FortuneService {

	private static final String[] Fortunes = { "You learn from your mistakes... You will learn a lot today.",
			"You can always find happiness at work on Friday", "You will be hungry again in one hour.",
			"Today will be an awesome day!", "Good news is waiting for you next month", "It is the right time to commence your plan", 
			"Your future is yours", "More challenges are coming, stay tune!", "Someone is The more you travel, the more you learn..."};

	private Random random = new Random();

	public String getFortune() {
		return Fortunes[randomIndexIntoFortunes()];
	}

	private int randomIndexIntoFortunes() {
		return random.nextInt(Fortunes.length);
	}
}
