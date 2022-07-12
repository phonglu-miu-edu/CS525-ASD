package creditcard.factories;

import creditcard.models.CreditCardAccount;
import creditcard.models.CreditCardType;
import framework.models.ICustomer;

public class CCardAccountFactory {
    public static CreditCardAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate) {
        return new CreditCardAccount(customer, accountNum, type, expiryDate);
    }
}
