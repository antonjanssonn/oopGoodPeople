package com.goodpeople.gooddeeds.Controller;

import com.goodpeople.gooddeeds.Model.Entities.IDeed;
import com.goodpeople.gooddeeds.Model.Repositories.DeedRepository;
import com.goodpeople.gooddeeds.Model.Repositories.DeedRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class DeedController {

    private DeedRepository deedRepository = new DeedRepositoryImpl();


    public DeedController() {
    }

    /**
     * Method for getting the active offers for a logged in account.
     * A account has to be logged in before calling this method.
     *
     * @return the list of active offers for the logged in account
     */
    public List<IDeed> showMyActiveOffersHandler() {
        return deedRepository.getMyActiveOffers();
    }

    /**
     * Method for getting the active requests for a logged in account.
     * A account has to be logged in before calling this method.
     *
     * @return the list of active requests for the logged in account
     */
    public List<IDeed> showMyActiveRequestsHandler() {
        return deedRepository.getMyActiveRequests();
    }

    /**
     * Method for creating a new offer with the logged in account as the giving account.
     * A account has to be logged in before calling this method.
     *
     * @param subject     The subject of the offer.
     * @param description The description of the offer.
     */
    public void createOfferHandler(String subject, String description) {
        deedRepository.createOffer(subject, description);
    }

    public void createRequestHandler(String subject, String description) {
        deedRepository.createRequest(subject, description);
    }

    /**
     * @return Gets the currently assigned currentDeed.
     */
    public IDeed getCurrentDeedHandler() {
        return deedRepository.getCurrentDeed();
    }

    /**
     * Reassigns currentDeed
     *
     * @param uuid the ID of new Deed to be assigned.
     */
    public void setCurrentDeedHandler(UUID uuid) {
        deedRepository.setCurrentDeed(uuid);
    }

    /**
     * Warning!
     * Removes the currently assigned Deed.
     * Requires user to be logged in and Deed-owner.
     */
    public void deleteCurrentDeedHandler() {
        deedRepository.deleteCurrentDeed();
    }

    /**
     * Checks if logged in account is the owner of the deed.
     *
     * @param deed The deed to check for ownership.
     * @return True if current account is owner of the deed.
     */
    public boolean isDeedOwnerHandler(IDeed deed) {
        return deedRepository.isDeedOwner(deed);
    }
}
