package banking.views;

import framework.views.IFincoViewController;

public interface IBankViewController extends IFincoViewController {
    void createCustomerAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, Integer type);
    void createCompanyAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployee, Integer type);
}
