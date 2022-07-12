package banking.models;

import framework.models.Account;
import framework.models.Customer;

public class Saving extends Account {
    public Saving(Customer customer, String accountNum) {
        super(customer, accountNum);
        interestRate = -0.03;
    }
}
