package framework.command;

import framework.IFinCo;

public class ReportGenerate implements ICommand {
    private IFinCo finCo;

    public ReportGenerate(IFinCo finCo) {
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }

}
