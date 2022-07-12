package framework;

import framework.factory.AccountFactory;
import framework.factory.CustomerFactory;
import framework.model.*;
import framework.repository.IRepository;
import framework.repository.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class FinCo implements IFinCo {

    public Collection<Account> accounts = new ArrayList<>();
    public Collection<Customer> customers = new ArrayList<>();
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
    public Collection<Account> getAccounts() {
        return accounts;
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customers;
    }

    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    @Override
    public Customer createCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        Customer customer = CustomerFactory.getPerson(name, street, city, state, zip, email, birthDate);

        this.customers.add(customer);

        return customer;
    }

    @Override
    public Customer createOrganization(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        Customer customer = CustomerFactory.getCompany(name, street, city, state, zip, email, noEmployees);

        this.customers.add(customer);

        return customer;
    }


    @Override
    public Account createAccount(Customer customer, String accountNum) {
        Account account = AccountFactory.createAccount(customer, accountNum);
        this.accounts.add(account);
        return account;
    }

    @Override
    public Account createAccount(Customer customer, String accountNum, Integer type) {
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
        for (Account acc : accounts) {
            acc.addInterest();
        }

        repository.write(repository.getRepoPath());
    }

    @Override
    public Entry withdraw(Account account, double amount) {
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
    public Entry deposit(Account account, double amount) {
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

    public void sendNotification(String message, Account account, double amount) {
        ICustomer customer = account.getCustomer();
        if (account.getNotification() != null) {
            if (customer instanceof Organization) {
                account.getNotification().sendNotification(message);
            } else if (amount > 400 || account.getCurrentBalance() < 0) {
                account.getNotification().sendNotification(message);
            }
        }
    }
}
