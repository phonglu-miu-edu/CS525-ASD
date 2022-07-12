package framework;

import framework.command.CommandManager;
import framework.view.IFincoViewController;

public interface IFramework {
    IFinco getFinCo();

    void setFinCo(IFinco finCo);

    IFincoViewController getViewController();

    void setViewController(IFincoViewController ui);

    CommandManager getCommandManager();

    void setCommandManager(CommandManager cmdManager);

    void setFrameworkApplication(IFramework frameworkApplication);

    void initData();
}
