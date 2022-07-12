package framework.commands;

public class OperationManager {

    public OperationManager() {
    }

    public void invoke(IOperation operation) {
        operation.execute();
    }

}
