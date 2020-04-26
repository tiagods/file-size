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

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ArquivoService {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
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
					    	int size = file.toString().length();
					    	
					    	Optional<String> opt = namesDenied.stream()
					    			.map(c->c.toUpperCase())
					    			.filter(f->name.toUpperCase().startsWith(f))
					    			.findFirst();
					    	if(!opt.isPresent()) {
					    		rabbitTemplate.convertAndSend("scanner", "arquivos.monitor", file.toString());
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
	
}
