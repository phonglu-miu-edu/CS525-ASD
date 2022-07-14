package bank.view;

import bank.command.AddAccount;
import framework.command.AddCompany;
import framework.command.AddPerson;
import framework.model.ICustomer;
import framework.view.FinCoViewController;
import framework.view.ViewType;

public class BankViewController extends FinCoViewController implements IBankViewController {
    public BankViewController(ViewType viewType) {
        super(viewType);
    }

    @Override
    public void setVisible() {
        finCoView = new BankView(this, viewType);

        try {
            finCoView.setVisible(true);
        }
        catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    @Override
    public void createCustomerAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, Integer type) {
        ICustomer customer = findCustomerByName(name);

        if (customer == null) {
            AddPerson addPerson = new AddPerson(name, street, city, state, zip, email, birthDate,frameworkApplication.getFinCo());
            frameworkApplication.getCommandManager().invoke(addPerson);
            customer = addPerson.getCustomer();
        }

        AddAccount addAccount = new AddAccount(customer, accountNum, type, frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(addAccount);
        frameworkApplication.getFinCo().getRepository().write();
    }
    
    public void createCompanyAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployee, Integer type) {
        ICustomer customer = findCustomerByName(name);

        if (customer == null) {
        	AddCompany addCompany = new AddCompany(name, street, city, state, zip, email, noOfEmployee, frameworkApplication.getFinCo());
            frameworkApplication.getCommandManager().invoke(addCompany);
            customer = addCompany.getCustomer();
        }

        AddAccount addAccount = new AddAccount(customer, accountNum, type, frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(addAccount);
        frameworkApplication.getFinCo().getRepository().write();
    }
}
