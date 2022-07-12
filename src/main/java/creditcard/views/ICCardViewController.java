package creditcard.views;

import creditcard.models.CreditCardType;
import framework.views.IFincoViewController;

public interface ICCardViewController extends IFincoViewController {
    void createCreditCardAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType);
}
