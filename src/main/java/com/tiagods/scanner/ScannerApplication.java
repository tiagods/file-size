package com.tiagods.scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@Slf4j
public class ScannerApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(ScannerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ScannerApplication.class);
	}
	
}
