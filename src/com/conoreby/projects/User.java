package com.conoreby.projects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * Created by conoreby on 6/4/17.
 */
public class User implements IUser {

    /* Instance Variables */
    private AbstractAccount account;
    private long userID;
    private char[] pin;

    public User (long userID, char[] pin, AbstractAccount account) {
       this.account = account;
       this.userID = userID;
       this.pin = pin;
    }

    @Override
    public Boolean isUserMatch(long userID, char[] pin) {
        throw new NotImplementedException();
    }

    @Override
    public AbstractAccount getAccount() {
       return this.account;
    }
}
