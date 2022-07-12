package ccard.framework;

import java.util.ArrayList;
import java.util.List;

public class OperationManager {
    private List<IOperator> operators;

    public OperationManager() {
        this.operators = new ArrayList<>();
    }

    public void addOperation(IOperator operator) {
        this.operators.add(operator);
    }

    public void invoke() {
        for (IOperator operator : operators) {
            operator.execute();
        }
    }
}
