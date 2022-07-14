package bank.view;

import framework.view.IFinCoViewController;

public interface IBankViewController extends IFinCoViewController {
    void createCustomerAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, Integer type);
    void createCompanyAndAccountByType(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployee, Integer type);
}
