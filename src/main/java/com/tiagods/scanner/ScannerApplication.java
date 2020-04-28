package com.tiagods.scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tiagods.scanner.services.ArquivoService;

@SpringBootApplication
@EnableScheduling
public class ScannerApplication implements CommandLineRunner{
	public static void main(String[] args){
		SpringApplication.run(ScannerApplication.class, args);
	}

	@Autowired ArquivoService servico;
	@Override
	public void run(String... args) throws Exception {
		servico.resetar();
	}
	
}
