package com.sijibomiaol.the_bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankResponse {

    private String responseCode;
    private String responseMessage;
    private AccountInfo info;
    private OtpResponse otpResponse;
}
