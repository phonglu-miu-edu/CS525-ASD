package banking.models;

import framework.models.Account;
import framework.models.Customer;

public class Checking extends Account {

    public Checking(Customer customer, String accountNum) {
        super(customer, accountNum);
        interestRate = -0.01;
    }
}
