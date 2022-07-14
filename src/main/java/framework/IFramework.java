package framework;

import framework.command.CommandManager;
import framework.view.IFinCoViewController;

public interface IFramework {
    IFinCo getFinCo();

    void setFinCo(IFinCo finCo);

    IFinCoViewController getViewController();

    void setViewController(IFinCoViewController ui);

    CommandManager getCommandManager();

    void setCommandManager(CommandManager commandManager);

    void setFrameworkApplication(IFramework frameworkApplication);

    void initData();
}
