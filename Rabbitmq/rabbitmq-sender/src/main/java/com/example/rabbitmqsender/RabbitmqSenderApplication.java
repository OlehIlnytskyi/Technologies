package com.example.rabbitmqsender;

import lombok.extern.java.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class RabbitmqSenderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSenderApplication.class, args);
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${rabbitmq.exchanges.order-direct}")
	private String exchangeOrderDirect;

	@Value("${rabbitmq.routing-keys.payment}")
	private String routingKeyPayment;

	@Override
	public void run(String... args) throws Exception {
		String message = "Ty Pidar";
		rabbitTemplate.convertAndSend(exchangeOrderDirect, routingKeyPayment, message);
		log.info("Send message - " + message);
	}
}
