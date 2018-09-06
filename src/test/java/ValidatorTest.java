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
    public void validateLimitsTestNormal() throws ValidationLimitException {
        String limit = "Kazuo,Ishiguro,kazuo@literature.com,200";
        validator.validateLimits(limit);
    }

    @Test(expected = ValidationLimitException.class)
    public void validateLimitsTestException() throws ValidationLimitException {
        String limit = "Kazuo,Ishiguro,kazuoliterature.com,200";
        validator.validateLimits(limit);
    }

    @Test
    public void validateTransactionsTestNormal() throws ValidationTransactionException {
        String transaction = "Kazuo,Ishiguro,kazuo@literature.com,20,P100";
        validator.validateTransactions(transaction);
    }

    @Test(expected = ValidationTransactionException.class)
    public void validateTransactionsTestException() throws ValidationTransactionException {
        String transaction = "kazuo,Ishiguro,kazuo@literature.com,20,P100";
        validator.validateTransactions(transaction);
    }
}
