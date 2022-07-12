package framework.model;

import java.util.Collection;

public class Report implements IReport {

    private Collection<Account> accounts;
    private String report;

    public Report(Collection<Account> accounts, String report) {
        this.accounts = accounts;
        this.report = "";
    }

    @Override
    public void generate() {
        report = "Acc num\t\tCurr Balance\t\tNo. entries\t\tCust Name" + "\n\n\n";

        for (Account account: accounts) {
            report += account.getBalance() + "\t\t" + account.getCurrentBalance() + "\t\t"
                    + account.getEntryHistory().size() + "\t\t" + account.getCustomer().getName()+ "\n\n\n";
        }
    }
}
