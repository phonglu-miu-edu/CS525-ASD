package ccard.command;

import ccard.CCardFinCo;
import framework.command.ICommand;
import framework.model.Entry;
import framework.model.IAccount;

public class Deposit implements ICommand {
    private IAccount account;
    private double amount;
    private CCardFinCo finCo;
    private Entry entry;

    public Deposit(IAccount account, double amount, CCardFinCo finCo) {
        this.account = account;
        this.amount = amount;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        entry = finCo.deposit(account, amount);
    }

    public Entry getEntry() {
        return entry;
    }
}
