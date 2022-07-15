package framework.command;

import framework.FinCo;
import framework.model.Entry;
import framework.model.IAccount;

public class Withdraw implements ICommand {
    private IAccount account;
    private double amount;
    private FinCo finCo;
    private Entry entry;

    public Withdraw(IAccount account, double amount, FinCo finCo) {
        this.account = account;
        this.amount = amount;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        this.entry = finCo.withdraw(account, amount);
    }

    public Entry getEntry() {
        return entry;
    }
}
