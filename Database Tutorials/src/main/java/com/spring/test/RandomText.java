package com.spring.test;

import java.util.Random;

import org.springframework.stereotype.Component;

// outputs one of three line of text randomly
@Component
public class RandomText {
	
	private static String[] texts = {
			"I'll be back",
			"Get out!",
			"I want your clothes, boots and motorcycle."
	};
	
	public String getText() {
		Random random = new Random();
		//return null;
		return texts[random.nextInt(texts.length)];
	}
	
}
