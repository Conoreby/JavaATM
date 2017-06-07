package com.conoreby.projects;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by conoreby on 6/6/17.
 */
public class ATM {

    private UserManager userManager;
    private IUser currentUser;
    private boolean isCorrectPIN;

    public ATM (List<IUser> users) throws ConflictingUserDataException {
       this.userManager = new UserManager(users);
    }

    /**
     * Set the current user to check the pin against
     * @param userID
     * @throws UserNotFoundException
     */
    public void setCurrentUser(long userID) throws UserNotFoundException {
        IUser foundUser = this.userManager.getUserForID(userID);
        if (foundUser == null)
            throw new UserNotFoundException();
        this.currentUser = foundUser;
    }

    /**
     * Login to the current user.
     *
     * Separated setting user and entering pin so if you mess up the pin it doesn't necessarily
     * prompt you to have to re-enter userID
     * @param PIN
     * @return
     * @throws UserNotFoundException
     */
    public Boolean loginCurrentUser(char [] PIN) throws UserNotFoundException {
        if (this.currentUser == null)
            throw new UserNotFoundException();

        Boolean isCorrectPIN = this.currentUser.isUserMatch(this.currentUser.getUserID(), PIN);
        if (isCorrectPIN)
            this.isCorrectPIN = isCorrectPIN;

       return isCorrectPIN;
    }

    public void logoutUser() {
        this.isCorrectPIN = false;
        this.currentUser = null;
    }

    public void withdrawFromCurrentUser(BigDecimal amount) throws Exception {
        if (!isCorrectPIN)
            throw new UnauthorizedActionException();

        AbstractAccount account = this.currentUser.getAccount();
        account.withdraw(amount);
    }

    public void depositToCurrentUser(BigDecimal amount) throws Exception {
        if (!isCorrectPIN)
            throw new UnauthorizedActionException();

        AbstractAccount account = this.currentUser.getAccount();

        account.deposit(amount);
    }

    /**
     * Returns currentUsers balance
     * @return
     * @throws UnauthorizedActionException
     */
    public BigDecimal getBalanceFromCurrentUser() throws UnauthorizedActionException {
        if (!isCorrectPIN)
            throw new UnauthorizedActionException();

        AbstractAccount account = this.currentUser.getAccount();
        return account.getBalance();
    }
}

