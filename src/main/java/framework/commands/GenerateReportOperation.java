package framework.commands;

import framework.IFinCo;

public class GenerateReportOperation implements  IOperation{

    private IFinCo finCo;

    public GenerateReportOperation(IFinCo finCo) {
        this.finCo = finCo;
//        finCo.setReport(new AllAccountsReport(finCo.getAccounts()));
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }

}
