package creditcard;

import creditcard.models.CreditCardType;
import framework.IFinCo;
import framework.models.Account;
import framework.models.ICustomer;

public interface ICCardFinCo extends IFinCo {
    Account createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate);
}
