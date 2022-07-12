package framework.command;

import framework.IFinCo;
import framework.model.IAccount;
import framework.model.IEntry;

public class Deposit implements ICommand {
    private IAccount account;
    private double amount;
    private IFinCo finCo;
    private IEntry entry;

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

    public IEntry getEntry() {
        return entry;
    }
}
