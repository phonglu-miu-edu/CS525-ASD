package framework;

import framework.commands.OperationManager;
import framework.views.IFincoViewController;

public interface IFramework {
    IFinCo getFinCo();

    void setFinCo(IFinCo finCo);

    IFincoViewController getViewController();

    void setViewController(IFincoViewController ui);

    OperationManager getOperationManager();

    void setOperationManager(OperationManager operationManager);

    void setFrameworkApplication(IFramework frameworkApplication);

    void initData();
}
