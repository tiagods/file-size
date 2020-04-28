package com.tiagods.scanner.listener;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiagods.scanner.model.Arquivo;
import com.tiagods.scanner.model.Arquivo.Tipo;
import com.tiagods.scanner.repository.Arquivos;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {
	
	@Autowired
	private Arquivos arquivos;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	public void receiveMessage(String message) {
		String value = message;
		log.debug("Received <" + value + ">");
		traduzirEnviar(value);
		latch.countDown();
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
	public void traduzirEnviar(String value) {
		Path path = Paths.get(value);
		if(Files.exists(path)){
			Arquivo ar = new Arquivo();
			ar.setAbsoluto(path.toString());
			ar.setTamanho(path.toString().length());
			ar.setNome(path.getFileName().toString());
			ar.setTipo(Files.isDirectory(path)?Tipo.PASTA:Tipo.ARQUIVO);
			arquivos.findByAbsoluto(ar.getAbsoluto()).ifPresentOrElse(c->{
					ar.setId(c.getId()); 
					arquivos.save(ar);}, 
					()->arquivos.save(ar));
		}
	}
	
}