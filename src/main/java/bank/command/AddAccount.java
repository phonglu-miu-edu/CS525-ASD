package bank.command;

import framework.IFinCo;
import framework.command.ICommand;
import framework.model.IAccount;
import framework.model.ICustomer;

public class AddAccount implements ICommand {
    private final ICustomer customer;
    private Integer type;
    private final IFinCo finCo;
    private IAccount account;
    private String accountNum;

    public AddAccount(ICustomer customer, String accountNum, Integer type, IFinCo finCo) {
        this.customer = customer;
        this.type = type;
        this.finCo = finCo;
        this.accountNum = accountNum;
        this.type = type;
    }

    @Override
    public void execute() {
        account = this.finCo.createAccount(customer, accountNum, type);
    }

    public IAccount getAccount() {
        return this.account;
    }
}
