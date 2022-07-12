package framework.model;

import java.util.Date;

public abstract class Entry implements IEntry {
    protected double amount;
    protected Date date;
    protected IAccount account;

    public Entry(IAccount account, double amount) {
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

    public IAccount getAccount() {
        return account;
    }

    public abstract void process();

}
