package com.terentev.bank.repository;

import com.terentev.bank.entity.Customer;
import com.terentev.bank.exception.ValidationLimitException;
import com.terentev.bank.validator.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.*;

/**
 * Repository  for storing, receiving and changing limits
 *
 * @author Kirill
 * @version 1.0
 */
public class CustomerLimitRepository {

    Logger log = LogManager.getLogger(CustomerLimitRepository.class);

    private Validator validator = new Validator();

    private static Map<Customer, Integer> customerLimit = new HashMap<>();

    public CustomerLimitRepository() {}

    /**
     * Method for validation of the limit and adding it to the repository
     *
     * @param limits - list of limit (Kazuo,Ishiguro,kazuo@literature.com,500)
     */
    public void addLimits(List<String> limits) {

        for (String limit: limits) {

            try {
                validator.validateLimits(limit);
            } catch (ValidationLimitException e) {
                log.error(e);
                return;
            }

            String[] limitElement = limit.split("\\s*,\\s*");

            Customer customer = new Customer();
            customer.setFirstName(limitElement[0]);
            customer.setSecondName(limitElement[1]);
            customer.setEmail(limitElement[2]);

            customerLimit.put(customer, Integer.parseInt(limitElement[3]));
        }
    }

    public Map<Customer, Integer> getCustomerLimit() {
        return customerLimit;
    }

    /**
     * Method for search a limit from customer
     *
     * @param customer - customer object - map key
     */
    public Integer getLimit(Customer customer) { ;
        return customerLimit.get(customer);
    }

    /**
     * Method for set a new limit from customer
     *
     * @param customer - customer object - map key
     * @param limit - limit - map value
     */
    public void setLimits(Customer customer, Integer limit) {
        customerLimit.replace(customer, limit);
    }
}
