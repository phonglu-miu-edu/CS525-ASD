package framework;

import framework.commands.OperationManager;
import framework.views.IFincoViewController;

public abstract class Framework implements IFramework {
    protected IFinCo finCo;
    protected IFincoViewController viewController;
    protected OperationManager operationManager;
    protected IFramework frameworkApplication;

    public static void setUp(IFramework frameworkApplication, IFincoViewController viewController, IFinCo finCo, OperationManager operationManager) {
        frameworkApplication.setViewController(viewController);
        frameworkApplication.setFinCo(finCo);
        frameworkApplication.setOperationManager(operationManager);

        viewController.setFrameworkApplication(frameworkApplication);
        finCo.setFrameworkApplication(frameworkApplication);

        frameworkApplication.initData();

        viewController.setVisible();
    }

    @Override
    public IFinCo getFinCo() {
        return finCo;
    }

    @Override
    public void setFinCo(IFinCo finCo) {
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
    public OperationManager getOperationManager() {
        return operationManager;
    }

    @Override
    public void setOperationManager(OperationManager operationManager) {
        this.operationManager = operationManager;
    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
    }
}
