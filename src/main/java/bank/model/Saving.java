package bank.model;

import framework.model.Account;
import framework.model.ICustomer;

public class Saving extends Account {
    public Saving(ICustomer customer, String accountNum) {
        super(customer, accountNum);
        interestRate = 0.02;
    }
}
