package com.terentev.bank.service;

import com.terentev.bank.entity.Customer;
import com.terentev.bank.exception.ValidationTransactionException;
import com.terentev.bank.repository.CustomerLimitRepository;
import com.terentev.bank.repository.RejectedTransactionRepository;
import com.terentev.bank.validator.Validator;
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

    private Validator validator = new Validator();

    private CustomerLimitRepository customerLimitRepository = new CustomerLimitRepository();

    private RejectedTransactionRepository rejectedTransactionRepository = new RejectedTransactionRepository();

    /**
     * Method for validation of the transactions, getting corresponding limit and calculations and set new limit value
     *
     * @param transactions - list of transactions (Kazuo,Ishiguro,kazuo@literature.com,20,P100 Kazuo,Ishiguro,kazuo@literature.com,100,P101 ...)
     */
    public void sendTransactions(List<String> transactions) {

        for (String transaction: transactions) {

            try {
                validator.validateTransactions(transaction);
            } catch (ValidationTransactionException e) {
                log.error(e);
                return;
            }

            String[] elementTransaction = transaction.split("\\s*,\\s*");

            Customer customer = new Customer();
            customer.setFirstName(elementTransaction[0]);
            customer.setSecondName(elementTransaction[1]);
            customer.setEmail(elementTransaction[2]);

            Integer limit = customerLimitRepository.getLimit(customer);

            if (limit == null) {
                try {
                    throw new ClassNotFoundException("Customer: " + customer.toString() + " not found!");
                } catch (ClassNotFoundException e) {
                    log.error(e);
                    return;
                }
            }

            Integer transactionSum = Integer.parseInt(elementTransaction[3]);

            if (limit < transactionSum) {
                log.error("rejected=" + elementTransaction[4]);
                rejectedTransactionRepository.addRejectedTransactions(transaction);
                System.out.println(transaction);
            } else {
                Integer newLimit = limit - transactionSum;
                customerLimitRepository.setLimits(customer, newLimit);
                log.info("approved=" + elementTransaction[4]);
            }
        }
    }
}
