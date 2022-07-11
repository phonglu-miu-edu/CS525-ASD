package ccard.framework;

import ccard.CreditCardAccount;

public class FinCo {
    public CreditCardAccount createAccount(String accountNum, double currentBalance, ICustomer customer) {
        return new CreditCardAccount(accountNum, currentBalance, customer);
    }
}
