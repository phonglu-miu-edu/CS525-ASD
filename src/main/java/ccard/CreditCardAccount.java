package ccard;

import ccard.framework.Account;
import ccard.framework.ICustomer;

import java.util.Date;

public class CreditCardAccount extends Account {
    private Date expiryDate;
    private CreditCardType type;

    public CreditCardAccount(String accountNum, double currentBalance, ICustomer customer) {
        super(accountNum, currentBalance, customer);
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public CreditCardType getType() {
        return type;
    }

    public void setType(CreditCardType type) {
        this.type = type;
    }
}