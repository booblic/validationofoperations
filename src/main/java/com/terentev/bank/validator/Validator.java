package com.terentev.bank.validator;

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
    public void validateLimits(String limit) throws ValidationLimitException {

        String[] limitElements = limit.split(",");

        Matcher firstNameMatcher = namePattern.matcher(limitElements[0]);

        Matcher secondNameMatcher = namePattern.matcher(limitElements[1]);

        Matcher emailMatcher = emailPattern.matcher(limitElements[2]);

        Matcher sumMatcher = sumPattern.matcher(limitElements[3]);

        if (!firstNameMatcher.find() || !secondNameMatcher.find() || !emailMatcher.find() || !sumMatcher.find()) {
            throw new ValidationLimitException("Limit " + Arrays.toString(limitElements) + " not valid!");
        }
    }

    /**
     * Method for validation of the transactions, getting corresponding limit and calculations and set new limit value
     *
     * @param transaction - string of transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     * @throws ValidationTransactionException
     */
    public void validateTransactions(String transaction) throws ValidationTransactionException {

        String[] transactionElements = transaction.split(",");

        Matcher firstNameMatcher = namePattern.matcher(transactionElements[0]);

        Matcher secondNameMatcher = namePattern.matcher(transactionElements[1]);

        Matcher emailMatcher = emailPattern.matcher(transactionElements[2]);

        Matcher sumMatcher = sumPattern.matcher(transactionElements[3]);

        if (!firstNameMatcher.find() || !secondNameMatcher.find() || !emailMatcher.find() || !sumMatcher.find()) {
            throw new ValidationTransactionException("Transaction " + Arrays.toString(transactionElements) + " not valid!");
        }
    }
}
