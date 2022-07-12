package creditcard.factories;

import creditcard.models.CreditCardAccount;
import creditcard.models.CreditCardType;
import framework.models.Account;
import framework.models.Customer;

public class CCardAccountFactory {

    public static Account createAccount(Customer customer, String accountNum, CreditCardType type) {
        return createAccount(customer, accountNum, type, null);
    }

    public static Account createAccount(Customer customer, String accountNum, CreditCardType type, String expiryDate) {
       if (expiryDate == null)
           return new CreditCardAccount(customer, accountNum, type);
       return new CreditCardAccount(customer, accountNum, type, expiryDate);
    }
}
