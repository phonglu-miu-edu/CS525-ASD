package framework.view;

import framework.IFramework;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Entry;

import java.util.Collection;

public class FinCoViewController implements IFinCoViewController{
    public IFramework frameworkApplication;
    public FinCoView finCoView;
    public ViewType viewType;

    public FinCoViewController(ViewType viewType) {
        this.viewType = viewType;
    }

    public void setVisible() {
        finCoView = new FinCoView(this, viewType);

        try {
            finCoView.setVisible(true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    public IFramework getFrameworkApplication() {
        return frameworkApplication;
    }

    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
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
    public void addInterest() {

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
}
