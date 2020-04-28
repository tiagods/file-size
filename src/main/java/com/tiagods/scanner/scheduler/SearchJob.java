package com.tiagods.scanner.scheduler;

import java.nio.file.Paths;

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
		long start = System.currentTimeMillis();
		log.info("Start Job");
		arquivos.iniciarScanner(Paths.get("//plkserver/Clientes"));
		log.info("End of process: {}", System.currentTimeMillis()-start);
	}
}
