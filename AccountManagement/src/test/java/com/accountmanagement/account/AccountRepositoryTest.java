package com.accountmanagement.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    void findAllByClientId() {
        //given
        Integer clientId = 1;
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        //when
        List<Account> expected = accountRepository.findAllByClientId(clientId);
        //then
        assertThat(expected).isNotNull();
    }
}