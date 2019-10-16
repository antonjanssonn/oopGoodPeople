package com.goodpeople.gooddeeds.Model;


import com.goodpeople.gooddeeds.Controller.AccountController;
import com.goodpeople.gooddeeds.Model.Entities.Account;
<<<<<<< HEAD
import com.goodpeople.gooddeeds.Model.Entities.Deed;

=======
import com.goodpeople.gooddeeds.Model.Entities.IAccount;
>>>>>>> master

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GoodDeedsTest {

<<<<<<< HEAD
    GoodDeeds gd;
    AccountController ac;
    Deed deed;

    @Before
    public void initialize() {
        gd = GoodDeeds.getGoodDeeds();
        ac = new AccountController();
        ac.createAccount("Test", 00000, "test@test.com", "123");
        ac.login("Test", "123");
        gd.createOffer("Subject", "Description");
        deed = gd.getDeeds().get(0);
        

=======

    GoodDeeds goodDeeds;

    @Before
    public void initialize() {
        goodDeeds = GoodDeeds.getGoodDeeds();
        goodDeeds.createAccount("Test", 00000, "test@test.com", "123");
        goodDeeds.login("test@test.com", "123");
        goodDeeds.createOffer("Subject", "Description");
>>>>>>> master
    }

    @After
    public void after() {
<<<<<<< HEAD
        gd.getDeeds().remove(0);
        gd = null;
=======
        goodDeeds.getAccounts().clear();
        goodDeeds.getDeeds().clear();
    }


    @Test
    public void shouldGetAccountsSize1() {
        assertEquals(goodDeeds.getAccounts().size(), 1);
    }

    @Test
    public void shouldCheckIfLoggedInReturnTrue() {
        assertTrue(goodDeeds.isLoggedIn());
    }

    @Test
    public void shouldValidateLoginReturnTrue() {
        assertTrue(goodDeeds.validateLogin("test@test.com", "123"));
    }

    @Test
    public void shouldValidateInvalidEmailLoginReturnFalse() {
        assertFalse(goodDeeds.validateLogin("wrong.email", "123"));
    }

    @Test
    public void shouldValidateInvalidPasswordLoginReturnFalse() {
        assertFalse(goodDeeds.validateLogin("test@test.com", "pass123"));
    }

    @Test
    public void updatePassword() {
        IAccount account = goodDeeds.getAccount();
        goodDeeds.updatePassword("newPassword");
        Assert.assertEquals(account.getPassword(), "newPassword");
>>>>>>> master
    }

    @Test
    public void createOfferShouldBeAddedToOffers() {
        assertEquals(1, goodDeeds.getDeeds().size());
    }

    @Test
    public void createOfferShouldStoreSubject() {
        assertEquals("Subject", goodDeeds.getDeeds().get(0).getSubject());
    }

    @Test
    public void createOfferShouldStoreDescription() {
        assertEquals("Description", goodDeeds.getDeeds().get(0).getDescription());
    }

    @Test
    public void shouldValidateIfEmailIsAlreadyUsedReturnTrue() {
        Assert.assertTrue(goodDeeds.isEmailUsed("test@test.com"));
    }

    @Test
<<<<<<< HEAD
    public void testEditSubject() {
        String subject = deed.getSubject();
        String description = deed.getDescription();
        gd.editOffer(deed, "Changed", "Description");
        assertFalse(subject == deed.getSubject());
        assertTrue(description == deed.getDescription());
    }

    @Test
    public void testEditDescription() {
        String subject = deed.getSubject();
        String description = deed.getDescription();
        gd.editOffer(deed, "Subject", "Changed");
        assertFalse(description == deed.getDescription());
        assertTrue(subject == deed.getSubject());
    }

    @Test
    public  void testEditOnlyYourOwnDeeds() {
        String subject = deed.getSubject();
        String description = deed.getDescription();
        ac.createAccount("SomeoneElse", 00000, "test@test.com", "123");
        Account newAccount = gd.getAccounts().get(1);
        deed.setGivingAccount(newAccount);
        gd.editOffer(deed, "Changed", "Changed");
        assertSame(subject, deed.getSubject());
        assertSame(description, deed.getDescription());

    }
}
=======
    public void shouldValidateIfEmailIsAlreadyUsedReturnFalse() {
        Assert.assertFalse(goodDeeds.isEmailUsed("notUsed@email.se"));
    }

    @Test
    public void getAccounts() {
        goodDeeds.createAccount("test3", 0000, "test3@test3.com", "123");
        assertEquals(goodDeeds.getAccounts().size(), 2);
    }

    @Test
    public void createAccount() {
        goodDeeds.createAccount("test3", 0000, "test3@test3.com", "123");
        assertEquals(goodDeeds.getAccounts().size(), 2);

    }

    @Test
    public void getMyOffers() {
        assertEquals(goodDeeds.getMyOffers().size(), 1);

    }

    @Test
    public void createOffer() {
        assertEquals(goodDeeds.getDeeds().size(), 1);
    }

    @Test
    public void getDeeds() {
        assertEquals(goodDeeds.getDeeds().size(), 1);
    }

    @Test
    public void shouldUpdateAccountDetails() {
        goodDeeds.editAccount("newName", "newEmail@email.se", 12345);
        Assert.assertEquals(goodDeeds.getAccount().getName(), "newName");
        Assert.assertEquals(goodDeeds.getAccount().getEmail(), "newEmail@email.se");
        Assert.assertEquals(goodDeeds.getAccount().getPostalCode(), 12345);
        assertEquals(goodDeeds.getDeeds().size(), 1);
    }

}
>>>>>>> master
