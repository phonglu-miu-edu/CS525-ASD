package creditcard.reports;

import creditcard.models.CreditCardAccount;
import framework.models.Account;
import framework.models.Customer;
import framework.models.ICustomer;
import framework.reports.IReport;

import java.util.Collection;

public class MonthlyBillingReport implements IReport {

    private final Collection<Account> accounts;
    private String billingReport;

    public MonthlyBillingReport(Collection<Account> accounts) {
        this.accounts = accounts;
        billingReport = "";
    }

    @Override
    public void generateReport() {
        for (Account account: accounts) {
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
