package com.sijibomiaol.the_bank.repository;

import com.sijibomiaol.the_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository <Transaction, String>{

    @Query("select t from Transaction t where t.accountNumber= :accountNumber and t.transactionDate between  :startDate and :endDate")
    List<Transaction> findByAccountNumberAndTransactionDateBetween(@Param("accountNumber") String accountNumber,
                                                                   @Param("startDate") LocalDateTime startDate,
                                                                   @Param("endDate") LocalDateTime endDate);

}
