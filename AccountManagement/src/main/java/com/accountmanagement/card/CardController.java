package com.accountmanagement.card;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody Card card){
        try{
            cardService.createCard(card);
            return ResponseEntity.ok(Map.of("Account created", true));

        } catch (IllegalStateException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards(){
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<?> getCardById(@PathVariable Integer cardId) {
        try {
            Card card = cardService.getCardById(cardId);
            return ResponseEntity.ok(card);
        } catch (IllegalStateException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/account-cards/{accountId}")
    public ResponseEntity<?> getCardsByAccount(@PathVariable Integer accountId){
        try {
            List<Card> cardsByAccount = cardService.getAllCardsByAccount(accountId);
            return ResponseEntity.ok(cardsByAccount);
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{cardId}")
    public ResponseEntity<?> updateCardById(@RequestBody Card card, @PathVariable Integer cardId){
        try {
            cardService.updateCardById(card, cardId);
            return ResponseEntity.ok(Map.of("Updated", true));
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{cardId}")
    public ResponseEntity<?> deleteCardById(@PathVariable Integer cardId){
        try {
            cardService.deleteCardById(cardId);
            return ResponseEntity.ok(Map.of("Deleted", true));
        } catch (IllegalStateException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
