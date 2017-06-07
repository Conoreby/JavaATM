package com.conoreby.projects;

import java.io.Console;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static ATM atm;
    private static boolean logoutUser;

    private static final String USER_ID_PROMPT = "Please enter your User ID to get started:";
    private static final String USER_NOT_FOUND_MESSAGE = "No user was found for the input User ID";
    private static final String PIN_PROMPT = "Please enter PIN:";
    private static final String INCORRECT_PIN_MESSAGE = "Invalid Pin";
    private static final String ACTION_PROMPT = "Press 1 followed by the Enter key to check Balance. \n"
                                              + "Press 2 followed by the Enter key to make a Deposit. \n"
                                              + "Press 3 followed by the Enter key to make a Withdrawal. \n"
                                              + "Press 4 followed by the Enter key to Log Out. \n";
    //TODO: Finish NUMBER ENTRY PROMPTS
    private static final String DEPOSIT_PROMPT = "Please enter the amount you would like to deposit as";

    public static void main(String[] args) throws ConflictingUserDataException {
        List<IUser> fakeUsers = createFakeUserList();
        atm = new ATM(fakeUsers);
        logoutUser = false;
        while (true) {
            loginUser();

            while(!logoutUser) {
                performUserAction();
            }
        }
    }

    private static void performUserAction() throws UnauthorizedActionException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(ACTION_PROMPT);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: balanceAction();
                case 2: depositAction();
                case 3: withdrawalAction();
                case 4: logoutAcion();
                default: break;

            }
        }

    }

    //TODO: Complete depositAction
    private static void depositAction() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(DEPO)
        }
        atm.depositToCurrentUser();
    }

    private static void balanceAction() throws UnauthorizedActionException {
        BigDecimal currentBalance = atm.getBalanceFromCurrentUser();
        String balanceString = NumberFormat.getCurrencyInstance(Locale.US).format(currentBalance);
        System.out.println(balanceString);
    }

    private static void loginUser() {

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

                try {
                    atm.loginCurrentUser(PIN);
                } catch (UserNotFoundException e) {
                    System.out.println(INCORRECT_PIN_MESSAGE);
                    continue;
                }
                return;
            }
        }
    }

    private static long promptForUserID() {
        Long userID = UserManager.RESERVED_USERID;

        try (Scanner scanner = new Scanner(System.in)){

            while (userID == UserManager.RESERVED_USERID) {
                System.out.println(USER_ID_PROMPT);
                userID = scanner.nextLong();
            }
        }
        return userID;
    }

    private static char[] promptForUserPin() {
        Console console = System.console();
        return console.readPassword();
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
