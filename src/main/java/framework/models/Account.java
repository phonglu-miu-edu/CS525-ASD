package framework.models;

import java.util.ArrayList;
import java.util.Collection;

public class Account implements IAccount {
	private Customer customer;
	private String accountNum;
	private double currentBalance;
	private Collection<Entry> entryHistory = new ArrayList<>();
	INotification<String> notification;
	
	protected double interestRate;

	public Account(Customer customer, String accountNum) {
		this.customer = customer;
		this.accountNum = accountNum;
		this.currentBalance = 0;
		
		this.interestRate = 0.01;
	}

	@Override
	public String toString() {
		return "Account{" + "customer=" + customer + ", accountNum='" + accountNum + '\'' + ", currentBalance="
				+ currentBalance + ", entryHistory=" + entryHistory + '}';
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCurrentBalance(double amount) {
		this.currentBalance = amount;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public Collection<Entry> getEntryHistory() {
		return entryHistory;
	}
	
	public void addEntryHistory(Entry entry)
	{
		this.entryHistory.add(entry);
	}
	
	public INotification<String> getNotification() {
		return notification;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public void setNotification(INotification<String> notification)
	{
		this.notification = notification;
	}

	@Override
	public void addInterest() {
		this.currentBalance += this.currentBalance * getInterestRate();
	}

	public void changeBalanceByAmount(double amount) {
		currentBalance += amount;
	}
}
