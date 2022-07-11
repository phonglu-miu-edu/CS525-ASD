package ccard;

public interface CreditCardType {
    double getMonthlyInterest();

    void setMonthlyInterest();

    double getMinimumPayment();

    double getMaximumPayment();
}
