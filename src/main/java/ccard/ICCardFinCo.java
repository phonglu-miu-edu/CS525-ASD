package ccard;

import ccard.model.CreditCardType;
import framework.IFinCo;
import framework.model.*;

public interface ICCardFinCo extends IFinCo {
    IAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate);
}
