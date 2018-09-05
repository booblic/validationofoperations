package com.terentev.bank;

import com.terentev.bank.repository.CustomerLimitRepository;
import com.terentev.bank.service.TransactionService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Module {

    public static void main(String[] args) {

        List<String> limitList = new ArrayList<>();

        List<String> transactionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.csv"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                limitList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomerLimitRepository customerLimitRepository = new CustomerLimitRepository();

        customerLimitRepository.addLimits(limitList);


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("transaction.csv"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                transactionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        TransactionService transactionService = new TransactionService();

        transactionService.sendTransactions(transactionList);

    }
}
