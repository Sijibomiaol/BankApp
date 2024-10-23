package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.BankResponse;
import com.sijibomiaol.the_bank.dto.OtpRequest;
import com.sijibomiaol.the_bank.dto.OtpValidationRequest;

public interface OtpService {
    BankResponse sendOtp(OtpRequest otpRequest);

    BankResponse validateOtp(OtpValidationRequest otpValidationRequest);
}
