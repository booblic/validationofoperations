package com.terentev.bank.repository;

import com.terentev.bank.exception.CustomerNotExistException;
import com.terentev.bank.exception.ValidationLimitException;
import com.terentev.bank.validator.ValidatorInputList;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Repository  for storing, receiving and changing limits
 *
 * @author Kirill
 * @version 1.0
 */
public class CustomerLimitRepository {

    Logger log = LogManager.getLogger(CustomerLimitRepository.class);

    private ValidatorInputList validatorInputList = new ValidatorInputList();

    private static Set<List<String>> setLimits = new HashSet<>();

    public CustomerLimitRepository() {}

    /**
     * Method for validation of the limit and adding it to the repository
     *
     * @param limits - list of limit (Kazuo,Ishiguro,kazuo@literature.com,500)
     */
    public void addLimits(List<String> limits) {
        try {
            validatorInputList.validateLimits(limits);
            setLimits.add(limits);
            log.debug("Limit: " + limits + " add successfully");
        } catch (ValidationLimitException e) {
            log.error(e);
            return;
        }
    }

    /**
     * Method for search for a limit by first name, last name and email
     *
     * @param transactions - list of transactions (Kazuo,Ishiguro,kazuo@literature.com,100,P123)
     * @throws CustomerNotExistException
     */
    public List<String> getLimits(List<String> transactions) throws CustomerNotExistException {

        List<String> limits = null;

        for (List<String> limit: setLimits) {

            if((limit.get(0).compareTo(transactions.get(0))==0) &&
                    (limit.get(1).compareTo(transactions.get(1))==0) &&
                    (limit.get(2).compareTo(transactions.get(2))==0)) {

                limits = limit;
                log.debug("Limit from customer: " + transactions.get(0) + ", " +  transactions.get(1) + ", " +  transactions.get(2) + " is found");
            }
        }

        if (limits == null) {
            throw new CustomerNotExistException("Limit from customer: " + transactions.get(0) + ", " + transactions.get(1) + ", " + transactions.get(2) + " not exist!");
        }

        return limits;
    }

    /**
     * Method for search for a limit by first name, last name and email
     *
     * @param limits - limit which needs to be changed
     * @param newLimit - new limit value
     */
    public void changeLimits(List<String> limits, String newLimit) {

        limits.set(3, newLimit);
        log.debug("Limit successfully changed " + limits.get(3));
    }

    public Set<List<String>> getSetLimits() {
        return setLimits;
    }
}
