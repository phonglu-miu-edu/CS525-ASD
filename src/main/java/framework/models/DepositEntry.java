package framework.models;

public class DepositEntry extends Entry {
    public DepositEntry(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void process() {

    }
}
