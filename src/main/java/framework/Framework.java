package framework;

import framework.command.CommandManager;
import framework.view.IFincoViewController;

public abstract class Framework implements IFramework {

    protected IFinco finCo;
    protected IFincoViewController viewController;
    protected CommandManager commandManager;
    protected IFramework frameworkApplication;

    public static void  setUp(IFramework frameworkApplication, IFincoViewController viewController, IFinco finCo, CommandManager commandManager) {
        frameworkApplication.setViewController(viewController);
        frameworkApplication.setFinCo(finCo);
        frameworkApplication.setCommandManager(commandManager);

        viewController.setFrameworkApplication(frameworkApplication);
        finCo.setFrameworkApplication(frameworkApplication);

        frameworkApplication.initData();

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
