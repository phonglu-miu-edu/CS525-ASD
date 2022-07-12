package framework;

import ccard.CreditCardType;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Entry;
import framework.model.IReport;
import framework.repository.IRepository;

import java.util.Collection;

public interface IFinco {

    void setFrameworkApplication(IFramework frameworkApplication);

    Collection<Account> getAccounts();

    Collection<Customer> getCustomers();

    public IRepository getRepository();

    Customer createCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate);

    Customer createOrganization(String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    Account createAccount(Customer customer, String accountNum);

    Account createAccount(Customer customer, String accountNum, Integer type);

    Account createAccount(Customer customer, String accountNum, CreditCardType type, String expiryDate);

    Account createAccount(Customer customer, String accountNum, CreditCardType type);

    void report();

    void setReport(IReport report);

    void interest();

    Entry withdraw(Account account, double amount);

    Entry deposit(Account account, double amount);
}
