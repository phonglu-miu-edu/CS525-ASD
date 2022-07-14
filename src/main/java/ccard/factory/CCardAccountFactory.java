package ccard.factory;

import ccard.model.CreditCardAccount;
import ccard.model.CreditCardType;
import framework.model.IAccount;
import framework.model.ICustomer;

public class CCardAccountFactory {
    public static IAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate) {
        return new CreditCardAccount(customer, accountNum, type, expiryDate);
    }
}
