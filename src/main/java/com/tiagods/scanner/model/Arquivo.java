package com.tiagods.scanner.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arquivo {

	public enum Tipo{
		ARQUIVO,PASTA,LINK
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "text")
	private String absoluto;
	private String nome;
	private int tamanho;
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	private Calendar atualizacao = Calendar.getInstance();
	@PreUpdate
	void onUpdate() {
		this.atualizacao = Calendar.getInstance();
	}
}
