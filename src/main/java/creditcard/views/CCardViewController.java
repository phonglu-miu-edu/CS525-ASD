package creditcard.views;

import creditcard.ICCardFinCo;
import creditcard.commands.AddAccountOperation;
import creditcard.models.CreditCardType;
import framework.commands.AddPersonOperation;
import framework.models.Customer;
import framework.views.FincoViewController;
import framework.views.VIEW_TYPE;

public class CCardViewController extends FincoViewController implements ICCardViewController {

    public CCardViewController(VIEW_TYPE viewType) {
        super(viewType);
    }

    @Override
    public void setVisible() {
        fincoView = new CCardView(this, viewType);

        try {
            fincoView.setVisible(true);
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

        AddAccountOperation addAccountOperation = new AddAccountOperation(customer, accountNum, cardType, expiryDate, (ICCardFinCo)frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);
    }
}
