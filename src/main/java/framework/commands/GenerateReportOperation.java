package framework.commands;

import framework.IFinco;

public class GenerateReportOperation implements  IOperation{

    private IFinco finCo;

    public GenerateReportOperation(IFinco finCo) {
        this.finCo = finCo;
//        finCo.setReport(new AllAccountsReport(finCo.getAccounts()));
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }

}
