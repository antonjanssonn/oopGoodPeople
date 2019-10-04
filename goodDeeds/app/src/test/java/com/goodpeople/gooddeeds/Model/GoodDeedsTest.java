package com.goodpeople.gooddeeds.Model;

import com.goodpeople.gooddeeds.Model.Entities.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoodDeedsTest {

    GoodDeeds gd;

    @Before
    public void initialize(){
        gd = GoodDeeds.getGoodDeeds();
        gd.getAccounts().add(new Account("Test", 00000, "test@test.com", "123"));
        gd.createOffer("Subject", "Description");
    }

    @After
    public void after() {
        gd = null;
    }

    @Test
    public void createOfferShouldBeAddedToOffers() {
        assertEquals(1, gd.getDeeds().size());
    }

    @Test
    public void createOfferShouldStoreSubject() {
        assertEquals("Subject", gd.getDeeds().get(0).getSubject());
    }

    @Test
    public void createOfferShouldStoreDescription() {
        assertEquals("Description", gd.getDeeds().get(0).getDescription());
    }
}