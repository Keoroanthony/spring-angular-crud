package com.accountmanagement.card;

import com.accountmanagement.account.Account;
import com.accountmanagement.account.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    /* CREATE */
    public Card createCard(Card card){
        //Check fields not null
        if (card.getCardAlias() == null || card.getCardType() == null || card.getAccount().getAccountId() == null) throw new IllegalStateException("Check card fields");

        //Check if account being linked to the card exists
        Integer accountId = card.getAccount().getAccountId();
        Optional<Account> account = accountRepository.findById(card.getAccount().getAccountId());
        if (account.isEmpty()) throw new IllegalStateException("Account with id " + accountId + " being linked to card is not found");

        cardRepository.save(card);
        return card;
    }


    /* READ */
    public Card getCardById(Integer cardId){
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isEmpty()) throw new IllegalStateException("Card with id " + cardId + " not found.");

        return card.get();
    }

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public List<Card> getAllCardsByAccount(Integer accountId){
        //Check if account exists
        var exists = cardRepository.existsById(accountId);
        if (!exists) throw new IllegalStateException("Account with id " + accountId + " not found");

        return cardRepository.findAllByAccount_AccountId(accountId);

    }

    /* UPDATE */
    public void updateCardById(Card updatedCard, Integer cardId){
        Card card = getCardById(cardId);

        //Update fields
        //Only field updatable
        card.setCardAlias(updatedCard.getCardAlias());

        cardRepository.save(card);
    }

    /* DELETE */
    public void deleteCardById(Integer cardId){
        //Check if card exists
        Card card = getCardById(cardId);

        //delete card
        cardRepository.delete(card);
    }
}
