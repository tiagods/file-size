package com.tiagods.scanner.services;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tiagods.scanner.listener.Subscriber;
import com.tiagods.scanner.repository.Arquivos;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ArquivoService {

	@Autowired
	private Arquivos arquivos;
	
	@Autowired
	private Subscriber subscriber;
	
	final static List<String> namesDenied = Arrays.asList("Thumbs.db","~$");
	
	@Async
	public void iniciarScanner(final Path file){
		Runnable run = new Runnable() {
			public void run() {
				try {
					Files.walkFileTree(file, new SimpleFileVisitor<Path> (){
					    @Override
					    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					    	String name = file.getFileName().toString();
					    	log.debug(name);
					    	Optional<String> opt = namesDenied.stream()
					    			.map(c->c.toUpperCase())
					    			.filter(f->name.toUpperCase().startsWith(f))
					    			.findFirst();
					    	if(!opt.isPresent()) {
					    		subscriber.enviar(file.toString());
					    	}
							return FileVisitResult.CONTINUE;
					    }
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		CompletableFuture.runAsync(run);
	}
	@Async
	public void resetar() {
		Runnable run = new Runnable() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				log.info("Solicitação de limpeza solicitada");
				arquivos.apagarRegistros();
				log.info("Solicitação de limpeza concluida: {}", System.currentTimeMillis()-start);
			}
		};
		CompletableFuture.runAsync(run);
	}
	
}
