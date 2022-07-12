package framework.commands;

import framework.IFinco;

public class AddInterestOperation implements IOperation {
	IFinco finCo;
	
	public AddInterestOperation(IFinco finco)
	{
		this.finCo = finco;
	}
	@Override
	public void execute() {
		finCo.addInterest();
	}

}
