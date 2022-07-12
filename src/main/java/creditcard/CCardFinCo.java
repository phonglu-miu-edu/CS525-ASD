package creditcard;

import creditcard.database.CreditCardRepository;
import creditcard.factories.CCardAccountFactory;
import creditcard.models.CreditCardAccount;
import creditcard.models.CreditCardType;
import framework.FinCo;
import framework.models.Email;
import framework.models.ICustomer;

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

