package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.TransactionDto;
import com.sijibomiaol.the_bank.entity.Transaction;
import com.sijibomiaol.the_bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    @Transactional
    public void  savedTransaction(TransactionDto transactionDto){
        Transaction savedTransaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .transactionDate(transactionDto.getTransactionDate())
                .status("Success")
                .build();

        transactionRepository.save(savedTransaction);
    }


}
