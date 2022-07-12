package ccard.view;

import ccard.model.CreditCardType;
import framework.view.IFinCoViewController;

public interface ICCardViewController extends IFinCoViewController {
    void createCreditCardAccount(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate, String expiryDate, CreditCardType cardType);
}
