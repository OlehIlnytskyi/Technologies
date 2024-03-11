package com.example.rabbitmqsender.threads;

import com.example.rabbitmqsender.sender.RabbitmqSender;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MessagesSpammer implements Runnable {

    private RabbitmqSender rabbitmqSender;
    private String message;
    private int serviceIndex;

    @Override
    public void run() {
        sendMessage();
    }

    private void sendMessage() {
        switch (serviceIndex) {
            case 1 -> rabbitmqSender.sendMessageToPaymentService(message);
            case 2 -> rabbitmqSender.sendMessageToInventoryService(message);
            case 3 -> rabbitmqSender.sendMessageToShippingService(message);
        }
    }
}
