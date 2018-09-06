import com.terentev.bank.entity.Customer;
import com.terentev.bank.repository.CustomerLimitRepository;
import com.terentev.bank.service.TransactionService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceTest {

    private static CustomerLimitRepository customerLimitRepository;

    private static TransactionService transactionService;

    @Before
    public void initTest() {
        customerLimitRepository = new CustomerLimitRepository();
        List<String> limits = new ArrayList<>();
        limits.add("Kazuo,Ishiguro,kazuo@literature.com,200");
        limits.add("Jane,Marple,detective@books.com,500");
        customerLimitRepository.addLimits(limits);

        transactionService = new TransactionService();
    }

    @After
    public void afterTest() {
        customerLimitRepository = null;
        transactionService = null;
    }

    @Test
    public void sendTransactionsTestFirst() {
        List<String> transactions = new ArrayList<>();
        transactions.add("Kazuo,Ishiguro,kazuo@literature.com,50,P100");

        transactionService.performTransactions(transactions);

        Customer customer = new Customer();
        customer.setFirstName("Kazuo");
        customer.setSecondName("Ishiguro");
        customer.setEmail("kazuo@literature.com");

        Assert.assertEquals(new Integer(150), customerLimitRepository.getLimit(customer));
    }

    @Test
    public void sendTransactionsTestSecond() {
        List<String> transactions = new ArrayList<>();
        transactions.add("Kazuo,Ishiguro,kazuo@literature.com,300,P101");

        transactionService.performTransactions(transactions);

        Customer customer = new Customer();
        customer.setFirstName("Kazuo");
        customer.setSecondName("Ishiguro");
        customer.setEmail("kazuo@literature.com");

        Assert.assertEquals(new Integer(200), customerLimitRepository.getLimit(customer));
    }
}
