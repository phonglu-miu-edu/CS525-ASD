package framework.view;

import framework.IFramework;
import framework.command.*;
import framework.factory.AccountFactory;
import framework.model.IAccount;
import framework.model.ICustomer;
import framework.model.IEntry;

import java.util.Collection;

public class FinCoViewController implements IFinCoViewController {
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
        } catch (Throwable t) {
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

    public ICustomer createCustomer(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        ICustomer customer = findCustomerByName(name);

        if (customer == null) {
            AddPerson addPerson = new AddPerson(
                name,
                street,
                city,
                state,
                zip,
                email,
                birthDate,
                frameworkApplication.getFinCo()
            );

            frameworkApplication.getCommandManager().invoke(addPerson);

            customer = addPerson.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.getFrameworkApplication().getFinCo().getRepository().write();

        return customer;
    }

    public ICustomer createOrg(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        ICustomer customer = findCustomerByName(name);

        if (customer == null) {
            AddCompany addCompany = new AddCompany(
                name,
                street,
                city,
                state,
                zip,
                email,
                noEmployees,
                frameworkApplication.getFinCo()
            );

            frameworkApplication.getCommandManager().invoke(addCompany);

            customer = addCompany.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.getFrameworkApplication().getFinCo().getRepository().write();

        return customer;
    }

    public Collection<ICustomer> getCustomers() {
        return frameworkApplication.getFinCo().getCustomers();
    }

    public IAccount createAccount(ICustomer customer, String accountNum) {
        IAccount account = AccountFactory.createAccount(customer, accountNum);

        customer.addAccount(account);
        this.getAccounts().add(account);

        return account;
    }

    public Collection<IAccount> getAccounts() {
        return frameworkApplication.getFinCo().getAccounts();
    }

    public void report() {
        ReportGenerate operation = new ReportGenerate(frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(operation);
    }

    public void addInterest() {
        AddInterest addInterest = new AddInterest(frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(addInterest);
    }

    public ICustomer findCustomerByName(String name) {
        for (ICustomer customer : frameworkApplication.getFinCo().getCustomers()) {
            if (customer.getName() == name) {
                return customer;
            }
        }
        return null;
    }

    public IEntry withdraw(IAccount account, double amount) {
        Withdraw withdraw = new Withdraw(account, amount, frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(withdraw);

        return withdraw.getEntry();
    }

    public IEntry deposit(IAccount account, double amount) {
        Deposit deposit = new Deposit(account, amount, frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(deposit);

        return deposit.getEntry();
    }
}
