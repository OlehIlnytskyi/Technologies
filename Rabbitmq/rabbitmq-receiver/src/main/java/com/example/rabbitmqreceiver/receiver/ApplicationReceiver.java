package com.example.rabbitmqreceiver.receiver;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class ApplicationReceiver {

    @RabbitListener(queues = "${rabbitmq.queues.payment}")
    public void processPayment(String message) {
        log.info("Received message - \"" + message + "\" from payment queue");
    }
}
