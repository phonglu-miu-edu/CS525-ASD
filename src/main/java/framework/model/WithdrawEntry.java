package framework.model;

import java.util.Date;

public class WithdrawEntry extends Entry{

    public WithdrawEntry(Account account, double amount) {
        super(account, amount);
    }


    @Override
    public void process() {

    }
}
