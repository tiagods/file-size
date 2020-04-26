package com.tiagods.scanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagods.scanner.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo,Long>{
	Optional<Arquivo> findByAbsoluto(String absoluto);
}
