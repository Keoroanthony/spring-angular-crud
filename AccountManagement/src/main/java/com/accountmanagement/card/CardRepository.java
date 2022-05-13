package com.accountmanagement.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {
    @Query("FROM Card i WHERE i.account.accountId= ?1")
    List<Card> findAllByAccount_AccountId(Integer accountId);
}
