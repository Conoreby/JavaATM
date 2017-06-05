package com.conoreby.projects;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by conoreby on 6/4/17.
 */
public interface IUser {

    public Bool isUserMatch(Long userID, char [] pin);

    public AbstractAccount getAccount();

}
