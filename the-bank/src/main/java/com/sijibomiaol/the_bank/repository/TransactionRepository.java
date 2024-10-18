package com.sijibomiaol.the_bank.repository;

import com.sijibomiaol.the_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository <Transaction, String>{

}
