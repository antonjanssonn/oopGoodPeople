package com.goodpeople.gooddeeds.Model.Repositories;


import com.goodpeople.gooddeeds.Model.GoodDeeds;

import com.goodpeople.gooddeeds.Model.Entities.IDeed;

import java.util.List;
import java.util.UUID;

/**
 * Handles the layer between service and data handler
 */

public class DeedRepositoryImpl implements DeedRepository {

    private GoodDeeds goodDeeds = GoodDeeds.getGoodDeeds();


    @Override
    public List<IDeed> getDeeds() {
        return goodDeeds.getDeeds();
    }

    /**
     * Method for getting the active offers for a logged in account.
     * A account has to be logged in before calling this method.
     *
     * @return the list of active offers for the logged in account
     */
    @Override
    public List<IDeed> getMyActiveOffers() {
        return goodDeeds.getMyActiveOffers();
    }

    /**
     * Method for getting the active requests for a logged in account.
     * A account has to be logged in before calling this method.
     *
     * @return the list of active requests for the logged in account
     */
    @Override
    public List<IDeed> getMyActiveRequests() {
        return goodDeeds.getMyActiveRequests();
    }

    /**
     * Method for creating a new offer with the logged in account as the giving account.
     * A account has to be logged in before calling this method.
     *
     * @param subject     The subject of the offer.
     * @param description The description of the offer.
     */
    @Override
    public void createOffer(String subject, String description) {
        goodDeeds.createOffer(subject, description);
    }


    /**
     * Edits the subject and/or description of an anlready existing deed
     *
     * @param subject     The subject to be edited
     * @param description The description to be edited
     */
    @Override
    public void editOffer(String subject, String description) {
        goodDeeds.editOffer(subject, description);
    }

    @Override
    public void createRequest(String subject, String description) {
        goodDeeds.createRequest(subject, description);
    }

    @Override
    public IDeed getCurrentDeed() {
        return goodDeeds.getCurrentDeed();
    }

    @Override
    public void setCurrentDeed(UUID id) {
        goodDeeds.setCurrentdeed(id);
    }


    /**
     * Gets a list of active requests by calling goodDeeds
     *
     * @return a list of deeds that are active requests
     */
    @Override
    public List<IDeed> getActiveRequests() {
        return goodDeeds.getActiveRequests();
    }

    /**
     * Gets a list of active offers by calling goodDeeds
     *
     * @return a list of deeds that are active offers
     */
    @Override
    public List<IDeed> getActiveOffers() {
        return goodDeeds.getActiveOffers();
    }

    /**
     * Checks if a deed belongs to the logged in account and
     * that the deed is not "done" yet
     *
     * @return true if the deed is active and created by the logged in account
     * false otherwise.
     */
    @Override
    public boolean isMyActiveDeed() {
        return goodDeeds.isMyActiveDeed();
    }

    @Override
    public boolean isClaimed() {
        return goodDeeds.isClaimed();
    }

    @Override
    public boolean isMyOwnDeed() {
        return goodDeeds.isMyOwnDeed();
    }

    @Override
    public void claimDeed() {
        goodDeeds.claimDeed();
    }
}
