package framework.model;

import java.util.Date;

public abstract class Entry {
    protected double amount;
    protected Date date;
    protected Account account;

    public Entry(Account account, double amount) {
        this.account = account;
        this.amount = amount;
        this.date = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public abstract void process();

}
