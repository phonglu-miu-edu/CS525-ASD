package framework.models;

public interface IAccount {

	void addInterest();

	public void setCurrentBalance(double amount);

	public double getCurrentBalance();
	
	void setNotification(INotification<String> notification);
	
	INotification<String> getNotification();
}
