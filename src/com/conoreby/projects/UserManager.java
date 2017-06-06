package com.conoreby.projects;

import java.util.List;

/**
 * Created by conoreby on 6/5/17.
 */
public class UserManager {

    private List<IUser> users;

    public UserManager(List<IUser> users) {
        this.users = users;
    }

    public IUser getUserForID(long userID) {
       for (IUser user : this.users) {
            if (userID == user.getUserID())
                return user;
       }
       return null;
    }

}
