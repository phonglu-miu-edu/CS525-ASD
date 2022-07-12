package framework;

import framework.commands.CommandManager;
import framework.database.IRepository;
import framework.views.FincoViewController;
import framework.views.VIEW_TYPE;


public class FrameworkApplication extends Framework {
    public static void main(String[] args) {
        FincoViewController viewController = new FincoViewController(VIEW_TYPE.BANK);
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
