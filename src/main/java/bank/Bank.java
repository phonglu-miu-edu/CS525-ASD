package bank;

import bank.view.BankViewController;
import framework.Framework;
import framework.IFinCo;
import framework.command.CommandManager;
import framework.repository.IRepository;
import framework.view.IFinCoViewController;
import framework.view.ViewType;

public class Bank extends Framework {
    public static void main(String[] args) {
        IFinCoViewController bankviewController = new BankViewController(ViewType.BANK);
        IFinCo finCoBank = new FinCoBank();
        CommandManager commandManager = new CommandManager();
        Bank bank = new Bank();

        Framework.setUp(bank, bankviewController, finCoBank, commandManager);
    }

    public void initData() {
        IRepository repo = this.viewController.getFrameworkApplication().getFinCo().getRepository();
        repo.load();
    }
}
