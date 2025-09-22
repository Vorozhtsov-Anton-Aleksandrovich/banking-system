package com.bankx.accountprocessing.service;


import com.bankx.accountprocessing.Dto.AccountDto;
import com.bankx.accountprocessing.entity.AccountEntity;
import com.bankx.accountprocessing.entity.Card;
import com.bankx.accountprocessing.entity.enums.AccountStatus;
import com.bankx.accountprocessing.entity.enums.CardStatus;
import com.bankx.accountprocessing.repository.AccountRepository;
import com.bankx.accountprocessing.repository.CardRepository;
import com.bankx.clientprocessing.kafka.event.CreateClientProductEvent;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CardService cardService;

    public AccountDto createAccount(AccountEntity accountEntity)  {
        accountRepository.save(accountEntity);
        return new AccountDto(
                accountEntity.getClientId(),
                accountEntity.getProductId()
        );
    }

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
}
