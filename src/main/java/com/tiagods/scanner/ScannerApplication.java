package com.tiagods.scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.tiagods.scanner.config.SwaggerConfig;

@SpringBootApplication
public class ScannerApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ScannerApplication.class, args);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ScannerApplication.class, SwaggerConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
