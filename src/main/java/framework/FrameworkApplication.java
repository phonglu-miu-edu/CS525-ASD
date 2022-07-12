package framework;

import framework.command.CommandManager;
import framework.repository.IRepository;
import framework.view.FincoViewController;
import framework.view.ViewType;

public class FrameworkApplication extends Framework{
    public static void main(String[] args) {
        FincoViewController viewController = new FincoViewController(ViewType.BANK);
        FinCo finCo = new FinCo();
        CommandManager operationManager = new CommandManager();
        IFramework frameworkApplication = new FrameworkApplication();

        Framework.setUp(frameworkApplication, viewController, finCo, operationManager);

//        viewController.setVisible();
    }

    public void initData() {
        IRepository repo = this.viewController.getFrameworkApplication().getFinCo().getRepository();
        repo.load(repo.getRepoPath());
    }
}
