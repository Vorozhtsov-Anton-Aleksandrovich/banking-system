package com.bankx.creditprocessing.kafka;

import com.bankx.clientprocessing.kafka.event.CreateClientCreditProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import javax.security.auth.login.AccountNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(
            topics = "${kafka-topics.client-credit-products}",
            groupId = "credits-processing-group"
    )
    public void consume(CreateClientCreditProductEvent event) throws AccountNotFoundException {
        //TODO доделать
    }
}
