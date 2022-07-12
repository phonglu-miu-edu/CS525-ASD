package banking;

import banking.factories.BankACFactory;
import framework.models.Account;
import framework.models.Customer;
import framework.models.Email;

public class FinCo extends framework.FinCo {
    public FinCo() {
        super();
    }

    public Account createAccount(Customer customer, String accountNum, Integer type) {
        Account account = BankACFactory.createAccount(customer, accountNum, type);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.accounts.add(account);
        customer.addAccount(account);
        return account;
    }
}
