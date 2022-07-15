package framework.command;

import framework.FinCo;

public class AddInterest implements ICommand {
    FinCo finCo;

    public AddInterest(FinCo finCo) {
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        finCo.addInterest();
    }

}
