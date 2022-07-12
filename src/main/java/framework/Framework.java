package framework;

import framework.command.CommandManager;
import framework.view.IFinCoViewController;

public abstract class Framework implements IFramework {
    protected IFinCo finCo;
    protected IFinCoViewController viewController;
    protected CommandManager commandManager;
    protected IFramework frameworkApplication;

    public static void setUp(IFramework frameworkApplication, IFinCoViewController viewController, IFinCo finCo, CommandManager commandManager) {
        frameworkApplication.setViewController(viewController);
        frameworkApplication.setFinCo(finCo);
        frameworkApplication.setCommandManager(commandManager);

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
    public IFinCoViewController getViewController() {
        return viewController;
    }

    @Override
    public void setViewController(IFinCoViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {
        this.frameworkApplication = frameworkApplication;
    }
}
