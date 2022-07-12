package framework.commands;

public class CommandManager {

    public CommandManager() {
    }

    public void invoke(IOperation operation) {
        operation.execute();
    }

}
