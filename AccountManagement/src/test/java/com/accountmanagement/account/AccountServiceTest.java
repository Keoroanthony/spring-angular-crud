package com.accountmanagement.account;

import com.accountmanagement.card.CardRepository;
import com.accountmanagement.card.CardService;
import com.accountmanagement.client.ClientRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    private AccountService accountService;
    @Mock private AccountRepository accountRepository;
    @Mock private CardService cardService;
    @Mock private CardRepository cardRepository;
    @Mock private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        accountService = new AccountService(
                accountRepository,
                cardService,
                cardRepository,
                clientRepository);
    }

    @Test
    @Disabled
    void createAccount() {
        //given
        Account account = new Account();
        account.setAccountId(1);
        account.setIBan("saving");
        account.setBicSwift("anthony-savings");
        account.setClientId(1);

        //when
        when(accountRepository.save(ArgumentMatchers.any(Account.class))).thenReturn(account);
        Account created = accountService.createAccount(account);

        //then
        verify(accountRepository).save(account);
        assertThat(created).isSameAs(account);

    }

    @Test
    void getAccountById() {
        //given
        Account account = new Account();
        account.setAccountId(1);
        //when
        when(accountRepository.findById(account.getAccountId())).thenReturn(Optional.of(account));
        Account exists = accountService.getAccountById(account.getAccountId());
        //then
        verify(accountRepository).findById(account.getAccountId());
        assertThat(exists).isSameAs(account);
    }

    @Test
    void getAllAccounts() {
        //given
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());
        given(accountRepository.findAll()).willReturn(accounts);
        //when
        List<Account> expected = accountService.getAllAccounts();
        //then
        verify(accountRepository).findAll();
        assertEquals(expected, accounts);
    }

    @Test
    void getAllAccountsByClient() {
        //given
        Integer clientId = 1;
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account());

        given(accountRepository.existsById(clientId))
                .willReturn(true);

        given(accountRepository.findAllByClientId(clientId)).willReturn(accounts);
        //when
        var allAccountsByClient = accountService.getAllAccountsByClient(clientId);

        //then
        assertThat(accounts).isNotNull();
        assertThat(allAccountsByClient).isNotNull();
        assertEquals(allAccountsByClient, accounts);
        verify(accountRepository).findAllByClientId(clientId);
    }

    @Test
    void updateAccountById() {
        //given
        Account account = new Account(
                1,
                "anthony",
                "anthony-saving",
                1,
                null
        );
        Account newAccount = new Account(
                1,
                "anthony",
                "anthony-invest",
                1,
                null
        );

        given(accountRepository.findById(account.getAccountId())).willReturn(Optional.of(account));
        //when
        accountService.updateAccountById(newAccount, account.getAccountId());
        //then
        verify(accountRepository).save(newAccount);
        verify(accountRepository).findById(account.getAccountId());

    }

    @Test
    void deleteAccountById() {
        //given
        Account account = new Account();
        account.setAccountId(1);
        //when
        when(accountRepository.findById(account.getAccountId())).thenReturn(Optional.of(account));
        //then
        accountService.deleteAccountById(account.getAccountId());
        verify(accountRepository).delete(account);
    }
}