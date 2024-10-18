package com.sijibomiaol.the_bank.controller;

import com.sijibomiaol.the_bank.dto.BankResponse;
import com.sijibomiaol.the_bank.dto.CreditDebitRequest;
import com.sijibomiaol.the_bank.dto.CustomerRequest;
import com.sijibomiaol.the_bank.dto.EnquiryRequest;
import com.sijibomiaol.the_bank.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public BankResponse createAccount(@RequestBody CustomerRequest customerRequest) {
        return customerService.createAccount(customerRequest);
    }

    @GetMapping("/balanceEnquiry")
    public BankResponse getBalanceEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        return customerService.getBalanceEnquiry(enquiryRequest);
    }

    @GetMapping("/nameEnquiry")
    public BankResponse getNameEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        return customerService.getNameEnquiry(enquiryRequest);
    }

    @PostMapping("/credit")
    public  BankResponse creditAccount (@RequestBody CreditDebitRequest creditDebitRequest) {
        return customerService.creditAccount(creditDebitRequest);
    }

    @PostMapping("/debit")
    public  BankResponse debitAccount (@RequestBody CreditDebitRequest creditDebitRequest) {
        return customerService.debitAccount(creditDebitRequest);
    }
}
