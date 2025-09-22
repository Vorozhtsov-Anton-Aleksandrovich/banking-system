package com.bankx.clientprocessing.kafka;

import com.bankx.clientprocessing.kafka.event.CreateCardEvent;
import com.bankx.clientprocessing.kafka.event.CreateClientCreditProductEvent;
import com.bankx.clientprocessing.kafka.event.CreateClientProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    private final String LOG_INFO_MESSAGE = "Sending event to topic {}: {}";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka-topics.client-products}")
    private String TOPIC_CLIENT_PRODUCTS;
    @Value("${kafka-topics.client-credit-products}")
    private String TOPIC_CLIENT_CREDIT_PRODUCTS;
    @Value("${kafka-topics.client-cards}")
    private String TOPIC_CARD;

    public void sendCreateClientProductEvent(CreateClientProductEvent event) {
        log.info(LOG_INFO_MESSAGE, TOPIC_CLIENT_PRODUCTS, event);
        kafkaTemplate.send(TOPIC_CLIENT_PRODUCTS, String.valueOf(event.getClientId()), event);
    }

    public void sendCreateClientCreditProductEvent(CreateClientCreditProductEvent event) {
        log.info(LOG_INFO_MESSAGE, TOPIC_CLIENT_CREDIT_PRODUCTS, event);
        kafkaTemplate.send(TOPIC_CLIENT_CREDIT_PRODUCTS, String.valueOf(event.getClientId()), event);
    }

    public void sendCreateCardEvent(CreateCardEvent event) {
        log.info(LOG_INFO_MESSAGE, TOPIC_CARD, event);
        kafkaTemplate.send(TOPIC_CARD, String.valueOf(event.getClientId()), event);
    }
}