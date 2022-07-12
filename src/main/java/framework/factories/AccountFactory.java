package framework.factories;

import framework.models.Account;
import framework.models.ICustomer;

public class AccountFactory {
    public static Account createAccount(ICustomer customer, String accountNum) {
        return new Account(customer, accountNum);
    }
}
