package com.tiagods.scanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.tiagods.scanner.model.Arquivo;

@Repository
public interface Arquivos extends JpaRepository<Arquivo,Long>{
	Optional<Arquivo> findByAbsoluto(String absoluto);
	@Procedure("LIMPAR_ARQUIVOS")
	public void apagarRegistros();
}
