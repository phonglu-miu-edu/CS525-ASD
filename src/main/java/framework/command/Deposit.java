package framework.command;

import framework.IFinCo;
import framework.model.Entry;
import framework.model.IAccount;

public class Deposit implements ICommand {
    private IAccount account;
    private double amount;
    private IFinCo finCo;
    private Entry entry;

    public Deposit(IAccount account, double amount, IFinCo finCo) {
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
