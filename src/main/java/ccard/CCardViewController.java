package ccard;

import ccard.framework.*;

import java.util.Date;

public class CCardViewController extends FinCoViewController implements ICCardViewController {
    @Override
    public void createAccount(String name, String phone, String street, String city, String state, String zip, String email, String birthDate, String accountNum, double currentBalance) {
        OperationManager operationManager = new OperationManager();

        IOperator addPerson = new AddPerson(name, phone, street, city, state, zip, email, birthDate);
        operationManager.addOperation(addPerson);

        Person person = new Person(name, phone, street, city, state, zip, email, birthDate);
        IOperator addCommand = new AddAccount(accountNum, currentBalance, person);
        operationManager.addOperation(addCommand);

        operationManager.invoke();
    }
}