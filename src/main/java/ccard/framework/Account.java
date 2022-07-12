package ccard.framework;

import java.util.ArrayList;
import java.util.List;

public class Account implements IAccount {
    private String accountNum;
    private double currentBalance;
    private ICustomer customer;
    private List<Entry> entryHistory;

    public Account(String accountNum, double currentBalance, ICustomer customer) {
        this.accountNum = accountNum;
        this.currentBalance = currentBalance;
        this.customer = customer;
        this.entryHistory = new ArrayList<>();
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public Object generateReport() {
        return null;
    }

    @Override
    public void addInterest() {

    }

    public void addEntry(Entry entry) {
        entryHistory.add(entry);
    }
}

