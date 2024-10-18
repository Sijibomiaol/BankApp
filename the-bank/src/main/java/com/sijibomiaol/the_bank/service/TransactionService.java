package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.TransactionDto;
import com.sijibomiaol.the_bank.entity.Transaction;

public interface TransactionService {

    void  savedTransaction(TransactionDto transactionDto);
}
