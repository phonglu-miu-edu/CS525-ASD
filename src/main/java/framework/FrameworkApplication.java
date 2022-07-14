package framework;

import framework.command.CommandManager;
import framework.repository.IRepository;
import framework.view.FinCoViewController;
import framework.view.ViewType;


public class FrameworkApplication extends Framework {
    public static void main(String[] args) {
        FinCoViewController viewController = new FinCoViewController(ViewType.FRAMEWORK);
        FinCo finCo = new FinCo();
        CommandManager operationManager = new CommandManager();
        IFramework frameworkApplication = new FrameworkApplication();

        Framework.setUp(frameworkApplication, viewController, finCo, operationManager);

//        viewController.setVisible();
    }

    public void initData() {
        IRepository repo = this.viewController.getFrameworkApplication().getFinCo().getRepository();
        repo.load();
    }
}
