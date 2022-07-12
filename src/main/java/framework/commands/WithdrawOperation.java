package framework.commands;

import framework.IFinCo;
import framework.models.Account;
import framework.models.Entry;

public class WithdrawOperation implements IOperation {
    private Account account;
    private double amount;
    private IFinCo finCo;
    private Entry entry;

    public WithdrawOperation(Account account, double amount, IFinCo finCo) {
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
