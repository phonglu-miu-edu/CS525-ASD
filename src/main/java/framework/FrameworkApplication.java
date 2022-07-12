package framework;

import framework.command.CommandManager;
import framework.view.IFincoViewController;

public class FrameworkApplication extends Framework{
    @Override
    public IFinco getFinCo() {
        return null;
    }

    @Override
    public void setFinCo(IFinco finCo) {

    }

    @Override
    public IFincoViewController getViewController() {
        return null;
    }

    @Override
    public void setViewController(IFincoViewController ui) {

    }

    @Override
    public CommandManager getCommandManager() {
        return null;
    }

    @Override
    public void setCommandManager(CommandManager cmdManager) {

    }

    @Override
    public void setFrameworkApplication(IFramework frameworkApplication) {

    }

    @Override
    public void initData() {

    }
}
