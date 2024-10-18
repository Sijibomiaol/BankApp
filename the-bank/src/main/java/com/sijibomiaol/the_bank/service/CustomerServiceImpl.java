package com.sijibomiaol.the_bank.service;


import com.sijibomiaol.the_bank.dto.*;
import com.sijibomiaol.the_bank.entity.Customer;
import com.sijibomiaol.the_bank.repository.CustomerRepository;
import com.sijibomiaol.the_bank.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailService emailService;

    @Override
    public BankResponse createAccount(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        Optional<Customer> existCustomer = customerRepository.findByAccountNumber(customer.getAccountNumber());
        if (existCustomer.isPresent()) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)

                    .build();
        }
        if (!isValidCustomerRequest(customerRequest)) {
            return BankResponse.builder()
                    .responseMessage("All fields are required and must be valid")

                    .build();
        }

        String accountNumber = AccountUtils.generateAccountId();
        BigDecimal intialBalance = BigDecimal.ZERO;
        String accountName = customerRequest.getFirstName() + " "
                + customerRequest.getOtherName() + " "
                + customerRequest.getLastName();

        Customer newcustomer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .accountNumber(accountNumber)
                .otherName(customerRequest.getOtherName())
                .phone(customerRequest.getPhone())
                .email(customerRequest.getEmail())
                .address(customerRequest.getAddress())
                .stateofOrigin(customerRequest.getStateofOrigin())
                .age(customerRequest.getAge())
                .status("Active")
                .accountBalance(intialBalance)
                .build();
        customerRepository.save(newcustomer);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(newcustomer.getEmail())
                .subject("Account Creation")
                .body("Hello " + accountName + ",\n\nCongratulation! Your account has been created! \n\n" +
                        "Here is your account details:\n" +
                        "Account Name:" + accountName + "\n"+
                        "Account Number:" + accountNumber+"\n\n" +
                        "Thank you for bank with us!")
                .build();
        emailService.sendEMailAlert(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .info(AccountInfo.builder()
                        .accountNumber(accountNumber)
                        .accountName(accountName)
                        .accountBalance(intialBalance)
                        .build())
                .build();
    }

    private boolean isValidCustomerRequest(CustomerRequest customerRequest) {
        return StringUtils.hasText(customerRequest.getFirstName()) &&
                StringUtils.hasText(customerRequest.getLastName()) &&
                StringUtils.hasText(customerRequest.getOtherName()) &&
                StringUtils.hasText(customerRequest.getAddress()) &&
                StringUtils.hasText(customerRequest.getPhone()) &&
                StringUtils.hasText(customerRequest.getStateofOrigin()) &&
                StringUtils.hasText(customerRequest.getEmail()) &&
                customerRequest.getAge() > 18;

    }
    @Override
    public BankResponse getBalanceEnquiry(EnquiryRequest enquiryRequest) {
        Optional<Customer> customerOptional = customerRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        if (customerOptional.isEmpty()) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_FOUND)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_FOUND_MESSAGE)
                    .info(null)
                    .build();
        }
        Customer existingCustomer = customerOptional.get();
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .info(AccountInfo.builder()
                        .accountName(existingCustomer.getFirstName() + " " + existingCustomer.getOtherName() + " " + existingCustomer.getLastName())
                        .accountBalance(existingCustomer.getAccountBalance())
                        .accountNumber(existingCustomer.getAccountNumber())
                        .build())
                .build();


    }
    @Override
    public BankResponse getNameEnquiry(EnquiryRequest enquiryRequest) {
        Optional<Customer> existsedCustomer = customerRepository.findByAccountNumber(enquiryRequest.getAccountNumber());

        if (existsedCustomer.isEmpty()) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_FOUND)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_FOUND_MESSAGE)
                    .info(null)
                    .build();
        }
        Customer existingCustomer = existsedCustomer.get();
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .info(AccountInfo.builder()
                .accountName(existingCustomer.getFirstName() + " " + existingCustomer.getOtherName() + " " + existingCustomer.getLastName())
                        .build())
                .build();

    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditDebitRequest) {
        Optional<Customer> optionalCustomerToCredit = customerRepository.findByAccountNumber(creditDebitRequest.getAccountNumber());

        if (optionalCustomerToCredit.isEmpty()) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_FOUND)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_FOUND_MESSAGE)
                    .info(null)
                    .build();
        }

        Customer customerToCredit =optionalCustomerToCredit.get();
        customerToCredit.setAccountBalance(customerToCredit.getAccountBalance().add(creditDebitRequest.getAmount()));
        customerRepository.save(customerToCredit);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(customerToCredit.getEmail())
                .subject("Credit Alert")
                .body("Hello " + customerToCredit.getFirstName() + ",\n\n" +
                        "Your account has been credited with an amount of: " + creditDebitRequest.getAmount() + "\n" +
                        "New balance: " + customerToCredit.getAccountBalance() + "\n\n" +
                        "Thank you for banking with us.\n" +
                        "Best regards,\n" +
                        "The Bank Team")
                .build();

                emailService.sendEMailAlert(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.CREDITED_SUCCESS_CODE)
                .responseMessage(AccountUtils.CREDITED_SUCESS_MESSAGE)
                .info(AccountInfo.builder()
                        .accountName(customerToCredit.getFirstName() + " " + customerToCredit.getLastName())
                        .accountNumber(creditDebitRequest.getAccountNumber())
                        .accountBalance(customerToCredit.getAccountBalance())
                        .build())
                .build();

    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest creditDebitRequest) {
        Optional<Customer> optionalCustomerToDebit = customerRepository.findByAccountNumber(creditDebitRequest.getAccountNumber());

        if (optionalCustomerToDebit.isEmpty()) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_FOUND)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_FOUND_MESSAGE)
                    .info(null)
                    .build();
        }
        Customer customerToDebit = optionalCustomerToDebit.get();
        BigDecimal currentBalance = customerToDebit.getAccountBalance();
        BigDecimal debitAmount = creditDebitRequest.getAmount();
        if (currentBalance.compareTo(debitAmount)<0){
            return BankResponse.builder()
                    .responseCode(AccountUtils.DEBIT_FAIL_CODE)
                    .responseMessage(AccountUtils.DEBIT_FAIL_MESSAGE)
                    .info(null)
                    .build();
        }


            BigDecimal updatedBalance = currentBalance.subtract(debitAmount);
            customerToDebit.setAccountBalance(updatedBalance);
            customerRepository.save(customerToDebit);
            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(customerToDebit.getEmail())
                    .subject("Debit Alert")
                    .body("Hello " + customerToDebit.getFirstName() + ",\n\n" +
                            "Your account has been debited with an amount of: " + debitAmount + "\n" +
                            "New balance: " + updatedBalance + "\n\n" +
                            "Thank you for banking with us.\n" +
                            "Best regards,\n" +
                            "The Bank Team")
                    .build();

            emailService.sendEMailAlert(emailDetails);

                return BankResponse.builder()
                        .responseCode(AccountUtils.DEBIT_SUCCESS_CODE)
                        .responseMessage(AccountUtils.DEBIT_SUCCESS_MESSAGE + debitAmount)
                        .info(AccountInfo.builder()
                                .accountName(customerToDebit.getFirstName() + " " + customerToDebit.getLastName())
                                .accountNumber(creditDebitRequest.getAccountNumber())
                                .accountBalance(customerToDebit.getAccountBalance())
                                .build())
                        .build();

        }





}
