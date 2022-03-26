package com.accountmanagement.account;

import com.accountmanagement.card.Card;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
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
