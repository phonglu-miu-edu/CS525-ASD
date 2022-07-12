package framework.command;

import framework.IFinCo;

public class GenerateReport implements ICommand {
    private IFinCo finCo;

    public GenerateReport(IFinCo finCo) {
        this.finCo = finCo;
//        finCo.setReport(new AllAccountsReport(finCo.getAccounts()));
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }

}
