package creditcard.models;

public class Copper implements CreditCardType {

    double monthlyInterest;
    double minimumPayment;

    public Copper() {
        this.monthlyInterest = 0.2;
        this.minimumPayment = 0.22;
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
        return "Copper";
    }
}
