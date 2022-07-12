package framework.commands;

import framework.IFinco;
import framework.models.Account;
import framework.models.Entry;

public class Withdraw implements ICommand {
    private Account account;
    private double amount;
    private IFinco finCo;
    private Entry entry;

    public Withdraw(Account account, double amount, IFinco finCo) {
        this.account = account;
        this.amount = amount;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        Entry entry = finCo.withdraw(account, amount);

        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }
}