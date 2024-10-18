package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.BankResponse;
import com.sijibomiaol.the_bank.dto.CreditDebitRequest;
import com.sijibomiaol.the_bank.dto.CustomerRequest;
import com.sijibomiaol.the_bank.dto.EnquiryRequest;

public interface CustomerService {
    BankResponse createAccount(CustomerRequest customerRequest);

    BankResponse getBalanceEnquiry(EnquiryRequest enquiryRequest);

    BankResponse getNameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);

    BankResponse debitAccount(CreditDebitRequest creditDebitRequest);
}
