package ccard;

import ccard.model.CreditCardType;
import framework.IFinCo;
import framework.model.Account;
import framework.model.Customer;
import framework.model.IAccount;
import framework.model.ICustomer;

public interface ICCardFinCo extends IFinCo {
    IAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate);
}
