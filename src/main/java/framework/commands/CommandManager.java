package framework.commands;

public class CommandManager {

    public CommandManager() {
    }

    public void invoke(ICommand operation) {
        operation.execute();
    }

}
