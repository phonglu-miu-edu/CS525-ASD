package framework.command;

import framework.IFinCo;
import framework.model.Account;
import framework.model.Customer;
import framework.model.IAccount;
import framework.model.ICustomer;

public class AddAccount implements ICommand {
    private final ICustomer customer;
    private final IFinCo finCo;
    private IAccount account;
    private String accountNum;

    public AddAccount(ICustomer customer, String accountNum, IFinCo finCo) {
        this.customer = customer;
        this.finCo = finCo;
        this.accountNum = accountNum;
    }

    @Override
    public void execute() {
        account = this.finCo.createAccount(customer, accountNum);
    }

    public IAccount getAccount() {
        return this.account;
    }
}
