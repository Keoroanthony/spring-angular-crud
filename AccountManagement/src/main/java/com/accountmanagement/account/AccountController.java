package com.accountmanagement.account;

import com.accountmanagement.card.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        try {
            accountService.createAccount(account);
            return ResponseEntity.ok("Account created successfully");
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Integer accountId){
        try {
            Account account = accountService.getAccountById(accountId);
            return ResponseEntity.ok(account);
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/client-account/{clientId}")
    public ResponseEntity<?> getAccountByClient(@PathVariable Integer clientId){
        try {
            List<Account> accountsByClient = accountService.getAllAccountsByClient(clientId);
            return ResponseEntity.ok(accountsByClient);
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("{accountId}")
    public ResponseEntity<?> updateAccountById(@RequestBody Account updatedAccount, @PathVariable Integer accountId){
        try {
            accountService.updateAccountById(updatedAccount, accountId);
            return ResponseEntity.ok(Map.of("Update", true));
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("{accountId}")
    public ResponseEntity<?> deleteAccountById(@PathVariable Integer accountId){
        try {
            accountService.deleteAccountById(accountId);
            return ResponseEntity.ok(Map.of("Deleted" , true));
        } catch (IllegalStateException exception){
          return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
