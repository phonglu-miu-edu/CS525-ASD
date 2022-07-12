package framework.commands;

import framework.IFinco;

public class AddInterest implements ICommand {
	IFinco finCo;
	
	public AddInterest(IFinco finco)
	{
		this.finCo = finco;
	}
	@Override
	public void execute() {
		finCo.addInterest();
	}

}
