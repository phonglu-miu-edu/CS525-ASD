package creditcard.commands;

import creditcard.models.CreditCardType;
import framework.commands.ICommand;
import framework.IFinco;
import framework.models.Account;
import framework.models.Customer;

public class AddAccountCommand implements ICommand {

    private final Customer customer;
    private String expiryDate;
    private final IFinco finCo;
    private Account account;
    private String accountNum;
    private CreditCardType type;

    public AddAccountCommand(Customer customer, String accountNum, CreditCardType type, String expiryDate, IFinco finCo) {
        this.customer = customer;
        this.expiryDate = expiryDate;
        this.finCo = finCo;
        this.accountNum = accountNum;
        this.type = type;
    }

    @Override
    public void execute() {
        account = this.finCo.createAccount(customer, accountNum, type, expiryDate);
    }

    public Account getAccount() {
        return this.account;
    }
}
