package com.example.rabbitmqsender.sender;

import lombok.extern.java.Log;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Log
@Component
public class RabbitmqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchanges.order-direct}")
    private String exchangeOrderDirect;

    @Value("${rabbitmq.routing-keys.payment}")
    private String routingKeyPayment;
    @Value("${rabbitmq.routing-keys.inventory}")
    private String routingKeyInventory;
    @Value("${rabbitmq.routing-keys.shipping}")
    private String routingKeyShipping;

    public void sendMessageToPaymentService(String message) {
        amqpTemplate.convertAndSend(exchangeOrderDirect, routingKeyPayment, message);
        log.info("Send message to Payment Service - " + message);
    }

    public void sendMessageToInventoryService(String message) {
        amqpTemplate.convertAndSend(exchangeOrderDirect, routingKeyInventory, message);
        log.info("Send message to Inventory Service - " + message);
    }

    public void sendMessageToShippingService(String message) {
        amqpTemplate.convertAndSend(exchangeOrderDirect, routingKeyShipping, message);
        log.info("Send message to Shipping Service - " + message);
    }
}
