package com.bankx.clientprocessing.kafka;

import com.bankx.clientprocessing.kafka.event.CreateCardEvent;
import com.bankx.clientprocessing.kafka.event.CreateClientCreditProductEvent;
import com.bankx.clientprocessing.kafka.event.CreateClientProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final String LOG_INFO_MESSAGE = "Sending event to topic {} with key {}: {}";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka-topics.client-products}")
    private String TOPIC_CLIENT_PRODUCTS;
    @Value("${kafka-topics.client-credit-products}")
    private String TOPIC_CLIENT_CREDIT_PRODUCTS;
    @Value("${kafka-topics.client-cards}")
    private String TOPIC_CARD;

    private static String getKey() {
        return UUID.randomUUID().toString();
    }

    private void sendEvent(String topic, Object event) {
        String key = getKey();
        log.info(LOG_INFO_MESSAGE, topic, key, event);
        kafkaTemplate.send(topic, key, event);
    }

    public void sendCreateClientProductEvent(CreateClientProductEvent event) {
        sendEvent(TOPIC_CLIENT_PRODUCTS, event);
    }

    public void sendCreateClientCreditProductEvent(CreateClientCreditProductEvent event) {
        sendEvent(TOPIC_CLIENT_CREDIT_PRODUCTS, event);
    }

    public void sendCreateCardEvent(CreateCardEvent event) {
        sendEvent(TOPIC_CARD, event);
    }
}