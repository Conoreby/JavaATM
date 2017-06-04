package com.conoreby.projects;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by conoreby on 6/4/17.
 */
public class User implements IUser {

    /* Instance Variables */
    private CheckingAccount account;
    private int userID;

    public User (int userID, CheckingAccount account) {
       this.account = account;
       this.userID = userID;
    }

    @Override
    public Bool IsUserMatch(Long userID, char[] pin) {
        throw new NotImplementedException();
    }


    @Override
    public CheckingAccount GetAccount() {
       return this.account;
    }
}
