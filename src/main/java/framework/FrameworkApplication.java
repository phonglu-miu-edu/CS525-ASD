package framework;

import framework.command.CommandManager;
import framework.view.IFinCoViewController;

public class FrameworkApplication extends Framework{
    @Override
    public IFinCo getFinCo() {
        return null;
    }

    @Override
    public void setFinCo(IFinCo finCo) {

    }

    @Override
    public IFinCoViewController getViewController() {
        return null;
    }

    @Override
    public void setViewController(IFinCoViewController ui) {

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
