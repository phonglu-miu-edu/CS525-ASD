package banking.views;

import banking.commands.AddAccount;
import framework.commands.AddOrganization;
import framework.commands.AddCustomer;
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

            frameworkApplication.getOperationManager().invoke(addCustomer);

            customer = addCustomer.getCustomer();
        }

        AddAccount addAccountOperation = new AddAccount(customer, accountNum, type, frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);

        frameworkApplication.getFinCo().getRepository().write(null);
    }
    
    public void createCompanyAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployee, Integer type) {
        Customer customer = findCustomerByName(name);

        if (customer == null) {
        	AddOrganization addOrganization = new AddOrganization(
                    name,
                    street,
                    city,
                    state,
                    zip,
                    email,
                    noOfEmployee,
                    frameworkApplication.getFinCo()
            );

            frameworkApplication.getOperationManager().invoke(addOrganization);

            customer = addOrganization.getCustomer();
        }

        AddAccount addAccountOperation = new AddAccount(customer, accountNum, type, frameworkApplication.getFinCo());

        frameworkApplication.getOperationManager().invoke(addAccountOperation);

        frameworkApplication.getFinCo().getRepository().write(null);
    }
}
