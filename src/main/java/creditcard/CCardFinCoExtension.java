package creditcard;

import creditcard.database.CreditCardRepository;
import creditcard.factories.CCardAccountFactory;
import creditcard.models.CreditCardType;
import framework.FinCo;
import framework.models.Account;
import framework.models.Customer;
import framework.models.Email;

public class CCardFinCoExtension extends FinCo {
    public CCardFinCoExtension() {
        super();
    }

    @Override
    public Account createAccount(Customer customer, String accountNum, CreditCardType type, String expiryDate) {
        Account account = CCardAccountFactory.createAccount(customer, accountNum, type, expiryDate);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.accounts.add(account);
        customer.addAccount(account);
        return account;
    }

    @Override
    public Account createAccount(Customer customer, String accountNum, CreditCardType type) {
        Account account = CCardAccountFactory.createAccount(customer, accountNum, type);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.accounts.add(account);
        customer.addAccount(account);
        return account;
    }
}
