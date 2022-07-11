package ccard;

public class Gold implements CreditCardType {
    @Override
    public double getMonthlyInterest() {
        return 0.14;
    }

    @Override
    public void setMonthlyInterest() {

    }

    @Override
    public double getMinimumPayment() {
        return 0.12;
    }

    @Override
    public double getMaximumPayment() {
        return 1;
    }
}

