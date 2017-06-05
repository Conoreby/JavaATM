package com.conoreby.projects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;

/**
 * Created by conoreby on 6/4/17.
 */
public class CheckingAccount extends AbstractAccount {

    public CheckingAccount(BigDecimal initialDeposit) {
        this.setBalance(initialDeposit);
    }

    @Override
    public void deposit(BigDecimal depositAmount) {
        throw new NotImplementedException();

    }

    @Override
    public void withdraw(BigDecimal withdrawalAmount) {
        throw new NotImplementedException();
    }
}
