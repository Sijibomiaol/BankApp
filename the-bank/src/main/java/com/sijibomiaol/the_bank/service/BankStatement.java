package com.sijibomiaol.the_bank.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sijibomiaol.the_bank.entity.Customer;
import com.sijibomiaol.the_bank.entity.Transaction;
import com.sijibomiaol.the_bank.repository.CustomerRepository;
import com.sijibomiaol.the_bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
@RequiredArgsConstructor
public class BankStatement {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public void generateTransactions(String fileName, String accountNumber, LocalDate startDate, LocalDate endDate) throws DocumentException, IOException {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<Customer> customer = customerRepository.findByAccountNumber(accountNumber);

        if(!customer.isPresent()) {
            throw new RuntimeException("Customer not found " + accountNumber);
        }
        Customer existingCustomer = customer.get();
        String accountName = existingCustomer.getFirstName()
                + " " + existingCustomer.getOtherName()
                +" " + existingCustomer.getLastName();

//        List<Transaction> transactionList    = transactionRepository
//                .findAll().stream()
//                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
//                .filter(transaction -> !transaction.getTransactionDate().isBefore(startDateTime)
//                        && !transaction.getTransactionDate().isAfter(endDateTime))
//                .toList();
        List<Transaction>transactionList = transactionRepository.findByAccountNumberAndTransactionDateBetween(accountNumber,
                startDateTime, endDateTime);

        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        log.info("Setting size of document");
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {



        PdfWriter.getInstance(document, outputStream);
        document.open();

        com.itextpdf.text.Font bankTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.WHITE);
        com.itextpdf.text.Font bankAddressFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.WHITE);

        PdfPTable bankInfoTable = new PdfPTable(4);
        PdfPCell bankName = new PdfPCell(new Phrase("The Sijibomi Bank", bankTitleFont));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.BLUE);
        bankName.setPadding(10f);

        PdfPCell bankAddress = new PdfPCell(new Phrase("19, Ijaye, Lagos Nigeria", bankAddressFont));
        bankAddress.setBorder(0);
        bankAddress.setBackgroundColor(BaseColor.BLUE);
        bankAddress.setPadding(5f);

        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAddress);
        document.add(bankInfoTable);

        PdfPTable customerInfoTable = new PdfPTable(2);
        customerInfoTable.setWidthPercentage(100);
        customerInfoTable.setSpacingBefore(10f);
        customerInfoTable.setSpacingBefore(10f);

        PdfPCell customerName = new PdfPCell(new Phrase("Customer Name: " + accountName ));
        PdfPCell customerAddress = new PdfPCell(new Phrase("Address: " + accountNumber ));
        PdfPCell startDateCell = new PdfPCell(new Phrase("Start Date" + startDateTime.format(formatter)));
        PdfPCell endDateCell = new PdfPCell(new Phrase("End Date" + endDateTime.format(formatter)));

        customerName.setBorder(0);
        customerAddress.setBorder(0);
        startDateCell.setBorder(0);
        endDateCell.setBorder(0);

        customerInfoTable.addCell(customerName);
        customerInfoTable.addCell(customerAddress);
        customerInfoTable.addCell(startDateCell);
        customerInfoTable.addCell(endDateCell);

        document.add(customerInfoTable);

        PdfPTable transationListTable = new PdfPTable(3);
        transationListTable.setWidthPercentage(100);
        transationListTable.setSpacingBefore(10f);
        transationListTable.setSpacingAfter(10f);

        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        PdfPCell dateHeader = new PdfPCell(new Phrase("Date", tableHeaderFont));
        PdfPCell AmountHeader = new PdfPCell(new Phrase("Amount", tableHeaderFont));
        PdfPCell typeHeader = new PdfPCell(new Phrase("Type", tableHeaderFont));

        transationListTable.addCell(dateHeader);
        transationListTable.addCell(AmountHeader);
        transationListTable.addCell(typeHeader);

        for (Transaction transaction : transactionList) {
            transationListTable.addCell(new Phrase(transaction.getTransactionDate().toString()));
            transationListTable.addCell(new Phrase(transaction.getAmount().toString()));
            transationListTable.addCell(new Phrase(transaction.getTransactionType().toString()));

        }
        document.add(transationListTable);
        document.close();

    }
        catch (Exception e) {
        log.error("Error generating bank Statement");}
    }
}



//        List<Transaction> transactionList    = transactionRepository
//                .findAll().stream()
//                .filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
//                .filter(transaction -> !transaction.getTransactionDate().isBefore(startDateTime)
//                        && !transaction.getTransactionDate().isAfter(endDateTime))
//                .toList();
//
//        return transactionList;
//    }
//
//    public void designStatement(List<Transaction> transactions) throws FileNotFoundException {
//        Rectangle statementSize = new Rectangle(PageSize.A4);
//        Document document = new Document(statementSize);
//        log.info("setting size of document");
//        OutputStream outputStream = new FileOutputStream(FILE);
//        PdfWriter.getInstance(document, outputStream);
//        document.open();
//
//        PdfPTable bankInfoTable = new PdfPTable(1);
//        PdfPCell bankName = new PdfPCell(new Phrase("The Sijibomi Bank"));
//        bankName.setBorder(0);
//        bankName.setBackgroundColor(BaseColor.BLUE);
//        bankName.setPadding(20f);
//
//
//        PdfPCell bankAddrress = new PdfPCell(new Phrase("19, Ijaye, Lagos Nigeria"));
//        bankAddrress.setBorder(0);
//        bankAddrress.setBackgroundColor(BaseColor.BLUE);
//        bankInfoTable.addCell(bankName);
//        bankInfoTable.addCell(bankAddrress);
//
////        PdfPTable statement = new PdfPTable(2);
////        PdfPCell customerInfo = new PdfPCell(new Phrase("Start Date :" + startDateTime ));
////        statement.setWidthPercentage(100);
//
//        PdfPCell customerName = new PdfPCell(new Phrase("Account Holder: " + accountHolderName));
//        PdfPCell customerAccountNumber = new PdfPCell(new Phrase("Account Number: " + accountNumber));
//        PdfPCell startDateCell = new PdfPCell(new Phrase("Start Date: " + startDateTime.toLocalDate().toString()));
//        PdfPCell endDateCell = new PdfPCell(new Phrase("End Date: " + endDateTime.toLocalDate().toString()));
//
//    }
//}
