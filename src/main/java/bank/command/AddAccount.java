package bank.command;

import framework.IFinCo;
import framework.command.ICommand;
import framework.model.IAccount;
import framework.model.ICustomer;

public class AddAccount implements ICommand {
    private final ICustomer customer;
    private String accountNum;
    private Integer type;
    private final IFinCo finCo;

    private IAccount account;

    public AddAccount(ICustomer customer, String accountNum, Integer type, IFinCo finCo) {
        this.customer = customer;
        this.accountNum = accountNum;
        this.type = type;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        account = this.finCo.createAccount(customer, accountNum, type);
    }

    public IAccount getAccount() {
        return account;
    }
}
