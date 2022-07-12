package framework.view;

import framework.IFramework;
import framework.command.*;
import framework.factory.AccountFactory;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Entry;

import javax.swing.*;
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
        fincoView = new FinCoView(this, type);

        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
            fincoView.setVisible(true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    @Override
    public IFramework getFrameworkApplication() {
        return frameworkApplication;
    }

    @Override
    public Customer createCustomer(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
            AddCustomer addCustomer = new AddCustomer(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    birthDate,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getCommandManager().invoke(addCustomer);

            customer = addCustomer.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.getFrameworkApplication().getFinCo().getRepository().write(null);

        return customer;
    }

    @Override
    public Customer createOrg(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
            AddOrganization addOrganization = new AddOrganization(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    noEmployees,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getCommandManager().invoke(addOrganization);

            customer = addOrganization.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.getFrameworkApplication().getFinCo().getRepository().write(null);

        return customer;
    }

    @Override
    public Collection<Customer> getCustomers() {
        return frameworkApplication.getFinCo().getCustomers();
    }

    @Override
    public Collection<Account> getAccounts() {
        return frameworkApplication.getFinCo().getAccounts();
    }

    @Override
    public void report() {
        ReportGenerate cmd = new ReportGenerate(frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(cmd);

    }

    @Override
    public void interest() {
        AddInterest cmd = new AddInterest(frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(cmd);
    }

    @Override
    public Customer findCustomerByName(String name) {
        for (Customer customer : frameworkApplication.getFinCo().getCustomers()) {
            if (customer.getName() == name) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Entry deposit(Account account, double amount) {
        Deposit cmd = new Deposit(account, amount, frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(cmd);
        return cmd.getEntry();
    }

    @Override
    public Entry withdraw(Account account, double amount) {
        Withdraw cmd = new Withdraw(account, amount, frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(cmd);
        return cmd.getEntry();
    }

    @Override
    public Account createAccount(Customer customer, String accountNum) {
        Account account = AccountFactory.createAccount(customer, accountNum);

        customer.addAccount(account);
        this.getAccounts().add(account);

        return account;
    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
    }
}
