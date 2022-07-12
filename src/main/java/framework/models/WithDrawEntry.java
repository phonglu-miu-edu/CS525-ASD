package framework.models;

public class WithDrawEntry extends Entry {
    public WithDrawEntry(Account account, double amount) {
        super(account, amount);
    }

    @Override
    public void process() {

    }
}
