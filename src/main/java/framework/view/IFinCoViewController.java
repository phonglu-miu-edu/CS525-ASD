package framework.view;

import framework.FinCo;
import framework.model.*;

import java.util.Collection;

public interface IFinCoViewController {
    void setVisible();

    ICustomer createCustomer(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate);

    ICustomer createOrg(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    IAccount createAccount(ICustomer customer, String accountNum);

    Collection<IAccount> getAccounts();

    void report();

    void addInterest();

    ICustomer findCustomerByName(String name);

    Entry withdraw(IAccount account, double amount);

    Entry deposit(IAccount account, double amount);
    void setFinCo(FinCo finCo);
    FinCo getFinCo();
}
