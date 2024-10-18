package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.*;

public interface CustomerService {
    BankResponse createAccount(CustomerRequest customerRequest);

    BankResponse getBalanceEnquiry(EnquiryRequest enquiryRequest);

    BankResponse getNameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);

    BankResponse debitAccount(CreditDebitRequest creditDebitRequest);

    BankResponse doTransfer(TransferRequest transferRequest);
}
