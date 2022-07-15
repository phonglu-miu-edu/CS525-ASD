package ccard;

import ccard.database.CreditCardRepository;
import ccard.factory.CCardAccountFactory;
import ccard.model.CreditCardType;
import framework.FinCo;
import framework.model.*;
import framework.repository.IRepository;
import framework.repository.Repository;
import framework.view.FinCoViewController;
import framework.view.ViewType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CCardFinCo extends FinCo {
    public IAccount createAccount(ICustomer customer, String accountNum, CreditCardType type, String expiryDate) {
        IAccount account = CCardAccountFactory.createAccount(customer, accountNum, type, expiryDate);
        account.setNotification(new Email(account.getCustomer().getEmail()));
        this.getAccounts().add(account);
        customer.addAccount(account);
        return account;
    }

    @Override
    public Entry withdraw(IAccount account, double amount) {
        account.changeBalanceByAmount(-1 * amount);
        Entry entry = new WithdrawEntry(account, amount);
        account.addEntryHistory(entry);

        if (amount > 600) {
            String message = "Withdrawal amount of " + amount + " is made at "
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
            sendNotification(message, account, amount);
        }

        this.repository.write();

        return entry;
    }

    @Override
    public Entry deposit(IAccount account, double amount) {
        account.changeBalanceByAmount(amount);
        Entry entry = new DepositEntry(account, amount);
        account.addEntryHistory(entry);

        if (account.getCustomer() instanceof Company) {
            String message = "Deposit amount of " + amount + " is made at "
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
            sendNotification(message, account, amount);
        }

        this.repository.write();

        return entry;
    }
    public static void main(String[] args) {
        CCardFinCo finCo = new CCardFinCo();

        IRepository repository = new CreditCardRepository(finCo, "db-ccard.json");
        finCo.setRepository(repository);

        FinCoViewController viewController = new FinCoViewController(ViewType.CCARD);
        finCo.setViewController(viewController);

        viewController.setVisible();
    }
}

