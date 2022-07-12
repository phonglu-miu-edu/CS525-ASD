package framework.command;

import framework.IFinCo;
import framework.model.Account;
import framework.model.Customer;

public class AddAccount implements ICommand {
    private final Customer customer;
    private final IFinCo finCo;
    private Account account;
    private String accountNum;

    public AddAccount(Customer customer, String accountNum, IFinCo finCo) {
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
