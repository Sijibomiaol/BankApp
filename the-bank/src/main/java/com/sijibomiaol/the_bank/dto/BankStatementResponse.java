package com.sijibomiaol.the_bank.dto;

import com.sijibomiaol.the_bank.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Builder
public class BankStatementResponse {
    private String accountNumber;
    private String accountName;
    private List<Transaction> transactionList;
    private String message;
    private String status;

    public BankStatementResponse(String Message) {
    }

    public BankStatementResponse(String accountNumber, String accountName, List<Transaction> transactionList, String message, String status) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.transactionList = transactionList;
        this.message = message;
        this.status = status;
    }
}
