package com.conoreby.projects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    private static ATM atm;
    private static boolean logoutUser;
    private static BufferedReader bufferedReader;

    private static final String USER_ID_PROMPT = "Please enter your User ID to get started:";
    private static final String USER_NOT_FOUND_MESSAGE = "No user was found for the input User ID";
    private static final String PIN_PROMPT = "Please enter PIN:";
    private static final String INCORRECT_PIN_MESSAGE = "Invalid Pin";
    private static final String ACTION_PROMPT = "Press 1 followed by the Enter key to check Balance. \n"
                                              + "Press 2 followed by the Enter key to make a Deposit. \n"
                                              + "Press 3 followed by the Enter key to make a Withdrawal. \n"
                                              + "Press 4 followed by the Enter key to Log Out. \n";
    private static final String DEPOSIT_PROMPT = "Please enter the amount you would like to deposit as a dollar amount "
                                                + "and cent amount separated by a decimal. Such as 500.00 or 10.25";
    private static final String WITHDRAWAL_PROMPT = "Please enter the amount you would like to withdraw as a dollar "
                                                + "amount and cent amount separated by a decimal. Such as 500.00 or 10.25";
    private static final String BALANCE_RESULT_MESSAGE = "Your current balance is:\n";
    private static final String OVERDRAFT_MESSAGE = "Transaction Cancelled: Insufficient funds";
    private static final String INCORRECT_INPUT = "Transaction Cancelled: Incorrect Input";


    public static void main(String[] args) throws Exception {
        List<IUser> fakeUsers = createFakeUserList();
        atm = new ATM(fakeUsers);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        logoutUser = false;

        startATMPromptLoop();
    }

    private static void startATMPromptLoop() throws Exception {
        while (true) {
            loginUser();

            //Catch Exceptions and output user friendly message
            while(!logoutUser) {
                performUserAction();
            }
            logoutUser = false;
        }

    }

    private static void performUserAction() throws Exception {
        System.out.println(ACTION_PROMPT);
        int choice = Integer.parseInt(bufferedReader.readLine());
        switch (choice) {
            case 1: balanceAction();
                    break;
            case 2: depositAction();
                    break;
            case 3: withdrawalAction();
                    break;
            case 4: logoutAction();
            default: break;

        }
    }

    private static void depositAction() throws Exception {
        System.out.println(DEPOSIT_PROMPT);

        BigDecimal depositAmount = new BigDecimal(bufferedReader.readLine());
        try {
            atm.depositToCurrentUser(depositAmount);
        } catch (NegativeDepositException e) {
            System.out.println(INCORRECT_INPUT);
            return;
        }

        //Output the new balance
        balanceAction();
    }

    private static void withdrawalAction() throws Exception {
        System.out.println(WITHDRAWAL_PROMPT);

        BigDecimal withdrawalAmount = new BigDecimal(bufferedReader.readLine());
        try {
            atm.withdrawFromCurrentUser(withdrawalAmount);
        } catch (OverdraftException e) {
            System.out.println(OVERDRAFT_MESSAGE);
            return;
        } catch (NegativeDepositException e) {
            System.out.println(INCORRECT_INPUT);
            return;
        }

        balanceAction();
    }

    private static void logoutAction() {
        atm.logoutUser();
        logoutUser = true;
    }

    private static void balanceAction() throws UnauthorizedActionException {
        BigDecimal currentBalance = atm.getBalanceFromCurrentUser();
        String balanceString = NumberFormat.getCurrencyInstance(Locale.US).format(currentBalance);

        System.out.println(BALANCE_RESULT_MESSAGE);
        System.out.println(balanceString + "\n");
    }

    private static void loginUser() throws IOException {

        while (true) {
            long currentUserID = promptForUserID();

            try {
                atm.setCurrentUser(currentUserID);
            } catch (UserNotFoundException e) {
                System.out.println(USER_NOT_FOUND_MESSAGE);
                continue;
            }

            while (true) {
                char[] PIN = promptForUserPin();

                boolean isLoggedIn = false;
                try {
                    isLoggedIn = atm.loginCurrentUser(PIN);
                } catch (UserNotFoundException e) {
                    System.out.println(USER_NOT_FOUND_MESSAGE);
                    continue;
                }

                if (!isLoggedIn) {
                    System.out.println(INCORRECT_PIN_MESSAGE);
                    continue;
                }

                return;
            }
        }
    }

    private static long promptForUserID() throws IOException {
        Long userID = UserManager.RESERVED_USERID;


        while (userID == UserManager.RESERVED_USERID) {
            System.out.println(USER_ID_PROMPT);
            userID = Long.parseLong(bufferedReader.readLine());
        }
        return userID;
    }

    private static char[] promptForUserPin() throws IOException {
            System.out.println(PIN_PROMPT);
            String PINString = bufferedReader.readLine();
            return PINString.toCharArray();
    }

    private static List<IUser> createFakeUserList() {
        CheckingAccount user1Account = new CheckingAccount(new BigDecimal("500.00"));
        BasicUser user1 = new BasicUser(1, "1111".toCharArray(), user1Account);

        CheckingAccount user2Account = new CheckingAccount(new BigDecimal("3.00"));
        BasicUser user2 = new BasicUser(2, "2222".toCharArray(), user2Account);

        List<IUser> toReturn = new ArrayList<>(2);
        toReturn.add(user1);
        toReturn.add(user2);

        return toReturn;
    }
}
