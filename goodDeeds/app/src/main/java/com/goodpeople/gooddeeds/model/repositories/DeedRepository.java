package com.goodpeople.gooddeeds.model.repositories;

import com.goodpeople.gooddeeds.model.entities.IDeed;

import java.util.List;
import java.util.UUID;

/**
 * Defines the interface for handling deed data between service and data handler
 */

public interface DeedRepository {

    List<IDeed> getDeeds();

    List<IDeed> getMyActiveOffers();

    List<IDeed> getMyActiveRequests();

    void createOffer(String subject, String description);

    void editDeed(String subject, String description);

    void createRequest(String subject, String description);

    IDeed getCurrentDeed();

    void setCurrentDeed(UUID id);

    List<IDeed> getActiveRequests();

    List<IDeed> getActiveOffers();

    void deleteCurrentDeed();


    boolean isMyActiveDeed();

    boolean isClaimed();

    boolean isMyOwnDeed();

    void claimDeed();

}
