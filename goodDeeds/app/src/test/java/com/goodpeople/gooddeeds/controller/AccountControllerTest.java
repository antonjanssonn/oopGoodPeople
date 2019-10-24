package com.goodpeople.gooddeeds.controller;

import com.goodpeople.gooddeeds.model.entities.IAccount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AccountControllerTest {
    private AccountController accountController;

    @Before
    public void setUp() {
        accountController = new AccountController();
        accountController.createAccount("richard", 41481, "rm123@rm.se", "pass123");
        accountController.login("rm123@rm.se", "pass123");
    }


    @Test
    public void shouldCreateAccountLoginReturnLoggedinAccount() {
        IAccount account = accountController.accountHandler();
        Assert.assertEquals(account.getPassword(), "pass123".hashCode());
        Assert.assertEquals(account.getEmail(), "rm123@rm.se");
    }


    @Test
    public void shouldValidateValidLogInReturnTrue() {
        Assert.assertTrue(accountController.validateLogin("rm123@rm.se", "pass123"));
    }

    @Test
    public void shouldValidateInvalidEmailLogInReturnFalse() {
        Assert.assertFalse(accountController.validateLogin("wrong.email", "pass123"));
    }

    @Test
    public void shouldValidateInvalidPasswordLogInReturnFalse() {
        Assert.assertFalse(accountController.validateLogin("rm123@rm.se", "wrongPassword"));
    }

    @Test
    public void shouldTestIfLoggedInReturnTrue() {
        Assert.assertTrue(accountController.isLoggedIn());
    }


    @Test
    public void shouldUpdatePasswordStoreNewPassword() {
        accountController.updatePasswordHandler("newPassword");
        Assert.assertEquals(accountController.accountHandler().getPassword(), "newPassword".hashCode());

    }

    @Test
    public void shouldValidateIfEmailIsAlreadyUsedReturnTrue() {
        Assert.assertTrue(accountController.isEmailUsedHandler("rm123@rm.se"));
    }

    @Test
    public void shouldValidateIfEmailIsAlreadyUsedReturnFalse() {
        Assert.assertFalse(accountController.isEmailUsedHandler("notUsed@email.se"));
    }

    @Test
    public void shouldUpdateAccountDetails() {
        accountController.editAccountHandler("newName", "newEmail@email.se", 12345);
        Assert.assertEquals(accountController.accountHandler().getName(), "newName");
        Assert.assertEquals(accountController.accountHandler().getEmail(), "newEmail@email.se");
        Assert.assertEquals(accountController.accountHandler().getPostalCode(), 12345);
    }

    @Test
    public void logout() {
        accountController.logout();
        Assert.assertFalse(accountController.isLoggedIn());

    }
}