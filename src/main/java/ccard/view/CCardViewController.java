package ccard.view;

import ccard.ICCardFinCo;
import ccard.command.AddAccount;
import ccard.command.Withdraw;
import ccard.model.CreditCardType;
import framework.command.AddCompany;
import framework.command.AddPerson;
import framework.model.IAccount;
import framework.model.ICustomer;
import framework.model.IEntry;
import framework.view.FinCoViewController;
import framework.view.ViewType;

public class CCardViewController extends FinCoViewController implements ICCardViewController {

    public CCardViewController(ViewType viewType) {
        super(viewType);
    }

    @Override
    public void setVisible() {
        finCoView = new CCardView(this, viewType);

        try {
            finCoView.setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    @Override
    public void createPersonalAccount(String accountNum, String clientName, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType) {
        ICustomer customer = findCustomerByName(clientName);

        if (customer == null) {
            AddPerson addPersonOperation = new AddPerson(
                clientName,
                street,
                city,
                state,
                zip,
                email,
                birthDate,
                frameworkApplication.getFinCo()
            );

            frameworkApplication.getCommandManager().invoke(addPersonOperation);

            customer = addPersonOperation.getCustomer();
        }

        AddAccount addAccount = new AddAccount(customer, accountNum, cardType, expiryDate, (ICCardFinCo) frameworkApplication.getFinCo());

        frameworkApplication.getCommandManager().invoke(addAccount);
    }

    @Override
    public void createCompanyAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployees, String expiryDate, CreditCardType cardType) {
        ICustomer customer = findCustomerByName(name);

        if (customer == null) {
            AddCompany addCompany = new AddCompany(
                name,
                street,
                city,
                state,
                zip,
                email,
                noOfEmployees,
                frameworkApplication.getFinCo()
            );

            frameworkApplication.getCommandManager().invoke(addCompany);

            customer = addCompany.getCustomer();
        }

        AddAccount addAccountOperation = new AddAccount(customer, accountNum, cardType, expiryDate, (ICCardFinCo) frameworkApplication.getFinCo());

        frameworkApplication.getCommandManager().invoke(addAccountOperation);
    }

    @Override
    public IEntry withdraw(IAccount account, double amount) {
        Withdraw withdraw = new Withdraw(account, amount, (ICCardFinCo) frameworkApplication.getFinCo());
        frameworkApplication.getCommandManager().invoke(withdraw);

        return withdraw.getEntry();
    }
}
