package framework.reports;

import framework.model.IAccount;

import java.util.Collection;

public class AllAccountsReport implements IReport {

    private Collection<IAccount> accounts;
    private String report;

    public AllAccountsReport(Collection<IAccount> accounts) {
        this.accounts = accounts;
        this.report = "";
    }

    @Override
    public void generate() {

        report = "Acc num\t\tCurr Balance\t\tNo. entries\t\tCust Name" + "\n\n\n";

        for (IAccount account: accounts) {
            report += account.getAccountNum() + "\t\t" + account.getCurrentBalance() + "\t\t"
                    + account.getEntryHistory().size() + "\t\t" + account.getCustomer().getName()+ "\n\n\n";
        }
    }

    public String getReport() {
        return report;
    }
}
