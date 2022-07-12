package ccard.report;

import ccard.model.CreditCardAccount;
import framework.model.Account;
import framework.model.IAccount;
import framework.model.ICustomer;
import framework.model.IReport;

import java.util.Collection;

public class MonthlyBillingReport implements IReport {
    private final Collection<IAccount> accounts;
    private String billingReport;

    public MonthlyBillingReport(Collection<IAccount> accounts) {
        this.accounts = accounts;
        billingReport = "";
    }

    @Override
    public void generate() {
        for (IAccount account : accounts) {
            CreditCardAccount ccAccount = (CreditCardAccount) account;
            ICustomer customer = ccAccount.getCustomer();
            String address = customer.getStreet() + ", " + customer.getCity() + ", " + customer.getState() + ", " + customer.getZip();
            billingReport += "Name = " + customer.getName() + "\n" +
                "Address = " + address + "\n" +
                "CC number = " + ccAccount.getAccountNum() + "\n" +
                "CC type = " + ccAccount.getType() + "\n" +
                "Previous balance = $" + ccAccount.getLastMonthBalance() + "\n" +
                "Total Credits = $" + ccAccount.getTotalCurrentMonthCredits() + "\n" +
                "Total Charges = $" + ccAccount.getTotalCurrentMonthCharges() + "\n" +
                "New balance = $" + ccAccount.getNewBalance() + "\n" +
                "Total amount due = $" + ccAccount.getTotalAmountDue() + "\n\n\n";

        }
    }

    public String getBillingReport() {
        return billingReport;
    }
}
