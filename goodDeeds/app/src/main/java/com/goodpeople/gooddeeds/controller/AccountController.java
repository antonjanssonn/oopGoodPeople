package com.goodpeople.gooddeeds.controller;

import com.goodpeople.gooddeeds.model.entities.IAccount;
import com.goodpeople.gooddeeds.model.repositories.AccountRepository;
import com.goodpeople.gooddeeds.model.repositories.AccountRepositoryImpl;

/**
 * Responsible for handling the input from Account-views.
 * Sends and fetches data to/from AccountRepository.
 */

public class AccountController {

    private final AccountRepository accountRepository = new AccountRepositoryImpl();

    public AccountController() {

    }

    /**
     * Creates account by calling repository method
     *
     * @param name       name of person creating the account
     * @param postalCode postal code of person creating the account
     * @param email      Email of person creating the account
     * @param password   Password of person creating the account
     */
    public void createAccountHandler(String name, int postalCode, String email, String password) {
        accountRepository.createAccount(name, postalCode, email, password);
    }

    /**
     * Validates login details by calling repository method
     *
     * @param email    email input
     * @param password password input
     * @return boolean that validates whether there is a match of account email + password
     */
    public boolean validateLoginHandler(String email, String password) {
        return accountRepository.validateLogin(email, password);
    }

    /**
     * @param email email input
     * @return boolean whether the email is used by another account or not
     */
    public boolean isEmailUsedHandler(String email) {
        return accountRepository.isEmailUsed(email);
    }

    /**
     * Logs in account by calling repository method
     *
     * @param email    email input
     * @param password password input
     */
    public void loginHandler(String email, String password) {
        accountRepository.login(email, password);
    }

    /**
     * @return boolean that specifies whether the user is logged in or not
     */
    public boolean isLoggedInHandler() {
        return accountRepository.isLoggedIn();
    }

    /**
     * @return returns logged in account-object
     */
    public IAccount getLoggedInAccountHandler() {
        return accountRepository.getAccount();
    }

    /**
     * Updates the logged in accounts password by calling repository method
     *
     * @param newPassword the new password
     */
    public void updatePasswordHandler(String newPassword) {
        accountRepository.updatePassword(newPassword);
    }

    /**
     * Updates account details by calling repository method
     *
     * @param name       potentially new account name
     * @param email      potentially new account email
     * @param postalCode potentially new postal code
     */
    public void editAccountHandler(String name, String email, int postalCode) {
        accountRepository.editAccount(name, email, postalCode);
    }

    /**
     * Logs out the account currently logged in, by calling the repository method.
     */
    public void logoutHandler() {
        accountRepository.logout();
    }
}
