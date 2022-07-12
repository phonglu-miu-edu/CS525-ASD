package framework.model;

import java.util.Collection;

public interface IAccount {
    String getAccountNum();
    void addInterest();

    void setBalance(double amount);

    double getBalance();

    INotification<String> getNotification();

    void setNotification(INotification<String> notification);

    Collection<IEntry> getEntryHistory();

    void addEntryHistory(IEntry entry);

     void changeBalanceByAmount(double amount);

    ICustomer getCustomer();

    double getCurrentBalance();
}
