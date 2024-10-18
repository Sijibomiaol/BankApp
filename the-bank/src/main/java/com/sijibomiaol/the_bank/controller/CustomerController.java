package com.sijibomiaol.the_bank.controller;

import com.sijibomiaol.the_bank.dto.*;
import com.sijibomiaol.the_bank.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Slf4j
@Tag(name = "Customer Management APIs")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(
            summary =  "Create New User Account",
            description = "Http Status 201 CREATED"
    )
    @ApiResponse(
            responseCode= "201",
            description="Htpp Status 201 Created"
    )
    
    @PostMapping
    public BankResponse createAccount(@RequestBody CustomerRequest customerRequest) {
        return customerService.createAccount(customerRequest);
    }

    @Operation(
            summary =  "Balance Enquiry",
            description = "Given an account number, check how much the user has"
    )
    @ApiResponse(
            responseCode= "200",
            description="Htpp Status Success"
    )

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

    @PostMapping("/transfer")
    public BankResponse transfer(@RequestBody TransferRequest transferRequest) {
        return customerService.doTransfer(transferRequest);
    }


}
