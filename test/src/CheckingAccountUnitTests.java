import com.conoreby.projects.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by conoreby on 6/4/17.
 */
public class CheckingAccountUnitTests {

    private CheckingAccount account;

    private static final BigDecimal INITIAL_DEPOSIT = new BigDecimal("500");

    @Before
    public void setupAccount() {
        this.account = new CheckingAccount(INITIAL_DEPOSIT);
    }

    @Test
    public void testAccountBalance() {
        BigDecimal balance = account.getBalance();
        assertEquals(balance, INITIAL_DEPOSIT);
    }

    /**
     * Test that you can't withdraw more than the account balance.
     */
    @Test(expected = OverdraftException.class)
    public void testOverdraft() throws OverdraftException, NegativeWithdrawalException {
        BigDecimal withdrawalAmount = INITIAL_DEPOSIT.add(BigDecimal.ONE);
        this.account.withdraw(withdrawalAmount);
        BigDecimal balance = this.account.getBalance();
        assertEquals(balance, INITIAL_DEPOSIT);
    }

    @Test
    public void testDepositDollars() throws NegativeDepositException {
       BigDecimal depositAmount = new BigDecimal("100");
       this.account.deposit(depositAmount);

       BigDecimal expectedNewBalance = new BigDecimal("600");
       BigDecimal newBalance = this.account.getBalance();

       assertEquals(expectedNewBalance, newBalance);
    }

    @Test
    public void testWithdrawDollars() {
        BigDecimal withdrawalAmount = new BigDecimal("100");
        try {
            this.account.withdraw(withdrawalAmount);
        } catch (NegativeWithdrawalException | OverdraftException e) {
            fail("Unexpected Exception was thrown");
        }

        BigDecimal expectedNewBalance = new BigDecimal("400");
        BigDecimal newBalance = this.account.getBalance();

        assertEquals(expectedNewBalance, newBalance);

    }

    @Test
    public void testDepositWithCents() {
        BigDecimal depositAmount = new BigDecimal("100.99");
        try {
            this.account.deposit(depositAmount);
        } catch (NegativeDepositException e) {
            fail("Unexpected Exception was thrown");
        }

        BigDecimal expectedNewBalance = new BigDecimal("600.99");
        BigDecimal newBalance = this.account.getBalance();

        assertEquals(expectedNewBalance, newBalance);

    }

    @Test
    public void testWithdrawalWithCents() {

        BigDecimal withdrawalAmount = new BigDecimal("100.99");
        try {
            this.account.withdraw(withdrawalAmount);
        } catch (NegativeWithdrawalException | OverdraftException e) {
            fail("Unexpected Exception was thrown");
        }

        BigDecimal expectedNewBalance = new BigDecimal("399.01");
        BigDecimal newBalance = this.account.getBalance();

        assertEquals(expectedNewBalance, newBalance);
    }

    /**
     * Test that you can't withdraw a negative amount
     */
    @Test(expected = NegativeWithdrawalException.class)
    public void testNegativeWithdrawal() throws NegativeWithdrawalException {
        BigDecimal withdrawalAmount = new BigDecimal("-100");

        try {
            this.account.withdraw(withdrawalAmount);
        } catch (OverdraftException e) {
            fail("Unexpected error was thrown");
        }
        BigDecimal balance = this.account.getBalance();
        assertEquals(balance, INITIAL_DEPOSIT);
    }

    /**
     * Test that you can't deposit a negative amount
     */
    @Test(expected = NegativeDepositException.class)
    public void testNegativeDeposit() throws NegativeDepositException {
        BigDecimal depositAmount = new BigDecimal("-100");

        this.account.deposit(depositAmount);
        BigDecimal balance = this.account.getBalance();
        assertEquals(balance, INITIAL_DEPOSIT);
    }
}
