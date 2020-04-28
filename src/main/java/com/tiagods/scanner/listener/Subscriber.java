package com.tiagods.scanner.listener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void enviar(String param) {
		rabbitTemplate.convertAndSend("scanner", "arquivos.monitor", param);
	}
}
