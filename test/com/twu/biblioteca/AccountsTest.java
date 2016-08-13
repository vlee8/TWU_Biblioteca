package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vlee on 12/08/2016.
 */
public class AccountsTest {

    private Accounts accounts;

    @Before
    public void setUp() throws Exception {
        PrintStream outputStream = mock(PrintStream.class);
        accounts = new Accounts(outputStream);
        //accounts = mock(Accounts.class);
    }

    @Test
    public void shouldDetectCorrectLibraryNumberFromCommand() throws Exception {
        assertEquals("123-4567", accounts.getLibraryNumberFromCommand("123-4567 password"));
    }

    @Test
    public void shouldDetectInvalidLibraryNumberFromCommand() throws Exception {
        assertEquals("", accounts.getLibraryNumberFromCommand("password"));
    }

    @Test
    public void shouldDetectCorrectPasswordFromCommand() throws Exception {
        assertEquals("password", accounts.getPasswordFromCommand("123-4567 password"));
    }

    @Test
    public void shouldDetectInvalidPasswordFromCommand() throws Exception {
        assertEquals("", accounts.getPasswordFromCommand("123-4567"));
    }

    @Test
    public void shouldDetectCorrectLoginCredentials() throws Exception {
        assertEquals("123-4567", accounts.checkLoginCredentials("123-4567 password"));
    }

    @Test
    public void shouldDetectIncorrectLoginCredentials() throws Exception {
        assertEquals("", accounts.checkLoginCredentials("123-4567 incorrectPassword"));
    }

    @Test
    public void shouldGetCorrectAccountFromLibraryNumber() throws Exception {
        assertEquals(accounts.getListOfAccounts().get(0), accounts.getAccountFromLibraryNumber("123-4567"));
    }

    @Test
    public void shouldGetNullFromIncorrectLibraryNumber() throws Exception {
        assertEquals(null, accounts.getAccountFromLibraryNumber("132-4567"));
    }
}
