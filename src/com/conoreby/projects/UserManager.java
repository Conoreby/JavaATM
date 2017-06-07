package com.conoreby.projects;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by conoreby on 6/5/17.
 */
public class UserManager {

    public static final long RESERVED_USERID = -1;
    private Map<Long, IUser> userMap;

    public UserManager(List<IUser> users) throws ConflictingUserDataException {
        this.userMap = new HashMap<>();
        for (IUser user : users)  {
            Long currentUserID = new Long(user.getUserID());
            if (currentUserID == UserManager.RESERVED_USERID || this.userMap.containsKey(currentUserID))
                throw new ConflictingUserDataException();
            this.userMap.put(user.getUserID(), user);
        }
    }

    public IUser getUserForID(long userID) {
        return this.userMap.get(userID);
    }
}

