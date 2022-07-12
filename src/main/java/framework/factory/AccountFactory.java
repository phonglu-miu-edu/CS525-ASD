package framework.factory;

import framework.model.Account;
import framework.model.Customer;

public class AccountFactory {
    public static Account createAccount(Customer customer, String accountNum) {
        return new Account(customer, accountNum);
    }
}
