package framework;

import framework.model.*;
import framework.reports.IReport;
import framework.repository.IRepository;

import java.util.Collection;

public interface IFinCo {

    void setFrameworkApplication(IFramework frameworkApplication);

    Collection<IAccount> getAccounts();

    Collection<ICustomer> getCustomers();

    public IRepository getRepository();

    ICustomer createCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate);

    ICustomer createCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    IAccount createAccount(ICustomer customer, String accountNum);

    IAccount createAccount(ICustomer customer, String accountNum, Integer type);

    void generateReport();

    void setReport(IReport report);

    void addInterest();

    Entry withdraw(IAccount account, double amount);

    Entry deposit(IAccount account, double amount);
}
