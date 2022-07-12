package bank;

import bank.database.BankRepository;
import bank.factory.BankAccountFactory;
import framework.FinCo;
import framework.model.Email;
import framework.model.IAccount;
import framework.model.ICustomer;

public class FinCoExtension extends FinCo {
    public FinCoExtension() {
        super();

        this.repository = new BankRepository(this);
        this.repository.setRepoPath("db-bank.json");
    }

    public IAccount createAccount(ICustomer customer, String accountNum, Integer type) {
        IAccount account = BankAccountFactory.createAccount(customer, accountNum, type);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.accounts.add(account);
        customer.addAccount(account);
        return account;
    }
}
