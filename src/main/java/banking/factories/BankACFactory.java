package banking.factories;

import banking.models.Checking;
import banking.models.Saving;
import framework.factories.AccountFactory;
import framework.models.Account;
import framework.models.Customer;

public class BankACFactory extends AccountFactory {
    public static Account createAccount(Customer customer, String accountNum, Integer type) {
        if (type == 1) {
            return new Checking(customer, accountNum);
        }  else {
            return new Saving(customer, accountNum);
        }
    }
}
