package banking.commands;

import framework.IFinco;
import framework.commands.IOperation;
import framework.models.Account;
import framework.models.Customer;

public class AddAccount implements IOperation {
    private final Customer customer;
    private String accountNum;
    private Integer type;
    private final IFinco finCo;

    private Account account;

    public AddAccount(Customer c, String a, Integer t, IFinco f) {
        this.customer = c;
        this.finCo = f;
        this.accountNum = a;
        this.type = t;
    }

    @Override
    public void execute() {
        account = this.finCo.createAccount(customer, accountNum, type);
    }

    public Account getAccount() {
        return this.account;
    }
}
