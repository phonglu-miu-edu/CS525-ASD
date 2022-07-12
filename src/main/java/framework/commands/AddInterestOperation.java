package framework.commands;

import framework.IFinCo;

public class AddInterestOperation implements IOperation {
	IFinCo finCo;
	
	public AddInterestOperation(IFinCo finco)
	{
		this.finCo = finco;
	}
	@Override
	public void execute() {
		finCo.addInterest();
	}

}
