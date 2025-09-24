package com.bankx.accountprocessing.service;

import com.bankx.accountprocessing.entity.Transaction;
import com.bankx.accountprocessing.repository.TransactionRepository;
import com.bankx.clientprocessing.entity.enums.TransactionStatus;
import com.bankx.clientprocessing.kafka.event.CreateTransactionEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(CreateTransactionEvent event) {
        log.info("Create Transaction");
        Transaction transactionToCreate = new Transaction();
        transactionToCreate.setAccountId(event.getAccountId());
        transactionToCreate.setCardId(event.getAccountId());
        transactionToCreate.setType(event.getType());
        transactionToCreate.setAmount(BigDecimal.valueOf(event.getAmount()));
        transactionToCreate.setTransactionStatus(TransactionStatus.PROCESSING);
        return transactionRepository.save(transactionToCreate);
    }

    public void updateTransactionStatus(Long id, TransactionStatus transactionStatus) {
        try {
            Transaction transaction = transactionRepository.findById(id).get();
            transaction.setTransactionStatus(transactionStatus);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Transaction not found: " + id);
        }
    }
}
