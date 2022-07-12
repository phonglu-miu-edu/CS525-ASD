package creditcard.views;

import creditcard.models.CreditCardType;
import framework.views.IFincoViewController;

public interface ICCardViewController extends IFincoViewController {
    void createPersonalAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType);
    void createCompanyAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noOfEmployees, String expiryDate, CreditCardType cardType);
}
