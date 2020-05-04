package com.tiagods.scanner.resource;

import javax.validation.Valid;

import com.tiagods.scanner.model.PathJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tiagods.scanner.services.ArquivoService;

@RestController
@RequestMapping(value = "/api/arquivos")
public class ArquivosResources {
	
	@Autowired
	ArquivoService arquivos;
	
	@PostMapping(value = "/monitorar")
	public ResponseEntity<?> scanner(@RequestBody @Valid PathJob job) {
		arquivos.iniciarScanner(job);
		return ResponseEntity.ok().build();
	}
	@GetMapping
	public ResponseEntity<?> next(){
		return ResponseEntity.noContent().build();
	}

}
