package framework;

import framework.database.IRepository;
import framework.models.*;
import framework.reports.IReport;

import java.util.Collection;

public interface IFinCo {
    void setFrameworkApplication(IFramework frameworkApplication);

    Collection<Account> getAccounts();

    Collection<Customer> getCustomers();

    public IRepository getRepository();

    Customer createPerson(String name, String street, String city, String state, Integer zip, String email, String birthDate);

    Customer createCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    Account createAccount(ICustomer customer, String accountNum);

    Account createAccount(ICustomer customer, String accountNum, Integer type);

    void generateReport();

    void setReport(IReport report);

    void addInterest();

    Entry withdraw(Account account, double amount);

    Entry deposit(Account account, double amount);
}
