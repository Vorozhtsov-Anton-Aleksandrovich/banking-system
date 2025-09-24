package com.bankx.accountprocessing.service;


import com.bankx.accountprocessing.Dto.AccountDto;
import com.bankx.accountprocessing.entity.AccountEntity;
import com.bankx.accountprocessing.entity.Card;
import com.bankx.accountprocessing.entity.Transaction;
import com.bankx.accountprocessing.entity.enums.AccountStatus;
import com.bankx.accountprocessing.entity.enums.CardStatus;
import com.bankx.accountprocessing.repository.AccountRepository;
import com.bankx.accountprocessing.repository.TransactionRepository;
import com.bankx.clientprocessing.entity.enums.TransactionStatus;
import com.bankx.clientprocessing.kafka.event.CreateClientProductEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final CardService cardService;
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

//    public AccountDto createAccount(AccountEntity accountEntity)  {
//        accountRepository.save(accountEntity);
//        return new AccountDto(
//                accountEntity.getClientId(),
//                accountEntity.getProductId()
//        );
//    }

    //TODO переделать
    public AccountDto createAccount(CreateClientProductEvent createClientProductEvent)  {
        AccountEntity accountEntity = AccountEntity.builder()
                .clientId(createClientProductEvent.getClientId())
                .productId(createClientProductEvent.getProductId())
                .createdAt(LocalDateTime.now()).build();
        accountRepository.save(accountEntity);

        //TODO сделать нормально
        return new AccountDto(
                accountEntity.getClientId(),
                accountEntity.getProductId()
        );
    }

    public Long getAccountIdByClientId(Long ClientId) {
        List<AccountEntity> accountEntities = accountRepository.findByClientId(ClientId);
        return accountEntities.get(0).getId();
    }

    public AccountDto addCardToAccount(Long accountId) throws AccountNotFoundException {
        AccountEntity accountEntity = accountRepository.findById(accountId).orElse(null);
        if (accountEntity == null) {
            throw new AccountNotFoundException("Account not found");
        }
        AccountStatus accountStatus = accountEntity.getAccountStatus();
        if (accountStatus.equals(AccountStatus.BLOCKED) ||
                accountStatus.equals(AccountStatus.CLOSED) ||
                accountStatus.equals(AccountStatus.SUSPENDED)) {
            throw new AccountNotFoundException();
        }
        accountEntity.setCardExist(true);
        accountRepository.save(accountEntity);

        Card card =  new Card();
        card.setAccountId(accountId);
        //TODO пока захордкодил
        card.setCardId("1");
        card.setPaymentSystem("MIR");
        card.setCardStatus(CardStatus.NEW);
        card.setExpireDate(LocalDate.now().plusYears(10));
        cardService.createCard(card);


        //TODO сделать нормально
        return new AccountDto(
                accountEntity.getClientId(),
                accountEntity.getProductId()
        );
    }

    public boolean conductTransaction(Long accountId, Long transactionId)
            throws AccountNotFoundException
    {
        Transaction transaction = transactionRepository
                .findById(transactionId).
                orElse(null);
        if (transaction == null) {
            throw new AccountNotFoundException("Transaction not found");
        }

        if (isAccountBlockedOrClosed(accountId)) {
            log.info("Account {} is blocked or closed", accountId);
            transactionService.updateTransactionStatus(transactionId, TransactionStatus.CANCELLED);
            return false;
        }

        AccountEntity accountEntity = accountRepository
                .findById(accountId)
                .orElse(null);


        BigDecimal accountBalance =  accountEntity.getBalance();
        BigDecimal transactionSum =  transaction.getAmount();

        if (accountBalance.compareTo(transactionSum) < 0) {
            log.info("Transaction cancelled: {}. Not enough money.",
                    transactionId);
            return false;
        }

        transactionService.updateTransactionStatus(transactionId, TransactionStatus.COMPLETE);
        accountEntity.setBalance(accountBalance.subtract(transactionSum));
        transactionRepository.save(transaction);

        return true;
    }

    public boolean isAccountBlockedOrClosed(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId).orElse(null);
        if (accountEntity == null) {
            throw new RuntimeException("Account not found: " + accountId);
        }
        return accountEntity.getAccountStatus().equals(AccountStatus.BLOCKED) ||
                accountEntity.getAccountStatus().equals(AccountStatus.CLOSED);
    }
}
