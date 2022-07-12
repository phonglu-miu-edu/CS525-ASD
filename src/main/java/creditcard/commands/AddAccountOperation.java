package creditcard.commands;

import creditcard.ICCardFinCo;
import creditcard.models.CreditCardType;
import framework.commands.IOperation;
import framework.models.Account;
import framework.models.ICustomer;

public class AddAccountOperation implements IOperation {
    private final ICustomer customer;
    private String expiryDate;
    private final ICCardFinCo cCardFinCo;
    private Account account;
    private String accountNum;
    private CreditCardType type;

    public AddAccountOperation(ICustomer customer, String accountNum, CreditCardType type, String expiryDate, ICCardFinCo cCardFinCo) {
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

    public Account getAccount() {
        return this.account;
    }
}
