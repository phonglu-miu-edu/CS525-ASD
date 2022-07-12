package ccard;

import ccard.database.CreditCardRepository;
import ccard.factory.CCardAccountFactory;
import ccard.model.CreditCardAccount;
import ccard.model.CreditCardType;
import framework.FinCo;
import framework.model.Email;
import framework.model.ICustomer;

public class CCardFinCo extends FinCo implements ICCardFinCo {
    public CCardFinCo() {
        super();

        this.repository = new CreditCardRepository(this);
        this.repository.setRepoPath("db-ccard.json");
    }

    public CreditCardAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate) {
        CreditCardAccount account = CCardAccountFactory.createAccount(customer, accountNum, type, expiryDate);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.accounts.add(account);
        customer.addAccount(account);
        return account;
    }
}

