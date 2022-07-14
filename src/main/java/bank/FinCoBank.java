package bank;

import bank.database.BankRepository;
import bank.factory.BankACFactory;
import framework.FinCo;
import framework.model.Email;
import framework.model.IAccount;
import framework.model.ICustomer;

public class FinCoBank extends FinCo {
    public FinCoBank() {
        super();

        this.repository = new BankRepository(this);
        this.repository.setRepoPath("db-bank.json");
    }

    public IAccount createAccount(ICustomer customer, String accountNum, Integer type) {
        IAccount account = BankACFactory.createAccount(customer, accountNum, type);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.accounts.add(account);
        customer.addAccount(account);
        return account;
    }
}
