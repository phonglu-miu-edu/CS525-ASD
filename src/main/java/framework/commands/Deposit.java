package framework.commands;

import framework.IFinco;
import framework.models.Account;
import framework.models.Entry;

public class Deposit implements ICommand {
    private Account account;
    private double amount;
    private IFinco finCo;
    private Entry entry;

    public Deposit(Account account, double amount, IFinco finCo) {
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
