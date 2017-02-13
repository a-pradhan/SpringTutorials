package com.spring.test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Component;


@Component
public class Logger {
	
//	private ConsoleWriter consoleWriter;
//	private FileWriter fileWriter;
	
	private ConsoleWriter consoleWriter;
	private LogWriter fileWriter;
	
	@Inject
	@Named(value="consoleWriter")
	public void setConsoleWriter(ConsoleWriter consoleWriter) {
		this.consoleWriter = consoleWriter;
	}
	
	@Inject
	public void setFileWriter(LogWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public void writeFile(String text) {
		fileWriter.write(text);
	}
	
	public void writeConsole(String text) {
		if(consoleWriter != null)
		  consoleWriter.write(text);
	}
	
	//@PostConstruct
	public void init() {
		System.out.println("init");
	}
	
	//@PreDestroy
	public void destroy() {
		System.out.println("destroy");
	}

}
