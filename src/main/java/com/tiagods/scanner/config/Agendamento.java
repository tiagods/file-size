package com.tiagods.scanner.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(value = "agendamento")
@Data
public class Agendamento {
	private String padrao;
}