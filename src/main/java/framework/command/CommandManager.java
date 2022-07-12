package framework.command;

public class CommandManager {

    public CommandManager() {
    }

    public void invoke(ICommand command) {
        command.execute();
    }
}
