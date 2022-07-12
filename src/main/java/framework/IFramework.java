package framework;

import framework.commands.CommandManager;
import framework.views.IFincoViewController;

public interface IFramework {
    IFinco getFinCo();

    void setFinCo(IFinco finCo);

    IFincoViewController getViewController();

    void setViewController(IFincoViewController ui);

    CommandManager getOperationManager();

    void setOperationManager(CommandManager operationManager);

    void setFrameworkApplication(IFramework frameworkApplication);

    //void initData();
}
