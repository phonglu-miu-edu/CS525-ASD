package banking;

import banking.views.BankViewController;
import framework.commands.CommandManager;
import framework.Framework;
import framework.views.IFincoViewController;
import framework.IFinco;
import framework.views.VIEW_TYPE;

public class Bank extends Framework {
    public static void main(String[] args) {
        IFincoViewController viewController = new BankViewController(VIEW_TYPE.BANK);
        IFinco finCoExtension = new FinCo();
        CommandManager commandManager = new CommandManager();
        Bank bank = new Bank();

        Framework.setUp(bank, viewController, finCoExtension, commandManager);
    }

}
