package ccard.command;

import ccard.ICCardFinCo;
import ccard.model.CreditCardType;
import framework.command.ICommand;
import framework.model.IAccount;
import framework.model.ICustomer;

public class AddAccount implements ICommand {
    private final ICustomer customer;
    private String expiryDate;
    private final ICCardFinCo cCardFinCo;
    private IAccount account;
    private String accountNum;
    private CreditCardType type;

    public AddAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate, ICCardFinCo cCardFinCo) {
        this.customer = customer;
        this.expiryDate = expiryDate;
        this.cCardFinCo = cCardFinCo;
        this.accountNum = accountNum;
        this.type = type;
    }

    @Override
    public void execute() {
        account = this.cCardFinCo.createAccount(customer, accountNum, type, expiryDate);
    }

    public IAccount getAccount() {
        return this.account;
    }
}
