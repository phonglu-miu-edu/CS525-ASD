package ccard.view;

import ccard.model.CreditCardType;
import framework.view.IFinCoViewController;

public interface ICCardViewController extends IFinCoViewController {
    void createPersonalAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType);
    void createCompanyAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployees, String expiryDate, CreditCardType cardType);
}
