package framework.model;


public class DepositEntry extends Entry {

    public DepositEntry(IAccount account, double amount) {
        super(account, amount);
    }

    @Override
    public void process() {

    }
}
