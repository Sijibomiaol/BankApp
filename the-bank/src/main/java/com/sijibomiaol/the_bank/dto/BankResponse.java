package com.sijibomiaol.the_bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankResponse {

    private String responseCode;
    private String responseMessage;

    private AccountInfo info;
}
