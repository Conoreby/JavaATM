package com.conoreby.projects;

import java.math.BigDecimal;

/**
 * Created by conoreby on 6/4/17.
 */
public class CheckingAccount extends AbstractAccount {

    public CheckingAccount(BigDecimal initialDeposit) {
        this.setBalance(initialDeposit);
    }

    @Override
    public void deposit(BigDecimal depositAmount) throws NegativeDepositException {
        /* compareTo returns -1, 0, or 1 as this BigDecimal is numerically less than, equal to, or greater than val. */
        if (depositAmount.compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeDepositException();
        BigDecimal currentBalance = this.getBalance();
        BigDecimal newBalance = currentBalance.add(depositAmount);
        this.setBalance(newBalance);

    }

    @Override
    public void withdraw(BigDecimal withdrawalAmount) throws NegativeWithdrawalException, OverdraftException {
        if (withdrawalAmount.compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeWithdrawalException();
        BigDecimal currentBalance = this.getBalance();

        if (currentBalance.compareTo(withdrawalAmount) < 0 )
            throw new OverdraftException();

        BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);
        this.setBalance(newBalance);
    }
}
