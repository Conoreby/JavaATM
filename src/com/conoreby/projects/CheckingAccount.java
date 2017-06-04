package com.conoreby.projects;

import java.math.BigDecimal;

/**
 * Created by conoreby on 6/4/17.
 */
public class CheckingAccount extends AbstractAccount {

    public CheckingAccount(BigDecimal initialDeposit) {
        this.SetBalance(initialDeposit);
    }

    @Override
    public void Deposit(BigDecimal depositAmount) {

    }

    @Override
    public void Withdraw(BigDecimal withdrawalAmount) {

    }
}
