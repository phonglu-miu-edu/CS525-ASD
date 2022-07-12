package framework.views;

import framework.IFramework;
import framework.commands.*;
import framework.factories.AccountFactory;
import framework.models.Account;
import framework.models.Customer;
import framework.models.Entry;

import javax.swing.*;
import java.util.Collection;

public class FincoViewController implements IFincoViewController {
    public IFramework frameworkApplication;
    public FinCoView fincoView;
    public VIEW_TYPE view_type;


    public FincoViewController(VIEW_TYPE view_type) {
        this.view_type = view_type;
    }

    public void setVisible() {
        fincoView = new FinCoView(this, view_type);

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

    public IFramework getFrameworkApplication() {
        return frameworkApplication;
    }

    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
    }

    public Customer createPerson(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
            AddPersonOperation addPersonOperation = new AddPersonOperation(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    birthDate,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getOperationManager().invoke(addPersonOperation);

            customer = addPersonOperation.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.getFrameworkApplication().getFinCo().getRepository().write(null);

        return customer;
    }

    public Customer createCompany(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
            AddCompanyOperation addCompanyOperation = new AddCompanyOperation(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    noEmployees,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getOperationManager().invoke(addCompanyOperation);

            customer = addCompanyOperation.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.getFrameworkApplication().getFinCo().getRepository().write(null);

        return customer;
    }

    public Collection<Customer> getCustomers() {
        return frameworkApplication.getFinCo().getCustomers();
    }

    public Account createAccount(Customer customer, String accountNum) {
        Account account = AccountFactory.createAccount(customer, accountNum);

        customer.addAccount(account);
        this.getAccounts().add(account);

        return account;
    }

    public Collection<Account> getAccounts() {
        return frameworkApplication.getFinCo().getAccounts();
    }

    public void generateReport() {
    	GenerateReportOperation operatoin = new GenerateReportOperation(frameworkApplication.getFinCo());
    	frameworkApplication.getOperationManager().invoke(operatoin);
    }

    public void addInterest() {
    	AddInterestOperation addInterestOperation = new AddInterestOperation(frameworkApplication.getFinCo());
    	frameworkApplication.getOperationManager().invoke(addInterestOperation);
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : frameworkApplication.getFinCo().getCustomers()) {
            if (customer.getName() == name) {
                return customer;
            }
        }
        return null;
    }

    public Entry withdraw(Account account, double amount) {
        WithdrawOperation withdrawOperation = new WithdrawOperation(account, amount, frameworkApplication.getFinCo());
        frameworkApplication.getOperationManager().invoke(withdrawOperation);

        return withdrawOperation.getEntry();
    }

    public Entry deposit(Account account, double amount) {
        DepositOperation depositOperation = new DepositOperation(account, amount, frameworkApplication.getFinCo());
        frameworkApplication.getOperationManager().invoke(depositOperation);

        return depositOperation.getEntry();
    }
}
