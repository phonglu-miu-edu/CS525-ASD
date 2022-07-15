package framework;

import framework.command.CommandManager;
import framework.factory.SimpleFactory;
import framework.model.*;
import framework.reports.IReport;
import framework.repository.IRepository;
import framework.repository.Repository;
import framework.view.FinCoViewController;
import framework.view.IFinCoViewController;
import framework.view.ViewType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class FinCo {
    protected IFinCoViewController viewController;
    protected CommandManager commandManager;
    protected IRepository repository;
    protected IReport report;

    private Collection<IAccount> accounts = new ArrayList<>();
    private Collection<ICustomer> customers = new ArrayList<>();

    protected FinCo() {
        this.commandManager = new CommandManager();
    }

    public Collection<IAccount> getAccounts() {
        return this.accounts;
    }

    public Collection<ICustomer> getCustomers() {
        return this.customers;
    }

    public IRepository getRepository() {
        return this.repository;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public ICustomer createCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        ICustomer customer = SimpleFactory.getCustomer(name, street, city, state, zip, email, birthDate);
        this.customers.add(customer);
        return customer;
    }

    public ICustomer createCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        ICustomer customer = SimpleFactory.getCompany(name, street, city, state, zip, email, noEmployees);
        this.customers.add(customer);
        return customer;
    }

    public IAccount createAccount(ICustomer customer, String accountNum) {
        IAccount account = SimpleFactory.createAccount(customer, accountNum);
        this.accounts.add(account);
        return account;
    }

    public IAccount createAccount(ICustomer customer, String accountNum, Integer type) {
        return null;
    }

    public void generateReport()  {
        this.report.generate();
    }

    public void setReport(IReport report)  {
        this.report = report;
    }

    public void addInterest() {
        for (IAccount acc : this.accounts) {
            acc.addInterest();
        }

        this.repository.write();
    }

    public Entry withdraw(IAccount account, double amount) {
        account.changeBalanceByAmount(-1 * amount);
        Entry entry = new WithdrawEntry(account, amount);
        account.addEntryHistory(entry);

        String message = "Withdrawal amount of " + amount + " is made at "
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        sendNotification(message, account, amount);

        this.repository.write();

        return entry;
    }

    public Entry deposit(IAccount account, double amount) {
        if (amount < 0) {
            return null;
        }

        account.changeBalanceByAmount(amount);
        Entry entry = new DepositEntry(account, amount);
        account.addEntryHistory(entry);

        String message = "Deposit amount of " + amount + " is made at "
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        sendNotification(message, account, amount);

        this.repository.write();

        return entry;
    }

    public void setAccounts(Collection<IAccount> accounts) {
        this.accounts = accounts;
    }

    public void setCustomers(Collection<ICustomer> customers) {
        this.customers = customers;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
        this.repository.load();
    }

    public void setViewController(IFinCoViewController viewController) {
        viewController.setFinCo(this);
        this.viewController = viewController;
    }

    public void sendNotification(String message, IAccount account, double amount) {
        ICustomer customer = account.getCustomer();
        if (account.getNotification() != null) {
            if (customer instanceof Company) {
                account.getNotification().sendNotification(message);
            } else if (amount > 400 || account.getCurrentBalance() < 0) {
                account.getNotification().sendNotification(message);
            }
        }
    }

    public static void main(String[] args) {
        FinCo finCo = new FinCo();

        IRepository repository = new Repository(finCo, "db.json");
        finCo.setRepository(repository);

        FinCoViewController viewController = new FinCoViewController(ViewType.FRAMEWORK);
        finCo.setViewController(viewController);

        viewController.setVisible();
    }
}
