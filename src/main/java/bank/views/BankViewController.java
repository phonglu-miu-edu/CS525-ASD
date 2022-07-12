package banking.views;

import banking.commands.AddAccount;
import framework.commands.AddCompanyOperation;
import framework.commands.AddPersonOperation;
import framework.models.Customer;
import framework.views.FincoViewController;
import framework.views.VIEW_TYPE;

import javax.swing.*;

public class BankViewController extends FincoViewController implements IBankViewController {
    public BankViewController(VIEW_TYPE view_type) {
        super(view_type);
    }

    @Override
    public void setVisible() {
        fincoView = new BankView(this, view_type);

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
    public void createCustomerAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, Integer type) {
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

        AddAccount addAccountOperation = new AddAccount(customer, accountNum, type, frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);

        frameworkApplication.getFinCo().getRepository().write(null);
    }
    
    public void createCompanyAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployee, Integer type) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
        	AddCompanyOperation addCompanyOperation = new AddCompanyOperation(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    noOfEmployee,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getOperationManager().invoke(addCompanyOperation);

            customer = addCompanyOperation.getCustomer();
        }

        AddAccount addAccountOperation = new AddAccount(customer, accountNum, type, frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);

        frameworkApplication.getFinCo().getRepository().write(null);
    }
}
