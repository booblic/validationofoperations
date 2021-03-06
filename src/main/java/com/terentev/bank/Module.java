package com.terentev.bank;

import com.terentev.bank.repository.CustomerLimitRepository;
import com.terentev.bank.repository.RejectedTransactionRepository;
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
        Module module = new Module();
        List<String> limitList = module.parseLimits();
        List<String> transactionList = module.parseTransaction();
        module.action(limitList, transactionList);
    }

    /**
     * Method for parse limits.csv
     *
     * @param limitList - list limits (Kazuo,Ishiguro,kazuo@literature.com,500)
     * @param transactionList - list transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     *
     * @return - list rejected transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     */
    public List<String> action(List<String> limitList, List<String> transactionList) {

        CustomerLimitRepository customerLimitRepository = new CustomerLimitRepository();

        TransactionService transactionService = new TransactionService();

        customerLimitRepository.addLimits(limitList);

        transactionService.performTransactions(transactionList);

        RejectedTransactionRepository rejectedTransactionRepository = new RejectedTransactionRepository();

        return rejectedTransactionRepository.getRejectedTransactions();
    }

    /**
     * Method for parse limits.csv
     *
     * @return - list limits (Kazuo,Ishiguro,kazuo@literature.com,500)
     */
    public List<String> parseLimits() {

        List<String> limitList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("src/main/resources/limits.csv"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                limitList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return limitList;
    }

    /**
     * Method for parse transactions.csv
     *
     * @return - list transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     */
    public List<String> parseTransaction() {

        List<String> transactionList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("src/main/resources/transactions.csv"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                transactionList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionList;
    }
}
