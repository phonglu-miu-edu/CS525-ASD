package framework.command;

import framework.IFinCo;

public class AddInterest implements ICommand {
    IFinCo finCo;

    public AddInterest(IFinCo finCo) {
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        finCo.addInterest();
    }

}
