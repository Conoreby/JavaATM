import com.conoreby.projects.BasicUser;
import com.conoreby.projects.IUser;
import com.conoreby.projects.UserManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by conoreby on 6/5/17.
 */
public class UserManagerUnitTests {

    private UserManager userManager;

    @Before
    public void setup() {
        List<IUser> userList = getFakeUserList();
        this.userManager = new UserManager(userList);
    }

    @Test
    public void testGetUserByID() {
        BasicUser user1 = (BasicUser) this.userManager.getUserForID(1);
        assertTrue(user1.isUserMatch(1, "1111".toCharArray()));
    }

    @Test
    public void testGetUserByWrongID() {
        BasicUser notRealUser = (BasicUser) this.userManager.getUserForID(42);
        assertNull(notRealUser);
    }

    private List<IUser> getFakeUserList() {
        BasicUser user1 = new BasicUser(1, "1111".toCharArray(), null);
        BasicUser user2 = new BasicUser(2, "2222".toCharArray(), null);
        BasicUser user3 = new BasicUser(3, "3333".toCharArray(), null);

        ArrayList<IUser> list = new ArrayList<>(3);
        list.add(user1);
        list.add(user2);
        list.add(user3);

        return list;
    }
}
