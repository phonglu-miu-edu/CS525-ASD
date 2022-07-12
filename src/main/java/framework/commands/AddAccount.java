package framework.commands;

import framework.IFinco;
import framework.models.Account;
import framework.models.Customer;

public class AddAccount implements ICommand {

    private final Customer customer;
    private final IFinco finCo;
    private Account account;
    private String accountNum;

    public AddAccount(Customer customer, String accountNum, IFinco finCo) {
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
