package ccard.command;

import ccard.ICCardFinCo;
import framework.IFinCo;
import framework.command.ICommand;
import framework.model.IAccount;
import framework.model.IEntry;

public class Withdraw implements ICommand {
    private IAccount account;
    private double amount;
    private ICCardFinCo finCo;
    private IEntry entry;

    public Withdraw(IAccount account, double amount, ICCardFinCo finCo) {
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
