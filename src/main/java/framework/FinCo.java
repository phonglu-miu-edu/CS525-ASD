package framework;

import framework.factory.SimpleFactory;
import framework.model.*;
import framework.reports.IReport;
import framework.repository.IRepository;
import framework.repository.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class FinCo implements IFinCo {

    public Collection<IAccount> accounts = new ArrayList<>();
    public Collection<ICustomer> customers = new ArrayList<>();
    public IFramework frameworkApplication;
    private IReport report;
    protected IRepository repository;

    public FinCo(IRepository repository) {
        this.repository = repository;
    }

    public FinCo() {
        this.repository = new Repository(this);
        repository.setRepoPath("db.json");
    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
    }

    @Override
    public Collection<IAccount> getAccounts() {
        return accounts;
    }

    @Override
    public Collection<ICustomer> getCustomers() {
        return customers;
    }

    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @Override
    public ICustomer createCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        ICustomer customer = SimpleFactory.getCustomer(name, street, city, state, zip, email, birthDate);

        this.customers.add(customer);

        return customer;
    }

    @Override
    public ICustomer createCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        ICustomer customer = SimpleFactory.getCompany(name, street, city, state, zip, email, noEmployees);

        this.customers.add(customer);

        return customer;
    }


    @Override
    public IAccount createAccount(ICustomer customer, String accountNum) {
        IAccount account = SimpleFactory.createAccount(customer, accountNum);
        this.accounts.add(account);
        return account;
    }

    @Override
    public IAccount createAccount(ICustomer customer, String accountNum, Integer type) {
        return null;
    }

    @Override
    public void generateReport()  {
        this.report.generate();
    }

    @Override
    public void setReport(IReport report)  {
        this.report = report;
    }

    @Override
    public void addInterest() {
        for (IAccount acc : accounts) {
            acc.addInterest();
        }

        repository.write(repository.getRepoPath());
    }

    @Override
    public Entry withdraw(IAccount account, double amount) {
        account.changeBalanceByAmount(-1 * amount);
        Entry entry = new WithdrawEntry(account, amount);
        account.addEntryHistory(entry);

        String message = "Withdrawal amount of " + amount + " is made at "
                + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        sendNotification(message, account, amount);

        repository.write(repository.getRepoPath());

        return entry;
    }

    @Override
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

        this.repository.write(repository.getRepoPath());

        return entry;
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
}
