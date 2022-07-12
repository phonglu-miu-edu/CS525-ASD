package framework.factory;

import framework.model.Account;
import framework.model.IAccount;
import framework.model.ICustomer;

public class AccountFactory {
    public static IAccount createAccount(ICustomer customer, String accountNum) {
        return new Account(customer, accountNum);
    }
}
