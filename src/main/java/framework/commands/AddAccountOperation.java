package framework.commands;

import framework.IFinCo;
import framework.models.Account;
import framework.models.Customer;

public class AddAccountOperation implements IOperation {

    private final Customer customer;
    private final IFinCo finCo;
    private Account account;
    private String accountNum;

    public AddAccountOperation(Customer customer, String accountNum, IFinCo finCo) {
        this.customer = customer;
        this.finCo = finCo;
        this.accountNum = accountNum;
    }

    @Override
    public void execute() {
        account = this.finCo.createAccount(customer, accountNum);
    }

    public Account getAccount() {
        return this.account;
    }
}
