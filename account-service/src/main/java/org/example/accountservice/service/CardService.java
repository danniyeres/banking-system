package org.example.accountservice.service;

import lombok.RequiredArgsConstructor;
import org.example.accountservice.model.Account;
import org.example.accountservice.model.Card;
import org.example.accountservice.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;

    public Card createCard(Account account) {
        Card card = Card.builder()
                .number(createCardNumber(account.getId()))
                .expiryDate(createExpiryDate())
                .cvv(createCardCvv())
                .account(account)
                .build();
        cardRepository.save(card);
        return card;
    }

    public Card getCard(Long accountId) {
        return cardRepository.findByAccount_Id(accountId);
    }

    public void deleteCard(Long accountId) {
        Card card = cardRepository.findByAccount_Id(accountId);
        cardRepository.delete(card);
    }

    public String createCardNumber(Long accountId) {
        var cardNumber = "44001234";
        int accountIdLength = String.valueOf(accountId).length();
        for (int i = 0; i < 8 - accountIdLength; i++) {
            cardNumber += 0;
        }
        cardNumber += accountId;
        return cardNumber;
    }

    public String createExpiryDate() {
        LocalDate today = LocalDate.now().plusYears(5);
        String month = today.getMonthValue() < 10 ? "0" + today.getMonthValue() : String.valueOf(today.getMonthValue());
        String year = String.valueOf(today.getYear()).substring(2);
        return month + "/" + year;
    }

    public static int createCardCvv() {
        int cvv =  (int) (Math.random() * 1000);
        if (String.valueOf(cvv).length() == 3)
            return cvv;
        else
            return createCardCvv();
    }
}
