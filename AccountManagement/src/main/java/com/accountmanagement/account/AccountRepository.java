package com.accountmanagement.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("FROM Account i WHERE i.clientId= ?1")
    List<Account> findAllByClientId(Integer clientId);
}
