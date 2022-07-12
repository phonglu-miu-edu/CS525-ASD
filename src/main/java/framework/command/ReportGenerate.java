package framework.command;

import framework.IFinCo;

public class ReportGenerate implements ICommand {
    private IFinCo finCo;

    public ReportGenerate(IFinCo finCo) {
        this.finCo = finCo;
//        finCo.setReport(new AllAccountsReport(finCo.getAccounts()));
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }

}
