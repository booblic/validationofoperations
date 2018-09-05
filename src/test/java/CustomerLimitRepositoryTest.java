import com.terentev.bank.exception.CustomerNotExistException;
import com.terentev.bank.repository.CustomerLimitRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomerLimitRepositoryTest {

    private static CustomerLimitRepository customerLimitRepository;

    private static List<String> limit1 = new ArrayList<>();

    private static List<String> limit2 = new ArrayList<>();

    @Before
    public void initTest() {
        customerLimitRepository = new CustomerLimitRepository();

        limit1.add("Kazuo");
        limit1.add("Ishiguro");
        limit1.add("kazuo@literature.com");
        limit1.add("200");

        limit2.add("Jane");
        limit2.add("Marple");
        limit2.add("detective@books.com");
        limit2.add("500");
    }

    @After
    public void afterTest() {
        customerLimitRepository = null;
    }

    @Test
    public void addLimitsTest() {

        customerLimitRepository.addLimits(limit1);

        customerLimitRepository.addLimits(limit2);

        Assert.assertEquals(2, customerLimitRepository.getSetLimits().size());
    }

    @Test
    public void getLimitsTest1() throws CustomerNotExistException {

        List<String> transaction1 = new ArrayList<>();
        transaction1.add("Kazuo");
        transaction1.add("Ishiguro");
        transaction1.add("kazuo@literature.com");
        transaction1.add("20");
        transaction1.add("P100");

        Assert.assertEquals(limit1, customerLimitRepository.getLimits(transaction1));
    }

    @Test
    public void getLimitsTest2() throws CustomerNotExistException {

        List<String> transaction2 = new ArrayList<>();
        transaction2.add("Kazuo");
        transaction2.add("Ishiguro");
        transaction2.add("kazuo@literature.com");
        transaction2.add("100");
        transaction2.add("P101");

        Assert.assertEquals(limit1, customerLimitRepository.getLimits(transaction2));
    }

    @Test
    public void getLimitsTest3() throws CustomerNotExistException {

        List<String> transaction3 = new ArrayList<>();
        transaction3.add("Jane");
        transaction3.add("Marple");
        transaction3.add("detective@books.com");
        transaction3.add("501");
        transaction3.add("P102");

        Assert.assertEquals(limit2, customerLimitRepository.getLimits(transaction3));
    }

    @Test
    public void getLimitsTest4() throws CustomerNotExistException {

        List<String> transaction4 = new ArrayList<>();
        transaction4.add("Kazuo");
        transaction4.add("Ishiguro");
        transaction4.add("kazuo@literature.com");
        transaction4.add("150");
        transaction4.add("P103");

        Assert.assertEquals(limit1, customerLimitRepository.getLimits(transaction4));
    }

    @Test
    public void getLimitsTest5() throws CustomerNotExistException {

        List<String> transaction5 = new ArrayList<>();
        transaction5.add("Jane");
        transaction5.add("Marple");
        transaction5.add("detective@books.com");
        transaction5.add("200");
        transaction5.add("P104");

        Assert.assertEquals(limit2, customerLimitRepository.getLimits(transaction5));
    }

    @Test(expected = CustomerNotExistException.class)
    public void getLimitsTest6() throws CustomerNotExistException {

        List<String> transaction6 = new ArrayList<>();
        transaction6.add("Kazuo");
        transaction6.add("Ishiguro");
        transaction6.add("kazuo@literature.com");
        transaction6.add("80");
        transaction6.add("P105");

        Assert.assertEquals(limit1, customerLimitRepository.getLimits(transaction6));
    }

    @Test
    public void changeLimitsTest() throws CustomerNotExistException {

        List<String> transaction1 = new ArrayList<>();
        transaction1.add("Kazuo");
        transaction1.add("Ishiguro");
        transaction1.add("kazuo@literature.com");
        transaction1.add("20");
        transaction1.add("P100");

        List<String> limit = customerLimitRepository.getLimits(transaction1);

        customerLimitRepository.changeLimits(limit, "180");

        Assert.assertEquals("180", limit.get(3));
    }
}
