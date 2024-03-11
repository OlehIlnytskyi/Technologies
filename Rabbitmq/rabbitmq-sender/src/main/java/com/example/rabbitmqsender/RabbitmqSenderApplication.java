package com.example.rabbitmqsender;

import com.example.rabbitmqsender.sender.RabbitmqSender;
import com.example.rabbitmqsender.threads.MessagesSpammer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RabbitmqSenderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSenderApplication.class, args);
	}

	@Autowired
	private RabbitmqSender rabbitmqSender;

	@Override
	public void run(String... args) throws Exception {
		MessagesSpammer paymentSpammer = new MessagesSpammer(rabbitmqSender, "Payment Message - Ty Pidar", 1);
		MessagesSpammer inventorySpammer = new MessagesSpammer(rabbitmqSender, "Inventory Message - Ty Pidar", 2);
		MessagesSpammer shippingSpammer = new MessagesSpammer(rabbitmqSender, "Shipping Message - Ty Pidar", 3);

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
		executor.scheduleAtFixedRate(paymentSpammer, 3, 1, TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(inventorySpammer, 4, 2, TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(shippingSpammer, 5, 1, TimeUnit.SECONDS);
	}
}
