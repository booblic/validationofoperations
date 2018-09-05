package com.terentev.bank.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository  for storing, receiving limits rejected transactions
 *
 * @author Kirill
 * @version 1.0
 */
public class RejectedTransactionRepository {

    private static List<List<String>> rejectedTransactions = new ArrayList<>();

    public void addRejectedTransactions(List<String> transactions) {
        rejectedTransactions.add(transactions);
    }

    public List<List<String>> getRejectedTransactions() {
        return rejectedTransactions;
    }
}
