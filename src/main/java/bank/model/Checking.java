package bank.model;

import framework.model.Account;
import framework.model.ICustomer;

public class Checking extends Account {

    public Checking(ICustomer customer, String accountNum) {
        super(customer, accountNum);
        interestRate = 0.01;
    }
}
