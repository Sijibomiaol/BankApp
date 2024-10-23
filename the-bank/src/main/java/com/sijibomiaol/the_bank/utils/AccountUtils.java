package com.sijibomiaol.the_bank.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Random;

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

    public static  final String TARGET_ACCOUNT_FAIL_CODE = "009";
    public static  final String TARGET_ACCOUNT_FAIL_MESSAGE = "Customer Account not found";

    public static  final String TRANSER_SUCCESS_CODE = "010";
    public static final String TRANSER_SUCCESS_MESSAGE = "Account has been Successfully Transfer ";

    public static final String LOGIN_FAILED_CODE="011";
    public static final String LOGIN_FAILED_MESSAGE="Invalid Login Credentials";

    public static final String OTP_SUCCESS_CODE="020";
    public static final String OTP_SUCCESS_MESSAGE="Account has been Successfully OTP ";

    public static final String VALIDATE_FAIL_CODE="022";
    public static final String VALIDATE_FAIL_MESSAGE="You have not sent an otp";

    public static final String EXPIRED_OTP_CODE="023";
    public static final String EXPIRED_OTP_MESSAGE="You have expired otp";

    public static final String INVALID_OTP_CODE= "024";
    public static final String INCALID_OTP_MESSAGE = "Invalid otp";

    public static final String VALIDATE_OTP_CODE="025";
    public static final String VALIDATE_OTP_MESSAGE= "Otp Validated";





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

    public static  String generateOtp(){
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        int count = 0;
        while(count<4){
            otp.append(random.nextInt(10));
            ++count;
        }
        return otp.toString();
    }

    public static void  main(String[] args) {
        System.out.println(generateOtp());
    }
}
