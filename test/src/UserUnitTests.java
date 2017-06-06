import com.conoreby.projects.AbstractAccount;
import com.conoreby.projects.CheckingAccount;
import com.conoreby.projects.User;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by conoreby on 6/5/17.
 */
public class UserUnitTests {

    private User testUser;
    private AbstractAccount account;
    private static final char [] PIN = "1234".toCharArray();
    private static final long USER_ID = Long.parseLong("1");

    @Before
    public void setupUser() {
        account = new CheckingAccount(new BigDecimal("500"));
        testUser = new User(USER_ID, PIN, account);
    }

    @Test
    public void testCorrectUserMatch() {
        char [] testPIN = "1234".toCharArray();
        long userID = Long.parseLong("1");
        Boolean result = testUser.isUserMatch(userID, testPIN);
        assertTrue(result);
    }

    @Test
    public void testIncorrectPIN() {
        char [] testPIN = "0000".toCharArray();
        long userID = Long.parseLong("1");
        Boolean result = testUser.isUserMatch(userID, testPIN);
        assertFalse(result);
    }

    @Test
    public void testIncorrectUserID() {
        char [] testPIN = "1234".toCharArray();
        long userID = Long.parseLong("2");
        Boolean result = testUser.isUserMatch(userID, testPIN);
        assertFalse(result);
    }
}
