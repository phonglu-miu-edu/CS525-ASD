package banking;

import banking.views.BankViewController;
import framework.Framework;
import framework.IFinCo;
import framework.commands.OperationManager;
import framework.database.IRepository;
import framework.views.IFincoViewController;
import framework.views.VIEW_TYPE;

public class Bank extends Framework {
    public static void main(String[] args) {
        IFincoViewController viewController = new BankViewController(VIEW_TYPE.BANK);
        IFinCo finCoExtension = new FinCo();
        OperationManager operationManager = new OperationManager();
        Bank bank = new Bank();

        Framework.setUp(bank, viewController, finCoExtension, operationManager);
    }

    @Override
    public void initData() {
        IRepository repo = this.viewController.getFrameworkApplication().getFinCo().getRepository();
        repo.load();
    }
}
