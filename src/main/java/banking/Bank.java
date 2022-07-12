package banking;

import banking.views.BankViewController;
import framework.Framework;
import framework.IFinco;
import framework.commands.CommandManager;
import framework.views.IFincoViewController;
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
