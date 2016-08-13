package com.twu.biblioteca;

import com.sun.istack.internal.Nullable;
import com.twu.biblioteca.Account;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlee on 12/08/2016.
 */
public class Accounts {
    private final PrintStream outputStream;
    private List<Account> listOfAccounts;

    public Accounts(PrintStream outputStream) {
        this.outputStream = outputStream;
        listOfAccounts = new ArrayList<>();
        addPredefinedAccounts();
    }

    public void addAccount(Account account) {
        listOfAccounts.add(account);
    }

    public void addPredefinedAccounts()
    {
        Account account = new Account("123-4567", "password", "Test User", "test@test.com", "07777777777");
        addAccount(account);
        account = new Account("234-5678", "passwordtwo", "Test User 2", "test2@test.com", "07777777778");
        addAccount(account);
    }

    public String getLibraryNumberFromCommand(String libraryAccountPassword) {
        int index = libraryAccountPassword.lastIndexOf(" ");

        if (index > -1) {
            return libraryAccountPassword.substring(0, index);
        }
        return "";
    }

    public String getPasswordFromCommand(String libraryAccountPassword) {
        int index = libraryAccountPassword.lastIndexOf(" ");

        if (index > -1) {
            return libraryAccountPassword.substring(index+1, libraryAccountPassword.length());
        }
        return "";
    }

    public String checkLoginCredentials(String libraryAccountPassword) {

        String libraryNumber = getLibraryNumberFromCommand(libraryAccountPassword);
        String password = getPasswordFromCommand(libraryAccountPassword);

        if (!libraryNumber.equals("") && (!password.equals(""))) {

            for (Account account:listOfAccounts) {

                if (account.getLibraryNumber().equals(libraryNumber)) {
                    if (account.getPassword().equals(password)) {
                        return libraryNumber;
                    } else {
                        return "";
                    }
                }
            }
        }
        return "";
    }

    public List<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    public @Nullable Account getAccountFromLibraryNumber(String libraryNumber) {
        for (Account account:listOfAccounts){
            if (account.getLibraryNumber().equals(libraryNumber)) {
                return account;
            }
        }
        return null;
    }

}
