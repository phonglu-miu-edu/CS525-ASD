package framework;

import framework.commands.CommandManager;
import framework.views.IFincoViewController;

public abstract class Framework implements IFramework {
    protected IFinco finCo;
    protected IFincoViewController viewController;
    protected CommandManager operationManager;
    protected IFramework frameworkApplication;

    public static void  setUp(IFramework frameworkApplication, IFincoViewController viewController, IFinco finCo, CommandManager operationManager) {
        frameworkApplication.setViewController(viewController);
        frameworkApplication.setFinCo(finCo);
        frameworkApplication.setOperationManager(operationManager);

        viewController.setFrameworkApplication(frameworkApplication);
        finCo.setFrameworkApplication(frameworkApplication);

        //frameworkApplication.initData();

        viewController.setVisible();
    }

    @Override
    public IFinco getFinCo() {
        return finCo;
    }

    @Override
    public void setFinCo(IFinco finCo) {
        this.finCo = finCo;
    }

    @Override
    public IFincoViewController getViewController() {
        return viewController;
    }

    @Override
    public void setViewController(IFincoViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public CommandManager getOperationManager() {
        return operationManager;
    }

    @Override
    public void setOperationManager(CommandManager operationManager) {
        this.operationManager = operationManager;
    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
    }
}
