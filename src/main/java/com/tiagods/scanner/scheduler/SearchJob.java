package com.tiagods.scanner.scheduler;

import java.nio.file.Paths;

import com.tiagods.scanner.model.PathJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tiagods.scanner.services.ArquivoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SearchJob{
	
	@Autowired
	private ArquivoService arquivos;
	
	@Scheduled(cron = "${agendamento.padrao}")
	public void execute(){
		PathJob job = new PathJob("//plkserver/Clientes",200);
		arquivos.iniciarScanner(job);
	}
}
