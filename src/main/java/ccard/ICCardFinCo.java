package ccard;

import ccard.model.CreditCardAccount;
import ccard.model.CreditCardType;
import framework.IFinCo;
import framework.model.ICustomer;

public interface ICCardFinCo extends IFinCo {
    CreditCardAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate);
}
