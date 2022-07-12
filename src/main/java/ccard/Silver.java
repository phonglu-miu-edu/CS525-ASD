package ccard;

public class Silver implements CreditCardType {
    @Override
    public double getMonthlyInterest() {
        return 0.16;
    }

    @Override
    public void setMonthlyInterest() {

    }

    @Override
    public double getMinimumPayment() {
        return 0.18;
    }

    @Override
    public double getMaximumPayment() {
        return 1;
    }
}
