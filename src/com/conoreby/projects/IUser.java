package com.conoreby.projects;

/**
 * Created by conoreby on 6/4/17.
 */
public interface IUser {

    Boolean isUserMatch(long userID, char[] pin);

    long getUserID();

    //This could be extended to a list of accounts
    AbstractAccount getAccount();

}
