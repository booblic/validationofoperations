package com.terentev.bank.service;

import com.terentev.bank.exception.CustomerNotExistException;
import com.terentev.bank.exception.ValidationTransactionException;
import com.terentev.bank.repository.CustomerLimitRepository;
import com.terentev.bank.repository.RejectedTransactionRepository;
import com.terentev.bank.validator.ValidatorInputList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * A service that implements business logic for a transactions
 *
 * @author Kirill
 * @version 1.0
 */
public class TransactionService {

    Logger log = LogManager.getLogger(TransactionService.class);

    private ValidatorInputList validatorInputList = new ValidatorInputList();

    private CustomerLimitRepository customerLimitRepository = new CustomerLimitRepository();

    private RejectedTransactionRepository rejectedTransactionRepository = new RejectedTransactionRepository();

    /**
     * Method for validation of the transactions, getting corresponding limit and calculations and set new limit value
     *
     * @param transactions - list of transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     */
    public void doTransaction(List<String> transactions) {

        try {
            validatorInputList.validateTransactions(transactions);
        } catch (ValidationTransactionException e) {
            log.error(e);
            return;
        }

        List<String> limits = null;
        try {
            limits = customerLimitRepository.getLimits(transactions);
        } catch (CustomerNotExistException e) {
            log.error(e);
            return;
        }

        Integer changeSum = Integer.parseInt(transactions.get(3));

        Integer limitSum = Integer.parseInt(limits.get(3));

        if (changeSum <= limitSum) {

            Integer newLimit = limitSum - changeSum;
            customerLimitRepository.changeLimits(limits, newLimit.toString());
            log.info("approved=" + transactions.get(4));

        } else {

            rejectedTransactionRepository.addRejectedTransactions(transactions);
            log.debug("Transactions: " + transactions + " add in rejected Transactions Repository");
            log.info("rejected=" + transactions.get(4));
            System.out.println(transactions);
        }
    }
}
