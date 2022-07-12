package framework.view;

import framework.IFramework;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Entry;

import java.util.Collection;

public class FincoViewController implements IFincoViewController{
    public IFramework frameworkApplication;
    public FinCoView fincoView;
    public ViewType type;


    public FincoViewController(ViewType type) {
        this.type = type;
    }

    @Override
    public void setVisible() {

    }

    @Override
    public IFramework getFrameworkApplication() {
        return null;
    }

    @Override
    public Customer createCustomer(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        return null;
    }

    @Override
    public Customer createOrg(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        return null;
    }

    @Override
    public Collection<Customer> getCustomers() {
        return null;
    }

    @Override
    public Collection<Account> getAccounts() {
        return null;
    }

    @Override
    public void report() {

    }

    @Override
    public void interest() {

    }

    @Override
    public Customer findCustomerByName(String name) {
        return null;
    }

    @Override
    public Entry deposit(Account account, double amount) {
        return null;
    }

    @Override
    public Entry withdraw(Account account, double amount) {
        return null;
    }

    @Override
    public Account createAccount(Customer customer, String accountNum) {
        return null;
    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {

    }
}
