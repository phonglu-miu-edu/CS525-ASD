package framework.command;

import framework.IFinCo;
import framework.model.IAccount;
import framework.model.IEntry;

public class Withdraw implements ICommand {
    private IAccount account;
    private double amount;
    private IFinCo finCo;
    private IEntry entry;

    public Withdraw(IAccount account, double amount, IFinCo finCo) {
        this.account = account;
        this.amount = amount;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        IEntry entry = finCo.withdraw(account, amount);

        this.entry = entry;
    }

    public IEntry getEntry() {
        return entry;
    }
}
