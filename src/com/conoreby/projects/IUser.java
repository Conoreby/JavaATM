package com.conoreby.projects;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by conoreby on 6/4/17.
 */
public interface IUser {

    public Bool IsUserMatch(Long userID, char [] pin);

    public AbstractAccount GetAccount();

}
