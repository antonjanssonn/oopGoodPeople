package com.goodpeople.gooddeeds.model.repositories;

import com.goodpeople.gooddeeds.model.GoodDeeds;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeedRepositoryImplTest {

    private DeedRepository dr;
    private GoodDeeds gd;

    @Before
    public void setUp() {
        dr = new DeedRepositoryImpl();
        gd = GoodDeeds.getGoodDeeds();
        gd.createAccount("A", 00000, "a@a.a", "a");
        gd.createAccount("B", 11111, "b@b.b", "b");
        gd.login("a@a.a", "a");
        dr.createOffer("test1", "test1");
        dr.createRequest("test1", "test1");
        gd.login("b@b.b", "b");
        dr.createOffer("test2", "test2");
        dr.createRequest("test2", "test2");
    }

    @After
    public void tearDown() {
        gd.getIDeeds().clear();
        gd.getAccounts().clear();
        gd.returnDeeds().clear();
        gd = null;
    }


    @Test
    public void getDeeds() {
        assertEquals(4, dr.getDeeds().size());
    }

    @Test
    public void getMyActiveOffers() {
        assertEquals(1, dr.getMyActiveOffers().size());
    }

    @Test
    public void getMyActiveRequests() {
        assertEquals(1, dr.getMyActiveRequests().size());
    }

    @Test
    public void createOffer() {
        assertEquals(4, dr.getDeeds().size());
    }

    @Test
    public void getActiveRequests() {
        assertEquals(dr.getActiveRequests().size(), 2);
    }

    @Test
    public void getActiveOffers() {
        assertEquals(dr.getActiveOffers().size(), 2);
    }
}