package ccard;

import java.util.Date;

public interface ICCardViewController {
    void createAccount(String name, String phone, String street, String city, String state, String zip, String email, String birthdate, String accountNum, double currentBalance);
}
