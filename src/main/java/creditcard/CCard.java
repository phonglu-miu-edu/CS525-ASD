package creditcard;

import creditcard.views.CCardViewController;
import framework.commands.OperationManager;
import framework.database.IRepository;
import framework.Framework;
import framework.views.IFincoViewController;
import framework.views.VIEW_TYPE;

public class CCard extends Framework {
    public static void main(String[] args) {
        IFincoViewController viewController = new CCardViewController(VIEW_TYPE.CCARD);
        ICCardFinCo finCo = new CCardFinCo();
        OperationManager operationManager = new OperationManager();
        CCard frameworkApplication = new CCard();

        Framework.setUp(frameworkApplication, viewController, finCo, operationManager);
    }

    @Override
    public void initData() {
    	IRepository repo = this.viewController.getFrameworkApplication().getFinCo().getRepository();
        repo.load();
    }
}
