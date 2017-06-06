package com.conoreby.projects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;


/**
 * Created by conoreby on 6/4/17.
 */
public class BasicUser implements IUser {

    /* Instance Variables */
    private AbstractAccount account;
    private long userID;
    private char[] pin;

    public BasicUser(long userID, char[] pin, AbstractAccount account) {
       this.account = account;
       this.userID = userID;
       this.pin = pin;
    }

    @Override
    public Boolean isUserMatch(long userID, char[] pin) {
        if (userID == this.userID && Arrays.equals(pin, this.pin)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getUserID() {
        return this.userID;
    }

    @Override
    public AbstractAccount getAccount() {
       return this.account;
    }
}
