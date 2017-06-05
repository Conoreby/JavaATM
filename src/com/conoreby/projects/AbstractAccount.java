package com.conoreby.projects;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by conoreby on 6/4/17.
 */
public abstract class AbstractAccount {

    /* Instance Variables */
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return this.balance;
    }

    protected void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public abstract void deposit(BigDecimal depositAmount);

    public abstract void withdraw(BigDecimal withdrawalAmount);
}

