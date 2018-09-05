package com.terentev.bank.validator;

import com.terentev.bank.exception.ValidationLimitException;
import com.terentev.bank.exception.ValidationTransactionException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class from validation limits lists and transactions list
 *
 * @author Kirill
 * @version 1.0
 */
public class ValidatorInputList {

    private static final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern namePattern = Pattern.compile("^[A-Z]{1}[^0-9\\W]+[A-Za-z\\-]+[^0-9\\W]$");

    private static final Pattern sumPattern = Pattern.compile("^[\\d]*[\\d]$");

    /**
     * Method for validation of the limit
     *
     * @param limits - list of limit (Kazuo,Ishiguro,kazuo@literature.com,500)
     * @throws ValidationLimitException
     */
    public void validateLimits(List<String> limits) throws ValidationLimitException {

        if ((limits == null) || (limits.size() < 4)) {
            throw new ValidationLimitException();
        }

        Matcher firstNameMatcher = namePattern.matcher(limits.get(0));

        Matcher secondNameMatcher = namePattern.matcher(limits.get(1));

        Matcher emailMatcher = emailPattern.matcher(limits.get(2));

        Matcher sumMatcher = sumPattern.matcher(limits.get(3));

        if ((firstNameMatcher.find() == false) || (secondNameMatcher.find() == false) || (emailMatcher.find() == false) || (sumMatcher.find() == false)) {
            throw new ValidationLimitException("Limit " + limits + " not valid!");
        }
    }

    /**
     * Method for validation of the transactions, getting corresponding limit and calculations and set new limit value
     *
     * @param transactions - list of transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     * @throws ValidationTransactionException
     */
    public void validateTransactions(List<String> transactions) throws ValidationTransactionException {

        if ((transactions == null) || (transactions.size() < 5)) {
            throw new ValidationTransactionException();
        }

        Matcher firstNameMatcher = namePattern.matcher(transactions.get(0));

        Matcher secondNameMatcher = namePattern.matcher(transactions.get(1));

        Matcher emailMatcher = emailPattern.matcher(transactions.get(2));

        Matcher sumMatcher = sumPattern.matcher(transactions.get(3));

        if ((firstNameMatcher.find() == false) || (secondNameMatcher.find() == false) || (emailMatcher.find() == false) || (sumMatcher.find() == false)) {
            throw new ValidationTransactionException("Transaction " + transactions.get(4) + " not valid!");
        }
    }
}
