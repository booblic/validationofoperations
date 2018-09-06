import com.terentev.bank.exception.ValidationCustomerException;
import com.terentev.bank.exception.ValidationLimitException;
import com.terentev.bank.exception.ValidationTransactionException;
import com.terentev.bank.validator.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTest {

    private static Validator validator;

    @Before
    public void initTest() {
        validator = new Validator();
    }

    @After
    public void afterTest() {
        validator = null;
    }

    @Test
    public void validateLimitsNormalTest() throws ValidationLimitException, ValidationCustomerException {
        String limit = "Kazuo,Ishiguro,kazuo@literature.com,200";
        validator.validateLimits(limit);
    }

    @Test(expected = ValidationCustomerException.class)
    public void validateLimitsExceptionTest() throws ValidationLimitException, ValidationCustomerException {
        String limit = "Kazuo,Ishiguro,kazuoliterature.com,200";
        validator.validateLimits(limit);
    }

    @Test(expected = ValidationLimitException.class)
    public void validateLimitsNegativeTest() throws ValidationLimitException, ValidationCustomerException {
        String limit = "Jane,Marple,detective@books.com,-100";
        validator.validateLimits(limit);
    }

    @Test
    public void validateTransactionsNormalTest() throws ValidationTransactionException, ValidationCustomerException {
        String transaction = "Kazuo,Ishiguro,kazuo@literature.com,20,P100";
        validator.validateTransactions(transaction);
    }

    @Test(expected = ValidationCustomerException.class)
    public void validateTransactionsExceptionTest() throws ValidationTransactionException, ValidationCustomerException {
        String transaction = "kazuo,Ishiguro,kazuo@literature.com,20,P100";
        validator.validateTransactions(transaction);
    }

    @Test(expected = ValidationTransactionException.class)
    public void validateTransactionsNegativeTest() throws ValidationTransactionException, ValidationCustomerException {
        String transaction = "Jane,Marple,detective@books.com,-30,P102";
        validator.validateTransactions(transaction);
    }

}
