package framework.command;

import framework.FinCo;

public class ReportGenerate implements ICommand {
    private FinCo finCo;

    public ReportGenerate(FinCo finCo) {
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        finCo.generateReport();
    }
}
