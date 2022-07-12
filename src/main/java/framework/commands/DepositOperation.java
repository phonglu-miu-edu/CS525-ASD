package framework.commands;

import framework.IFinCo;
import framework.models.Account;
import framework.models.Entry;

public class DepositOperation implements IOperation {
    private Account account;
    private double amount;
    private IFinCo finCo;
    private Entry entry;

    public DepositOperation(Account account, double amount, IFinCo finCo) {
        this.account = account;
        this.amount = amount;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        check();

        entry = finCo.deposit(account, amount);
    }

    public void check() {
        // here other class override it.
    }

    public Entry getEntry() {
        return entry;
    }
}
