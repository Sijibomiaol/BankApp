package com.sijibomiaol.the_bank.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
public class AccountUtils {
    public AccountUtils() {
    }

    public  static  final String ACCOUNT_EXISTS_CODE = "001";
    public  static  final  String ACCOUNT_EXISTS_MESSAGE = "Account already exists";

    public static  final String ACCOUNT_CREATION_SUCCESS= "002";
    public static  final String ACCOUNT_CREATION_MESSAGE = "Account creation success";

    public static  final String ACCOUNT_NOT_FOUND = "003";
    public static  final String ACCOUNT_NOT_FOUND_MESSAGE = "Customer account  not found";

    public static  final String ACCOUNT_FOUND_CODE = "004";
    public static  final String ACCOUNT_FOUND_MESSAGE = "Customer Account found";

    public static final String CREDITED_SUCCESS_CODE = "005";
    public static final String CREDITED_SUCESS_MESSAGE = "Account has been  Successful Credited";

    public static final String CREDITED_FAIL_CODE = "006";
    public static final String CREDITED_FAIL_MESSAGE = "Account  Credit  Failed";

    public static final String DEBIT_SUCCESS_CODE = "007";
    public static final String DEBIT_SUCCESS_MESSAGE = "Account has been Successfully Debited ";

    public static final String DEBIT_FAIL_CODE = "008";
    public static final String DEBIT_FAIL_MESSAGE = "Insufficient Balance ";





    public static String generateAccountId(){
        Year currentYear = Year.now();

        int min = 100000;

        int max = 999999;

        double randNumber = (int)Math.floor(Math.random() *(max - min +1)+ min);

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);

        StringBuilder accountNumber = new StringBuilder();
        accountNumber.append(year).append(randomNumber);

        return accountNumber.toString();


    }
}
