package ccard.model;

public class Silver implements CreditCardType {

    double monthlyInterest;
    double minimumPayment;

    public Silver() {
        this.monthlyInterest = 0.16;
        this.minimumPayment = 0.18;
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
        return "Silver";
    }
}
