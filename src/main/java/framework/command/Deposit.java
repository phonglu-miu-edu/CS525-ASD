package framework.command;

import framework.IFinco;
import framework.model.Account;
import framework.model.Entry;

public class Deposit implements ICommand{

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
        Entry entry = finCo.withdraw(account, amount);

        this.entry = entry;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public IFinco getFinCo() {
        return finCo;
    }

    public void setFinCo(IFinco finCo) {
        this.finCo = finCo;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
