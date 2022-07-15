package bank;

import bank.database.BankRepository;
import bank.factory.BankACFactory;
import framework.FinCo;
import framework.model.Email;
import framework.model.IAccount;
import framework.model.ICustomer;
import framework.repository.IRepository;
import framework.repository.Repository;
import framework.view.FinCoViewController;
import framework.view.ViewType;

public class FinCoBank extends FinCo {
    public IAccount createAccount(ICustomer customer, String accountNum, Integer type) {
        IAccount account = BankACFactory.createAccount(customer, accountNum, type);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.getAccounts().add(account);
        customer.addAccount(account);
        return account;
    }

    public static void main(String[] args) {
        FinCoBank finCo = new FinCoBank();

        IRepository repository = new BankRepository(finCo, "db-bank.json");
        finCo.setRepository(repository);

        FinCoViewController viewController = new FinCoViewController(ViewType.BANK);
        finCo.setViewController(viewController);

        viewController.setVisible();
    }
}
