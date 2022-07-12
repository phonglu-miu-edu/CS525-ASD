package ccard;

public class Copper implements CreditCardType {
    @Override
    public double getMonthlyInterest() {
        return 0.2;
    }

    @Override
    public void setMonthlyInterest() {

    }

    @Override
    public double getMinimumPayment() {
        return 0.22;
    }

    @Override
    public double getMaximumPayment() {
        return 1;
    }
}
