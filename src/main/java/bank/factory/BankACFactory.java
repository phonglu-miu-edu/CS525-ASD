package bank.factory;

import bank.model.Checking;
import bank.model.Saving;
import framework.model.IAccount;
import framework.model.ICustomer;

public class BankACFactory {
    public static IAccount createAccount(ICustomer customer, String accountNum, Integer type) {
        if (type == 1) {
            return new Checking(customer, accountNum);
        }  else {
            return new Saving(customer, accountNum);
        }
    }
}
