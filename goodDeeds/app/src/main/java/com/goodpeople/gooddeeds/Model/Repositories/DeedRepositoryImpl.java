package com.goodpeople.gooddeeds.Model.Repositories;

import com.goodpeople.gooddeeds.Model.Entities.Deed;
import com.goodpeople.gooddeeds.Model.GoodDeeds;

import java.util.List;

public class DeedRepositoryImpl implements DeedRepository {

    private GoodDeeds goodDeeds = GoodDeeds.getGoodDeeds();


    @Override
    public List<Deed> getDeeds() {
        return goodDeeds.getDeeds();
    }

    @Override
    public List<Deed> getMyDeeds() {
        return goodDeeds.getMyDeeds();
    }
    @Override
    public void createOffer(String subject, String description) {
        goodDeeds.createOffer(subject, description);
    }
}
