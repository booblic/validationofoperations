package com.terentev.bank.validator;

import com.terentev.bank.exception.ValidationCustomerException;
import com.terentev.bank.exception.ValidationLimitException;
import com.terentev.bank.exception.ValidationTransactionException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class from validation limits lists and transactions list
 *
 * @author Kirill
 * @version 1.0
 */
public class Validator {

    private static final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern namePattern = Pattern.compile("^[A-Z]{1}[^0-9\\W]+[A-Za-z\\-]+[^0-9\\W]$");

    private static final Pattern sumPattern = Pattern.compile("^[\\d]*[\\d]$");

    /**
     * Method for validation of the limit
     *
     * @param limit - string of limit (Kazuo,Ishiguro,kazuo@literature.com,500)
     * @throws ValidationLimitException
     */
    public void validateLimits(String limit) throws ValidationLimitException, ValidationCustomerException {

        String[] limitElements = limit.split(",");

        validateCustomer(limitElements[0], limitElements[1], limitElements[2]);

        Matcher sumMatcher = sumPattern.matcher(limitElements[3]);

        if (!sumMatcher.find()) {
            throw new ValidationLimitException("Limit " + limitElements[3] + " not valid!");
        }
    }

    /**
     * Method for validation of the transactions, getting corresponding limit and calculations and set new limit value
     *
     * @param transaction - string of transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     * @throws ValidationTransactionException
     */
    public void validateTransactions(String transaction) throws ValidationTransactionException, ValidationCustomerException {

        String[] transactionElements = transaction.split(",");

        validateCustomer(transactionElements[0], transactionElements[1], transactionElements[2]);

        Matcher sumMatcher = sumPattern.matcher(transactionElements[3]);

        if (!sumMatcher.find()) {
            throw new ValidationTransactionException("Transaction sum " + transactionElements[3] + " not valid!");
        }
    }

    private void validateCustomer(String firstName, String lastName, String email) throws ValidationCustomerException {

        Matcher firstNameMatcher = namePattern.matcher(firstName);

        Matcher secondNameMatcher = namePattern.matcher(lastName);

        Matcher emailMatcher = emailPattern.matcher(email);

        if (!firstNameMatcher.find() || !secondNameMatcher.find() || !emailMatcher.find()) {
            throw new ValidationCustomerException("Customer data " + firstName + ", " + lastName + ", " + email + " not valid!");
        }
    }
}
