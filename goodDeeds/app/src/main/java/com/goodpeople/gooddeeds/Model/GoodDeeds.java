package com.goodpeople.gooddeeds.Model;

import com.goodpeople.gooddeeds.Model.Entities.Account;
import com.goodpeople.gooddeeds.Model.Entities.Deed;
import com.goodpeople.gooddeeds.Model.Entities.IAccount;
import com.goodpeople.gooddeeds.Model.Entities.IDeed;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Defines the base for the model.
 * Holds the list of existing deeds and accounts
 * and the current logged in account.
 */

public class GoodDeeds {

    private static GoodDeeds goodDeeds;
    final List<IDeed> deeds = new ArrayList<>();
    private final List<IAccount> accounts = new ArrayList<>();
    private Deed currentDeed;
    private IAccount loggedInAccount;

    private GoodDeeds() {

/*
        Account a2 = new Account("Anton", 30597, "1234@gmail.com", "ahah".hashCode());

        Account a = new Account("Anton", 30597, "anton46304@gmail.com", "ahah".hashCode());
        Deed d = Deed.newOffer(a, "Gräsklipp", "Jag hjälper gärna till att klippa gräsmattan i storgöteborg, ge mig en pling");
        Deed d2 = Deed.newOffer(a, "Hårklipp", "Jag klipper gärna håret på folk! Ge mig en pling vetja!");
        accounts.add(a2);
        Deed d3 = Deed.newRequest(a, "Rosett", "Jag kan inte knyta mina skor, kan någon hjälpa mig?");
        Deed d4 = Deed.newRequest(a2, "Lokalsinne", "Jag har tappat bort mig och skulle behöva hjälpa av någon att hitta hem. Hjälp önskas snarast, gärna innan skymningen.");
        accounts.add(a);
        loggedInAccount = a;
        deeds.add(d);
        deeds.add(d2);
        deeds.add(d3);
        deeds.add(d4);
*/

    }

    public static GoodDeeds getGoodDeeds() {
        if (goodDeeds == null) {
            goodDeeds = new GoodDeeds();
        }
        return goodDeeds;
    }

    /**
     * @return returns all Account objects that is active
     */
    public List<IAccount> getAccounts() {
        return accounts;
    }

    /**
     * Creates new Account-object and adds it to accounts-list
     *
     * @param name       Name of person trying to create account
     * @param postalCode Postal Code of person trying to create account
     * @param email      Email of person trying to create account
     * @param password   Wanted account password
     */
    public void createAccount(String name, int postalCode, String email, String password) {
        accounts.add(new Account(name, postalCode, email, password.hashCode()));
    }

    /**
     * Logs in user by assigning it to variable: loggedInAccount
     *
     * @param email    email of user trying to log in
     * @param password password of user trying to log in
     */
    public void login(String email, String password) {
        for (IAccount account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword() == password.hashCode()) {
                loggedInAccount = account;
            }
        }
    }

    /**
     * @param email    email
     * @param password password
     * @return boolean depending on whether there exists an account with those details or not
     */
    public boolean validateLogin(String email, String password) {
        for (IAccount account : accounts) {
            if (account.getEmail().equals(email) && account.getPassword() == password.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checking so that we hold the invariant that there should be only one account per e-mail
     *
     * @param email email address
     * @return boolean depending on whether the email is already used in another account or not
     */
    public boolean isEmailUsed(String email) {
        for (IAccount account : accounts) {
            if (account.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    private IDeed fetchDeed(UUID id) throws Exception {
        for (IDeed deed : deeds) {
            if (deed.getUuid().equals(id))
                return deed;
        }
        throw new Exception();
    }

    /**
     * Sets currentDeed to Deed with matching UUID.
     *
     * @param id UUID search for.
     */
    public void setCurrentdeed(UUID id) {
        try {
            currentDeed = (Deed) fetchDeed(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the currently assigned currentDeed.
     */
    public IDeed getCurrentDeed() {
        return currentDeed;
    }

    /**
     * WARNING
     * removes the CurrentDeed from the app.
     * Requires user to be logged in and Deed-owner.
     */
    public void deleteCurrentDeed() {
        if (isMyActiveDeed())
            deeds.remove(currentDeed);
        else
            throw new java.lang.RuntimeException("permission denied.");
    }

    /**
     * @return boolean depending on if the user is logged in or not
     */
    public boolean isLoggedIn() {
        return loggedInAccount != null;
    }

    /**
     * @return the logged in Account-object
     */
    public IAccount getAccount() {
        return this.loggedInAccount;
    }

    /**
     * Updates password for logged in account
     *
     * @param newPassword new password
     */
    public void updatePassword(String newPassword) {
        loggedInAccount.setPassword(newPassword.hashCode());
    }

    /**
     * sets name, email and postal code. It does not care if there actually is new values or not
     *
     * @param name       potentially new name
     * @param email      potentially new email
     * @param postalCode potentially new postal code
     */
    public void editAccount(String name, String email, int postalCode) {
        loggedInAccount.setName(name);
        loggedInAccount.setEmail(email);
        loggedInAccount.setPostalCode(postalCode);
    }

    /**
     * Creates a offer with the currently logged in account as giving account.
     * A user has to be logged in before calling this method.
     *
     * @param subject     The subject of the offer
     * @param description The description of the offer
     */
    public void createOffer(String subject, String description) {

        Deed newOffer = Deed.newOffer(loggedInAccount, subject, description);
        deeds.add(newOffer);
    }


    /**
     * Edits the subject and description of an already existing deed.
     * The logged in account must be the original creator of the deed.
     *
     * @param subject     The subject of the deed.
     * @param description the description of the deed.
     */
    public void editOffer(String subject, String description) {
        Deed deed = (Deed) getCurrentDeed();
        deed.setSubject(subject);
        deed.setDescription(description);
    }

    /**
     * Returns a list of all existing deeds.
     *
     * @return all deeds as IDeed
     */
    public List<IDeed> getDeeds() {
        List<IDeed> ideeds = new ArrayList<>(deeds);
        return ideeds;
    }

    /**
     * Creates a list of deeds where the logged in account is registered as the giving account
     * and the receiving account is yet to be claimed.
     * A user has to be logged in before calling this method.
     *
     * @return a list of deeds with logged in account as giving account
     */

    public List<IDeed> getMyActiveOffers() {
        List<IDeed> myActiveOffers = new ArrayList<>();

        for (IDeed d : deeds) {
            if (loggedInAccount == d.getGivingAccount() && d.getReceivingAccount() == null) {
                myActiveOffers.add(d);
            }
        }
        return (myActiveOffers);
    }

    /**
     * Creates a list of deeds where the logged in account is registered as the receiving account
     * and the giving account is yet to be claimed.
     * A user has to be logged in before calling this method.
     *
     * @return a list of deeds with logged in account as receiving account
     */

    public List<IDeed> getMyActiveRequests() {
        List<IDeed> myActiveRequests = new ArrayList<>();

        for (IDeed d : deeds) {
            if (loggedInAccount == d.getReceivingAccount() && d.getGivingAccount() == null) {
                myActiveRequests.add(d);
            }
        }
        return (myActiveRequests);
    }

    /**
     * Creates a list of deeds where the logged in account is registered as either the giving
     * account or the receiving account and the other is not null.
     *
     * @return a list of claimed deeds with logged in account
     * as either volunteer or receiver of help
     */
    public List<IDeed> getMyClaimedDeeds() {
        List<IDeed> myClaimedDeeds = new ArrayList<>();

        for (IDeed d : deeds) {
            if ((d.getReceivingAccount() == loggedInAccount && d.getGivingAccount() != null) ||
                    (d.getGivingAccount() == loggedInAccount && d.getReceivingAccount() != null) &&
                            !d.isDone()) {
                myClaimedDeeds.add(d);
            }
        }
        return myClaimedDeeds;
    }

    /**
     * Creates a list of deeds that are done and where the logged in account has
     * either given och received help
     *
     * @return a list of done deeds with logged in account
     * as either volunteer or receiver of help
     */
    public List<IDeed> getMyDoneDeeds() {
        List<IDeed> myDoneDeeds = new ArrayList<>();

        for (IDeed d : deeds) {
            if (d.isDone()) {
                myDoneDeeds.add(d);
            }
        }
        return myDoneDeeds;
    }

    /**
     * Creates a list which will gather all deeds that are requests.
     *
     * @return a list of deeds with active requests
     */
    public List<IDeed> getActiveRequests() {
        List<IDeed> allActiveRequests = new ArrayList<>();

        for (IDeed d : deeds) {
            if ((d.getReceivingAccount() != null) && (d.getGivingAccount() == null)) {
                allActiveRequests.add(d);
            }
        }
        return allActiveRequests;
    }

    /**
     * Creates a list which will gather all deeds that are offers.
     *
     * @return a list of deeds with active offers
     */

    public List<IDeed> getActiveOffers() {
        List<IDeed> allActiveOffers = new ArrayList<>();

        for (IDeed d : deeds) {
            if ((d.getGivingAccount() != null) && (d.getReceivingAccount() == null)) {
                allActiveOffers.add(d);
            }
        }
        return allActiveOffers;
    }

    /**
     * Logs out the currently logged in account.
     */
    public void logout() {
        loggedInAccount = null;
    }


    public void createRequest(String subject, String description) {
        Deed newRequest = Deed.newRequest(loggedInAccount, subject, description);
        deeds.add(newRequest);
    }

    /**
     * Checks if a deed is active (not claimed) and that
     * the logged in account is the creator of the deed.
     *
     * @return true if the deed belong to the user and is not yet claimed
     * false otherwise
     */
    public boolean isMyActiveDeed() {
        IDeed deed = getCurrentDeed();

        List<IDeed> offers = getMyActiveOffers();
        List<IDeed> requests = getMyActiveRequests();


        return (offers.contains(deed) || requests.contains(deed));


    }

    /**
     * Method for getting all deeds
     *
     * @return a list of deeds
     */
    public List<IDeed> returnDeeds() {
        return deeds;
    }

    /**
     * Method for checking that the logged in account is not
     * the creator of the deed
     *
     * @return true if the creator of the deed is the logged in account
     * false otherwise
     */
    public boolean isMyOwnDeed() {
        return ((currentDeed.getGivingAccount() == loggedInAccount)
                || (currentDeed.getReceivingAccount() == loggedInAccount));
    }

    /**
     * Method for checking that the deed is still active, eg not claimed
     *
     * @return true if both giving and receiving account of the deed is initialized.
     * false otherwise
     */
    public boolean isClaimed() {
        return ((currentDeed.getGivingAccount() != null)
                && (currentDeed.getReceivingAccount() != null));
    }


    /**
     * Method for claiming deed. Sets, whichever is not already initialized of,
     * givingAccount or receivingAccount to the loggedInAccount.
     */
    public void claimDeed() {
        if (currentDeed.getReceivingAccount() == null) {
            currentDeed.setReceivingAccount(loggedInAccount);
        } else if (currentDeed.getGivingAccount() == null) {
            currentDeed.setGivingAccount(loggedInAccount);
        }
    }

    /**
     * Method for marking a deed as done
     */
    public void deedIsDone() {
        System.out.print(getAccount().getKarmaPoints());
        currentDeed.deedIsDone();
        updateKarmaPoints();
        System.out.print(getAccount().getKarmaPoints());
    }

    /**
     * Method for updating total karmaPoints for an account when a deed is done
     */
    public void updateKarmaPoints() {
        int dPoints = currentDeed.getKarmaPoints();
        currentDeed.getGivingAccount().updateKarmaPoints(dPoints);
        currentDeed.getReceivingAccount().updateKarmaPoints(((-1) * dPoints));
    }
}
