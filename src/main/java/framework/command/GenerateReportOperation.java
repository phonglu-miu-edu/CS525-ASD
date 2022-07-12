package framework.command;

import framework.IFinco;

public class GenerateReportOperation implements  ICommand{

    private IFinco finCo;

    public GenerateReportOperation(IFinco finCo) {
        this.finCo = finCo;
//        finCo.setReport(new AllAccountsReport(finCo.getAccounts()));
    }

    @Override
    public void execute() {
        finCo.report();
    }

}
