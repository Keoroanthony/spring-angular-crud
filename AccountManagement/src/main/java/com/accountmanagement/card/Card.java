package com.accountmanagement.card;

import com.accountmanagement.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    private String cardAlias;

    @Column(updatable = false)
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "account_id", updatable = false)
    @JsonBackReference
    private Account account;
}
