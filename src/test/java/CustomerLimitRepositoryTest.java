import com.terentev.bank.entity.Customer;
import com.terentev.bank.exception.CustomerNotExistException;
import com.terentev.bank.repository.CustomerLimitRepository;
import org.junit.*;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;

public class CustomerLimitRepositoryTest {

    private static CustomerLimitRepository customerLimitRepository;

    @BeforeClass
    public static void initTest() {
        customerLimitRepository = new CustomerLimitRepository();
    }

    @AfterClass
    public static void afterTest() {
        customerLimitRepository = null;
    }

    @Test
    public void addLimitsTest() {
        List<String> limits = new ArrayList<>();
        limits.add("Kazuo,Ishiguro,kazuo@literature.com,200");
        limits.add("Jane,Marple,detective@books.com,500");
        customerLimitRepository.addLimits(limits);

        Assert.assertEquals(2, customerLimitRepository.getCustomerLimit().size());
    }

    @Test
    public void getLimitTest() throws CustomerNotExistException {
        Customer customer = new Customer();
        customer.setFirstName("Kazuo");
        customer.setSecondName("Ishiguro");
        customer.setEmail("kazuo@literature.com");

        Assert.assertEquals(new Integer(200), customerLimitRepository.getLimit(customer));
    }

    @Test
    public void changeLimitsTest() {
        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setSecondName("Marple");
        customer.setEmail("detective@books.com");

        customerLimitRepository.setLimits(customer, 100);

        Assert.assertEquals(new Integer(100), customerLimitRepository.getLimit(customer));
    }
}
