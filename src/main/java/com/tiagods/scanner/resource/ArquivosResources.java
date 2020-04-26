package com.tiagods.scanner.resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagods.scanner.services.ArquivoService;

@RestController
@RequestMapping(value = "/api/arquivos")
public class ArquivosResources {
	
	@Autowired
	ArquivoService arquivos;
	
	@PostMapping("/monitorar")
	public ResponseEntity<?> scanner(@RequestBody @Valid String pasta) {
		Path path = Paths.get(pasta);
		if(Files.exists(path) && Files.isDirectory(path)) {
			arquivos.iniciarScanner(path);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Diretorio inválido\"}");
		}
		return ResponseEntity.ok().build();
	}

}
