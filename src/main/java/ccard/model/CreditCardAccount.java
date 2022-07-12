package ccard.model;

import framework.model.*;

import java.time.LocalDate;

public class CreditCardAccount extends Account {
    private String expiryDate;
    private CreditCardType type;

    public CreditCardAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate) {
        super(customer, accountNum);
        this.expiryDate = expiryDate == "" ? LocalDate.now().toString() : expiryDate;
        this.type = type;
        this.setCurrentBalance(2000);
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getCurrentMonthlyBalance() {
        return super.getCurrentBalance();
    }

    public double getTotalMonthlyCredits() {
        return 0;
    }

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }

    public double getLastMonthBalance() {
        // this is a placeholder algorithm for previous balance
        return this.getCurrentMonthlyBalance() * 0.9;
    }

    public double getTotalCurrentMonthCredits() {
        // for now go through all the payments for this account
        double deposits = 0;
        for (IEntry entry: getEntryHistory()) {
            if (entry instanceof DepositEntry) {
                deposits += entry.getAmount();
            }
        }

        return deposits;
    }

    public double getTotalCurrentMonthCharges() {
        // for now go through all the withdraws for this account
        double withdraws = 0;
        for (IEntry entry: getEntryHistory()) {
            if (entry instanceof WithdrawEntry) {
                withdraws += entry.getAmount();
            }
        }
        return withdraws;
    }

    public double getNewBalance() {
        return getLastMonthBalance() - getTotalCurrentMonthCredits() +
                getTotalCurrentMonthCharges() +
                this.type.getMonthlyInterest() * (getLastMonthBalance() - getTotalCurrentMonthCredits());
    }

    public double getTotalAmountDue() {
        return this.type.getMinimumPayment() * getNewBalance();
    }

    @Override
    public void addInterest() {
        // this is a placeholder algorithm for calculating interest
        this.setCurrentBalance(this.getCurrentBalance() * (1 - this.type.getMonthlyInterest()));
    }
}
