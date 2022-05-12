package com.accountmanagement.account;

import com.accountmanagement.card.Card;
import com.accountmanagement.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer accountId;
    private String iBan;
    private String bicSwift;
    private Integer clientId;

    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    private List<Card> cards;
}
