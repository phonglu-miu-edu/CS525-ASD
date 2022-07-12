package framework.model;

public class WithdrawEntry extends Entry {

    public WithdrawEntry(IAccount account, double amount) {
        super(account, amount);
    }

    @Override
    public void process() {

    }
}
