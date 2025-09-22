package com.bankx.accountprocessing.kafka;

import com.bankx.accountprocessing.service.AccountService;
import com.bankx.clientprocessing.kafka.event.CreateCardEvent;
import com.bankx.clientprocessing.kafka.event.CreateClientProductEvent;
import com.bankx.clientprocessing.kafka.event.CreateTransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    private final AccountService accountService;

    @KafkaListener(
            topics = "${kafka-topics.client-products}",
            groupId = "client-processing-group"
    )
    public void consume(CreateClientProductEvent event) {
        log.info("Received event: {}", event);

        accountService.createAccount(event);
    }

    @KafkaListener(
            topics = "${kafka-topics.client-transactions}",
            groupId = "transactions-processing-group"
    )
    public void consume(CreateTransactionEvent event) {
        // пока непонятно, что с ней делать
    }

    @KafkaListener(
            topics = "${kafka-topics.client-cards}",
            groupId = "cards-processing-group"
    )
    public void consume(CreateCardEvent event) throws AccountNotFoundException {
        Long accountId = accountService.getAccountIdByClientId(event.getClientId());
        accountService.addCardToAccount(accountId);
    }
}
