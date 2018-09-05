package com.terentev.bank;

import com.terentev.bank.repository.CustomerLimitRepository;
import com.terentev.bank.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class Module {

    public static void main(String[] args) {

        CustomerLimitRepository customerLimitRepository = new CustomerLimitRepository();

        List<String> limit1 = new ArrayList<>();
        limit1.add("Kazuo");
        limit1.add("Ishiguro");
        limit1.add("kazuo@literature.com");
        limit1.add("200");

        customerLimitRepository.addLimits(limit1);

        List<String> limit2 = new ArrayList<>();
        limit2.add("Jane");
        limit2.add("Marple");
        limit2.add("detective@books.com");
        limit2.add("500");

        customerLimitRepository.addLimits(limit2);

        TransactionService transactionService = new TransactionService();

        List<String> transaction1 = new ArrayList<>();
        transaction1.add("Kazuo");
        transaction1.add("Ishiguro");
        transaction1.add("kazuo@literature.com");
        transaction1.add("20");
        transaction1.add("P100");

        transactionService.doTransaction(transaction1);

        List<String> transaction2 = new ArrayList<>();
        transaction2.add("Kazuo");
        transaction2.add("Ishiguro");
        transaction2.add("kazuo@literature.com");
        transaction2.add("100");
        transaction2.add("P101");

        transactionService.doTransaction(transaction2);

        List<String> transaction3 = new ArrayList<>();
        transaction3.add("Jane");
        transaction3.add("Marple");
        transaction3.add("detective@books.com");
        transaction3.add("501");
        transaction3.add("P102");

        transactionService.doTransaction(transaction3);

        List<String> transaction4 = new ArrayList<>();
        transaction4.add("Kazuo");
        transaction4.add("Ishiguro");
        transaction4.add("kazuo@literature.com");
        transaction4.add("150");
        transaction4.add("P103");

        transactionService.doTransaction(transaction4);

        List<String> transaction5 = new ArrayList<>();
        transaction5.add("Jane");
        transaction5.add("Marple");
        transaction5.add("detective@books.com");
        transaction5.add("200");
        transaction5.add("P104");

        transactionService.doTransaction(transaction5);

        List<String> transaction6 = new ArrayList<>();
        transaction6.add("Kazuo");
        transaction6.add("Ishiguro");
        transaction6.add("kazuo@literature.com");
        transaction6.add("80");
        transaction6.add("P105");

        transactionService.doTransaction(transaction6);
    }
}
