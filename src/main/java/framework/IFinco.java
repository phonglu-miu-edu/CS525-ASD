package framework;
import creditcard.models.CreditCardType;
import framework.database.IRepository;
import framework.models.Account;
import framework.models.Customer;
import framework.models.Entry;
import framework.reports.IReport;

import java.util.Collection;

public interface IFinco {
    void setFrameworkApplication(IFramework frameworkApplication);

    Collection<Account> getAccounts();

    Collection<Customer> getCustomers();

    public IRepository getRepository();

    Customer createPerson(String name, String street, String city, String state, Integer zip, String email, String birthDate);

    Customer createCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    Account createAccount(Customer customer, String accountNum);

    Account createAccount(Customer customer, String accountNum, Integer type);

    Account createAccount(Customer customer, String accountNum, CreditCardType type, String expiryDate);

    Account createAccount(Customer customer, String accountNum, CreditCardType type);

    void generateReport();

    void setReport(IReport report);

    void addInterest();

    Entry withdraw(Account account, double amount);

    Entry deposit(Account account, double amount);
}
