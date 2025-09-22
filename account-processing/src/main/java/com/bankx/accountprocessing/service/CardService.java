package com.bankx.accountprocessing.service;

import com.bankx.accountprocessing.Dto.CardDto;
import com.bankx.accountprocessing.entity.Card;
import com.bankx.accountprocessing.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardDto createCard(Card cardEntity) {
         cardRepository.save(cardEntity);

         //TODO нормальный вывод
         return null;
    }
}
