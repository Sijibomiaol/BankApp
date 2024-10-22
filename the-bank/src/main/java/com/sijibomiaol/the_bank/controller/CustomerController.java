package com.sijibomiaol.the_bank.controller;

import com.itextpdf.text.DocumentException;
import com.sijibomiaol.the_bank.dto.*;
import com.sijibomiaol.the_bank.entity.Customer;
import com.sijibomiaol.the_bank.entity.Transaction;
import com.sijibomiaol.the_bank.repository.CustomerRepository;
import com.sijibomiaol.the_bank.service.BankStatement;
import com.sijibomiaol.the_bank.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Customer Management APIs")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @Autowired
    private BankStatement bankStatement;
    @Autowired
    private CustomerRepository customerRepository;

    @Operation(
            summary = "Create New User Account",
            description = "Http Status 201 CREATED"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Htpp Status 201 Created"
    )

    @PostMapping
    public BankResponse createAccount(@RequestBody CustomerRequest customerRequest) {
        return customerService.createAccount(customerRequest);
    }

    @Operation(
            summary = "Balance Enquiry",
            description = "Given an account number, check how much the user has"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Htpp Status Success"
    )

    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto) {
        return customerService.login(loginDto);
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
    public BankResponse creditAccount(@RequestBody CreditDebitRequest creditDebitRequest) {
        return customerService.creditAccount(creditDebitRequest);
    }

    @PostMapping("/debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest creditDebitRequest) {
        return customerService.debitAccount(creditDebitRequest);
    }

    @PostMapping("/transfer")
    public BankResponse transfer(@RequestBody TransferRequest transferRequest) {
        return customerService.doTransfer(transferRequest);
    }


    @GetMapping("/bankStatement")
    public ResponseEntity<BankStatementResponse> generateBankStatement(
            @RequestParam String accountNumber,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start;
        LocalDate end;

        // Parse the dates with exception handling
        try {
            start = LocalDate.parse(startDate, formatter);
            end = LocalDate.parse(endDate, formatter);
        } catch (DateTimeParseException e) {
            log.error("Invalid date format: startDate={}, endDate={}", startDate, endDate);
            return ResponseEntity.badRequest().body(new BankStatementResponse("Invalid date format. Use yyyy-MM-dd."));
        }

        try {
            // Assuming generateTransactions method returns List<Transaction>
            String fileName = "C:\\Users\\aderi\\Downloads\\HP Downloads\\bank_statement_" + accountNumber + ".pdf";
            bankStatement.generateTransactions(fileName, accountNumber, start, end);
            return ResponseEntity.ok().body(new BankStatementResponse(fileName));
        } catch (DocumentException e) {
            throw new RuntimeException("Bank statement could not generate");
        } catch (IOException e) {
            throw new RuntimeException("Bank statement could not generate");
        }


    }

}
