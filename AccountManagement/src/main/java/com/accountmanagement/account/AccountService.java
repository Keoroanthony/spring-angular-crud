package com.accountmanagement.account;

import com.accountmanagement.card.Card;
import com.accountmanagement.card.CardRepository;
import com.accountmanagement.card.CardService;
import com.accountmanagement.client.Client;
import com.accountmanagement.client.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CardService cardService;
    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository,
                          CardService cardService,
                          CardRepository cardRepository,
                          ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.cardService = cardService;
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
    }

    /* CREATE */
    public Account createAccount(Account account){
        if (account.getClientId() == null || account.getIBan() == null || account.getBicSwift() == null) throw new IllegalStateException("Check account details");

//        //Check if account being linked to the card exists
        Optional<Client> clientExists = clientRepository.findById(account.getClientId());
        if (clientExists.isEmpty()) throw new IllegalStateException("Client with id " + account.getClientId() + " being linked to account is not found");

        accountRepository.save(account);
        return account;
    }

    /* READ */
    public Account getAccountById(Integer accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) throw new IllegalStateException("Account with id " + accountId + " not found");

        return account.get();
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public List<Account> getAllAccountsByClient(Integer clientId){
        //check if client exists
        var exists = accountRepository.existsById(clientId);
        if (!exists) throw new IllegalStateException("Client with id " + clientId + " not found");

        return accountRepository.findAllByClientId(clientId);
    }

    
    /* UPDATE */
    public void updateAccountById(Account updatedAccount, Integer accountId){
        //Get account by id
        Account account = getAccountById(accountId);
        //todo: Check payload

        //Update account
        account.setBicSwift(updatedAccount.getBicSwift());
        account.setIBan(updatedAccount.getIBan());
        account.setClientId(updatedAccount.getClientId());

        //Save account
        accountRepository.save(account);
    }

    /* DELETE */
    public void deleteAccountById(Integer accountId){
        Account account = getAccountById(accountId);

        //delete cards associated with account
        List<Integer> cardIds = cardRepository.findAll()
                                    .stream()
                                    .filter(card -> card.getAccount().getAccountId() == accountId)
                                    .map(Card::getCardId)
                                    .collect(Collectors.toList());

        cardRepository.deleteAllById(cardIds);

        //delete account
        accountRepository.delete(account);
    }
}
