package com.tiagods.scanner.services;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.tiagods.scanner.model.PathJob;
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
	public void iniciarScanner(final PathJob job){
		Runnable run = () -> {
			try {
				long start = System.currentTimeMillis();
				log.info("Start Job");
				Path path = Paths.get(job.getPath());
				Files.walkFileTree(path, new SimpleFileVisitor<Path> (){
					@Override
					public FileVisitResult visitFile(Path file1, BasicFileAttributes attrs) throws IOException {
						String name = file1.getFileName().toString();
						log.debug(name);
						Optional<String> opt = namesDenied.stream()
								.map(c->c.toUpperCase())
								.filter(f->name.toUpperCase().startsWith(f))
								.findFirst();
						if(!opt.isPresent() && name.length()>=job.getSize()) {
							subscriber.enviar(file1.toString());
						}
						return FileVisitResult.CONTINUE;
					}
				});
				log.info("End of process: {}", System.currentTimeMillis()-start);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		};
		CompletableFuture.runAsync(run);
	}
	public void resetar() {
		Runnable run = () -> {
			long start = System.currentTimeMillis();
			log.info("Solicitação de limpeza solicitada");
			arquivos.apagarRegistros();
			log.info("Solicitação de limpeza concluida: {}", System.currentTimeMillis()-start);
		};
		CompletableFuture.runAsync(run);
	}
	
}
