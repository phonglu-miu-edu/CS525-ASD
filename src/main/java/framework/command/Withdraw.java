package framework.command;

import framework.IFinCo;
import framework.model.Account;
import framework.model.Entry;

public class Withdraw implements ICommand {

    private Account account;
    private double amount;
    private IFinCo finCo;
    private Entry entry;
    @Override
    public void execute() {
        Entry entry = finCo.withdraw(account, amount);

        this.entry = entry;
    }
}
