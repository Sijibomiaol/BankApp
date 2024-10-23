package com.sijibomiaol.the_bank.controller;

import com.sijibomiaol.the_bank.dto.BankResponse;
import com.sijibomiaol.the_bank.dto.OtpRequest;
import com.sijibomiaol.the_bank.dto.OtpValidationRequest;
import com.sijibomiaol.the_bank.service.OtpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/otp")
@AllArgsConstructor
public class OtpContoller {

    private final OtpService otpService;

    @PostMapping("/sendOtp")
    public BankResponse sendOtp(@RequestBody OtpRequest otpRequest) {
        return otpService.sendOtp(otpRequest);
    }
    @PostMapping("/validateOtp")
    public BankResponse validateOtp(@RequestBody OtpValidationRequest otpValidationRequest) {
        return otpService.validateOtp(otpValidationRequest);
    }
}
