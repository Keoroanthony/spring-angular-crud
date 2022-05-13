package com.accountmanagement.card;

import com.accountmanagement.account.Account;
import com.accountmanagement.account.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    private  CardService cardService;
    @Mock private CardRepository cardRepository;
    @Mock private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        cardService = new CardService(
                cardRepository,
                accountRepository
        );
    }

    @Test
    @Disabled
    void createCard() {
        //given
        Account account = new Account();
        account.setAccountId(1);

        Card card = new Card();
        card.setCardId(1);
        card.setCardAlias("debit card");
        card.setCardType(CardType.PHYSICAL);
        card.setAccount(account);
        //when
        when(cardRepository.save(ArgumentMatchers.any(Card.class))).thenReturn(card);
        Card created = cardService.createCard(card);

        //then
        verify(cardRepository).save(card);
        assertThat(created).isSameAs(card);
    }

    @Test
    void getCardById() {
        //given
        Card card = new Card();
        card.setCardId(1);
        //when
        when(cardRepository.findById(card.getCardId())).thenReturn(Optional.of(card));
        Card exists = cardService.getCardById(card.getCardId());
        //then
        verify(cardRepository).findById(card.getCardId());
        assertThat(exists).isSameAs(card);
    }

    @Test
    void getAllCards() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        given(cardRepository.findAll()).willReturn(cards);
        //when
        List<Card> expected = cardService.getAllCards();
        //then
        verify(cardRepository).findAll();
        assertEquals(expected, cards);
    }

    @Test
    void getAllCardsByAccount() {
        //given
        Integer accountId = 1;
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());

        given(cardRepository.existsById(accountId))
                .willReturn(true);

        given(cardRepository.findAllByAccount_AccountId(accountId)).willReturn(cards);
        //when
        var allCardsByAccount = cardService.getAllCardsByAccount(accountId);

        //then
        assertThat(cards).isNotNull();
        assertThat(allCardsByAccount).isNotNull();
        assertEquals(allCardsByAccount, cards);
        verify(cardRepository).findAllByAccount_AccountId(accountId);
    }

    @Test
    void updateCardById() {
        //given
        Card card = new Card(
                1,
                "debit",
                CardType.PHYSICAL,
                new Account()
        );
        Card newCard = new Card(
                1,
                "credit",
                CardType.PHYSICAL,
                new Account()
        );
        //when
        given(cardRepository.findById(card.getCardId())).willReturn(Optional.of(card));
        //when
        cardService.updateCardById(newCard, card.getCardId());
        //then
        verify(cardRepository).save(newCard);
        verify(cardRepository).findById(card.getCardId());

    }

    @Test
    void deleteCardById() {
        //given
        Card card = new Card();
        card.setCardId(1);
        //when
        when(cardRepository.findById(card.getCardId())).thenReturn(Optional.of(card));
        //then
        cardService.deleteCardById(card.getCardId());
        verify(cardRepository).delete(card);
    }
}