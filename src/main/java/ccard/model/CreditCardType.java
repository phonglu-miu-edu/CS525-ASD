package ccard.model;

public interface CreditCardType {
    double getMonthlyInterest();
    double getMinimumPayment();
    void setMinimumPayment(double payment);
    void setMonthlyInterest(double interest);
}
