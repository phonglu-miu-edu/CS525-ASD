package ccard;

import ccard.view.CCardViewController;
import framework.Framework;
import framework.command.CommandManager;
import framework.repository.IRepository;
import framework.view.IFinCoViewController;
import framework.view.ViewType;

public class CCard extends Framework {
    public static void main(String[] args) {
        IFinCoViewController viewController = new CCardViewController(ViewType.CCARD);
        ICCardFinCo finCo = new CCardFinCo();
        CommandManager operationManager = new CommandManager();
        CCard frameworkApplication = new CCard();

        Framework.setUp(frameworkApplication, viewController, finCo, operationManager);
    }

    @Override
    public void initData() {
    	IRepository repo = this.viewController.getFrameworkApplication().getFinCo().getRepository();
        repo.load();
    }
}
