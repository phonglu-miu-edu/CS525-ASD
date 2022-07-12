package ccard.model;

public class Gold implements CreditCardType {

    double monthlyInterest;
    double minimumPayment;

    public Gold() {
        this.monthlyInterest = 0.14;
        this.minimumPayment = 0.12;
    }

    @Override
    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    @Override
    public double getMinimumPayment() {
        return minimumPayment;
    }

    @Override
    public void setMinimumPayment(double payment) {
        monthlyInterest = payment;
    }

    @Override
    public void setMonthlyInterest(double interest) {
        minimumPayment = interest;
    }

    @Override
    public String toString() {
        return "Gold";
    }
}
