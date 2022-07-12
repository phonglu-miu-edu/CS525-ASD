package framework.model;

import java.util.ArrayList;
import java.util.Collection;

public class Account implements IAccount{

    private Customer customer;
    private String accountNum;
    private double currentBalance;
    private Collection<Entry> entryHistory = new ArrayList<>();

    protected double interestRate;

    public Account(Customer customer, String accountNum) {
        this.customer = customer;
        this.accountNum = accountNum;
        this.currentBalance = 0;
        //this.entryHistory = entryHistory;
        this.interestRate = 0.01;
    }

    @Override
    public String toString() {
        return "Account{" +
                "customer=" + customer +
                ", accountNum='" + accountNum + '\'' +
                ", currentBalance=" + currentBalance +
                ", entryHistory=" + entryHistory +
                ", interestRate=" + interestRate +
                '}';
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void addinterest() {
        this.currentBalance += this.currentBalance * getInterestRate();
    }

    @Override
    public void setBalance(double balance) {
        this.currentBalance = balance;
    }

    @Override
    public double getBalance() {
        return 0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Collection<Entry> getEntryHistory() {
        return entryHistory;
    }

    public void setEntryHistory(Collection<Entry> entryHistory) {
        this.entryHistory = entryHistory;
    }

    public void addEntryHistory(Entry entry)
    {
        this.entryHistory.add(entry);
    }

    public void changeBalanceByAmount(double amount) {
        currentBalance += amount;
    }

}
