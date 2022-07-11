package ccard.framework;

import java.util.Date;

public abstract class Entry {
    private double amount;
    private Date date;

    Entry(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }

    abstract void process();
}
