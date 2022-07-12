package creditcard;

import creditcard.views.CCardViewController;
import framework.commands.CommandManager;
import framework.Framework;
import framework.views.IFincoViewController;
import framework.views.VIEW_TYPE;
import framework.IFinco;

public class CCard extends Framework {

    public static void main(String[] args) {
        IFincoViewController viewController = new CCardViewController(VIEW_TYPE.CCARD);
        IFinco finCo = new CCardFinCoExtension();
        CommandManager operationManager = new CommandManager();
        CCard frameworkApplication = new CCard();

        Framework.setUp(frameworkApplication, viewController, finCo, operationManager);
    }
}
