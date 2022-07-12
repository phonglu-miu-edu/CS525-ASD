package ccard.view;

import ccard.ICCardFinCo;
import ccard.model.CreditCardType;
import ccard.command.AddAccount;
import framework.command.AddPerson;
import framework.model.Customer;
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
    public void createCreditCardAccount(String accountNum, String clientName, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType) {
        Customer customer = findCustomerByName(clientName);

        if (customer == null) {
            AddPerson addPerson = new AddPerson(
                clientName,
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

        AddAccount addAccount= new AddAccount(customer, accountNum, cardType, expiryDate, (ICCardFinCo) frameworkApplication.getFinCo());

        frameworkApplication.getCommandManager().invoke(addAccount);
    }
}
