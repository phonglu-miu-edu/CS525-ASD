package framework.model;

import java.util.Collection;

public interface IAccount {
    String getAccountNum();
    void addInterest();

    void setBalance(double amount);

    double getBalance();

    INotification<String> getNotification();

    void setNotification(INotification<String> notification);

    Collection<Entry> getEntryHistory();

    void addEntryHistory(Entry entry);

     void changeBalanceByAmount(double amount);

    ICustomer getCustomer();

    double getCurrentBalance();

    void setCurrentBalance(double currentBalance);
}
