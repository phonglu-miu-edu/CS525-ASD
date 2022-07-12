package framework.commands;

import framework.IFinco;

public class ReportGenerate implements ICommand {

    private IFinco finCo;

    public ReportGenerate(IFinco finCo) {
        this.finCo = finCo;
//        finCo.setReport(new AllAccountsReport(finCo.getAccounts()));
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }

}
