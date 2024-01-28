package com.example.rabbitmqreceiver.receiver;

import lombok.extern.java.Log;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class ApplicationReceiver {

    @RabbitListener(queues = "${rabbitmq.queues.payment}")
    public void receiveMessageToPaymentService(String message) {
        log.info("Received message - \"" + message + "\" from payment queue");
    }

    @RabbitListener(queues = "${rabbitmq.queues.inventory}")
    public void receiveMessageToInventoryService(String message) {
        log.info("Received message - \"" + message + "\" from inventory queue");
    }

    @RabbitListener(queues = "${rabbitmq.queues.shipping}")
    public void receiveMessageToShippingService(String message) {
        log.info("Received message - \"" + message + "\" from shipping queue");
    }
}
