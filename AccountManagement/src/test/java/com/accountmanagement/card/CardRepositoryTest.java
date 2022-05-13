package com.accountmanagement.card;

import com.accountmanagement.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;
    @Test
    void findAllByAccount_AccountId() {
        //given
        Integer accountId = 1;
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        //when
        List<Card> expected = cardRepository.findAllByAccount_AccountId(accountId);
        //then
        assertThat(expected).isNotNull();
    }
}