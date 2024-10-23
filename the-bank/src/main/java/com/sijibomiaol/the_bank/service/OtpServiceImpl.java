package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.*;
import com.sijibomiaol.the_bank.entity.Otp;
import com.sijibomiaol.the_bank.repository.OtpRepository;
import com.sijibomiaol.the_bank.utils.AccountUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;

    private final EmailService emailService;


    @Override
    public BankResponse sendOtp(OtpRequest otpRequest) {

        Otp existingOtp = otpRepository.findByEmail(otpRequest.getEmail());
         if(existingOtp != null) {
             otpRepository.delete(existingOtp);
         }
        String otp = AccountUtils.generateOtp();
        log.info("Sending OTP : {}", otp);
        otpRepository.save(Otp.builder()
                        .email(otpRequest.getEmail())
                        .otp(otp)
                        .expireAt(LocalDateTime.now().plusMinutes(5))
                .build());
        emailService.sendEMailAlert(EmailDetails.builder()
                        .subject("Do not Disclose!!")
                        .recipient(otpRequest.getEmail())
                        .body("This otp expires in 5 min" + otp)
                .build());

        return BankResponse.builder()
                .responseCode(AccountUtils.OTP_SUCCESS_CODE)
                .responseMessage(AccountUtils.OTP_SUCCESS_MESSAGE)
                .build();
    }
    @Override
    public BankResponse validateOtp(OtpValidationRequest otpValidationRequest) {

        Otp otp = otpRepository.findByEmail(otpValidationRequest.getEmail());
        log.info("Validating OTP : {}", otpValidationRequest.getEmail());
        if (otp == null) {
            return BankResponse.builder()
                    .responseMessage(AccountUtils.VALIDATE_FAIL_MESSAGE)
                    .responseCode(AccountUtils.VALIDATE_FAIL_CODE)
                    .build();
        }

        if (otp.getExpireAt().isBefore(LocalDateTime.now())) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.EXPIRED_OTP_CODE)
                    .responseMessage(AccountUtils.EXPIRED_OTP_MESSAGE)
                    .build();
        }
        if (!otp.getOtp().equals(otpValidationRequest.getOtp())){
            return BankResponse.builder()
                    .responseMessage(AccountUtils.INCALID_OTP_MESSAGE)
                    .responseCode(AccountUtils.INVALID_OTP_CODE)
                    .build();
        }

        return BankResponse.builder()
                .responseMessage(AccountUtils.VALIDATE_OTP_MESSAGE)
                .responseCode(AccountUtils.VALIDATE_OTP_CODE)
                .otpResponse(OtpResponse.builder()
                        .isOtpValid(true)
                        .build())
                .build();


    }

}
