package com.goodpeople.gooddeeds.Model.Entities;

public class Deed {

    private Account givingAccount;
    private Account receivingAccount;
    private String subject;
    private String description;

    private Deed() {
    }

    public static Deed newOffer(Account givingAccount, String subject, String description) {
        Deed d = new Deed();
        d.setGivingAccount(givingAccount);
        d.setSubject(subject);
        d.setDescription(description);
        return d;
    }

    public static Deed newRequest(Account receivingAccount, String subject, String description) {
        Deed d = new Deed();
        d.setReceivingAccount(receivingAccount);
        d.setSubject(subject);
        d.setDescription(description);
        return d;
    }

    public Account getGivingAccount() {
        return givingAccount;
    }

    public void setGivingAccount(Account givingAccount) {
        this.givingAccount = givingAccount;
    }

    public Account getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(Account receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
