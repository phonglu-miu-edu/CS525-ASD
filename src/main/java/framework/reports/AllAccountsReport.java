package framework.reports;

import framework.models.Account;

import java.util.Collection;

public class AllAccountsReport implements IReport {

    private Collection<Account> accounts;
    private String report;

    public AllAccountsReport(Collection<Account> accounts) {
        this.accounts = accounts;
        this.report = "";
    }

    @Override
    public void generateReport() {

        report = "Acc num\t\tCurr Balance\t\tNo. entries\t\tCust Name" + "\n\n\n";

        for (Account account: accounts) {
            report += account.getAccountNum() + "\t\t" + account.getCurrentBalance() + "\t\t"
                    + account.getEntryHistory().size() + "\t\t" + account.getCustomer().getName()+ "\n\n\n";
        }
    }

    public String getReport() {
        return report;
    }
}
