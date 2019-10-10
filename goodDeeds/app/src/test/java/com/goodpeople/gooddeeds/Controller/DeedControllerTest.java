package com.goodpeople.gooddeeds.Controller;

import com.goodpeople.gooddeeds.Model.GoodDeeds;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeedControllerTest {

    DeedController deedController;
    GoodDeeds goodDeeds;

    @Before
    public void initialize() {
        deedController = new DeedController();
        goodDeeds = GoodDeeds.getGoodDeeds();
        goodDeeds.createAccount("Test1",0000,"test1@test.se","123");
        goodDeeds.createAccount("Test2",0000,"test2@test.se","123");
        goodDeeds.login("test1@test.se","123");
        deedController.createOfferHandler("test","test");
        goodDeeds.login("test2@test.se","123");
        deedController.createOfferHandler("test","test");
    }

    @After
    public void after(){
        deedController.showOffersHandler().clear();
        goodDeeds.getDeeds().clear();
    }

    @Test
    public void showOffersHandler() {
        assertEquals(deedController.showOffersHandler().size(), 2);
    }

    @Test
    public void showMyOffersHandler() {
        assertEquals(deedController.showMyOffersHandler().size(),1);

    }

    @Test
    public void createOfferHandler() {
        assertEquals(goodDeeds.getDeeds().size(),2);
    }
}