package com.spring.test;

import org.springframework.stereotype.Component;

@Component("consoleWriter")
public class ConsoleWriter implements LogWriter {

	public void write(String text) {
		System.out.println(text);
	}


}
