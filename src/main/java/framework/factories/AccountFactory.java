package framework.factories;

import framework.models.Account;
import framework.models.Customer;

public class AccountFactory {
    public static Account createAccount(Customer customer, String accountNum) {
        return new Account(customer, accountNum);
    }
}
