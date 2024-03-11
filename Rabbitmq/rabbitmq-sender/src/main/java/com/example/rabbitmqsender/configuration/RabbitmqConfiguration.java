package com.example.rabbitmqsender.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

    @Value("${rabbitmq.exchanges.order-direct}")
    private String exchangeOrderDirect;

    @Value("${rabbitmq.queues.payment}")
    private String paymentQueue;
    @Value("${rabbitmq.queues.inventory}")
    private String inventoryQueue;
    @Value("${rabbitmq.queues.shipping}")
    private String shippingQueue;

    @Value("${rabbitmq.routing-keys.payment}")
    private String routingKeyPayment;
    @Value("${rabbitmq.routing-keys.inventory}")
    private String routingKeyInventory;
    @Value("${rabbitmq.routing-keys.shipping}")
    private String routingKeyShipping;

    @Bean
    public DirectExchange orderDirectExchange() {
        return new DirectExchange(exchangeOrderDirect);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(paymentQueue);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(inventoryQueue);
    }

    @Bean
    public Queue shippingQueue() {
        return new Queue(shippingQueue);
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder
                .bind(paymentQueue())
                .to(orderDirectExchange())
                .with(routingKeyPayment);
    }

    @Bean
    public Binding inventoryBinding() {
        return BindingBuilder
                .bind(inventoryQueue())
                .to(orderDirectExchange())
                .with(routingKeyInventory);
    }

    @Bean
    public Binding shippingBinding() {
        return BindingBuilder
                .bind(shippingQueue())
                .to(orderDirectExchange())
                .with(routingKeyShipping);
    }
}
