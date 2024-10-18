package com.sijibomiaol.the_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    private String transactionType;
    private BigDecimal amount;
    private String accountNumber;
    private String status;
    private LocalDateTime transactionDate;
}
