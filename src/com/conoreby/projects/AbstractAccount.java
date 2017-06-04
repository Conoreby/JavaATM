package com.conoreby.projects;

import java.math.BigDecimal;

/**
 * Created by conoreby on 6/4/17.
 */
public abstract class AbstractAccount {

    /* Instance Variables */
    private BigDecimal balance;



    public BigDecimal GetBalance() {
        return this.balance;
    }

    protected void SetBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract void Deposit(BigDecimal depositAmount);


    public abstract void Withdraw(BigDecimal withdrawalAmount);

}
