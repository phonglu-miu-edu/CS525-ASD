package ccard;

import ccard.framework.ICustomer;

public class CCardAccountFactory {
    public static CreditCardAccount createAccount(String accountNum, double currentBalance, ICustomer customer) {
        CreditCardFinCo creditCardFinCo = new CreditCardFinCo();
        return creditCardFinCo.createAccount(accountNum, currentBalance, customer);
    }
}
