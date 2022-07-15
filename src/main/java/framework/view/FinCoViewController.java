package framework.view;

import framework.FinCo;
import framework.command.*;
import framework.factory.SimpleFactory;
import framework.model.Entry;
import framework.model.IAccount;
import framework.model.ICustomer;

import java.util.Collection;

public class FinCoViewController implements IFinCoViewController {
    protected FinCoView finCoView;
    protected FinCo finCo;
    protected ViewType viewType;

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
                finCo
            );

            this.finCo.getCommandManager().invoke(addPerson);

            customer = addPerson.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.finCo.getRepository().write();

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
                finCo
            );

            finCo.getCommandManager().invoke(addCompany);

            customer = addCompany.getCustomer();
        }

        this.createAccount(customer, accountNum);

        this.finCo.getRepository().write();

        return customer;
    }

    public IAccount createAccount(ICustomer customer, String accountNum) {
        IAccount account = SimpleFactory.createAccount(customer, accountNum);

        customer.addAccount(account);
        this.getAccounts().add(account);

        return account;
    }

    public Collection<IAccount> getAccounts() {
        return finCo.getAccounts();
    }

    public void report() {
        ReportGenerate operation = new ReportGenerate(finCo);
        this.finCo.getCommandManager().invoke(operation);
    }

    public void addInterest() {
        AddInterest addInterest = new AddInterest(finCo);
        this.finCo.getCommandManager().invoke(addInterest);
    }

    public ICustomer findCustomerByName(String name) {
        for (ICustomer customer : finCo.getCustomers()) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public Entry withdraw(IAccount account, double amount) {
        Withdraw withdraw = new Withdraw(account, amount, finCo);
        this.finCo.getCommandManager().invoke(withdraw);

        return withdraw.getEntry();
    }

    public Entry deposit(IAccount account, double amount) {
        Deposit deposit = new Deposit(account, amount, finCo);
        this.finCo.getCommandManager().invoke(deposit);

        return deposit.getEntry();
    }

    @Override
    public void setFinCo(FinCo finCo) {
        this.finCo = finCo;
    }

    public FinCo getFinCo() {
        return this.finCo;
    }
}
