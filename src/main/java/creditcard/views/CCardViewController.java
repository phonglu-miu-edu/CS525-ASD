package creditcard.views;

import creditcard.commands.AddAccountOperation;
import creditcard.models.CreditCardType;
import framework.commands.AddCompanyOperation;
import framework.commands.AddPersonOperation;
import framework.models.Customer;
import framework.views.FincoViewController;
import framework.views.VIEW_TYPE;

import javax.swing.*;

public class CCardViewController extends FincoViewController implements ICCardViewController {

    public CCardViewController(VIEW_TYPE view_type) {
        super(view_type);
    }

    @Override
    public void setVisible() {
        fincoView = new CCardView(this, view_type);

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
    public void createPersonalAccount(String accountNum, String clientName, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType) {

        Customer customer = findCustomerByName(clientName);

        if (customer == null) {
            AddPersonOperation addPersonOperation = new AddPersonOperation(
                    clientName,
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

        AddAccountOperation addAccountOperation = new AddAccountOperation(customer, accountNum, cardType, expiryDate, frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);
    }

    @Override
    public void createCompanyAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployees, String expiryDate, CreditCardType cardType) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
            AddCompanyOperation addPersonOperation = new AddCompanyOperation(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    noOfEmployees,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getOperationManager().invoke(addPersonOperation);

            customer = addPersonOperation.getCustomer();
        }

        AddAccountOperation addAccountOperation = new AddAccountOperation(customer, accountNum, cardType, expiryDate, frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);
    }
}
