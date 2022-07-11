package ccard;

import ccard.framework.ICustomer;
import ccard.framework.IOperator;

public class AddAccount implements IOperator {
    private String accountNum;
    private double currentBalance;
    private ICustomer customer;

    public AddAccount(String accountNum, double currentBalance, ICustomer customer) {
        this.accountNum = accountNum;
        this.currentBalance = currentBalance;
        this.customer = customer;
    }

    @Override
    public void execute() {
        CCardAccountFactory.createAccount(this.accountNum, this.currentBalance, this.customer);
    }
}