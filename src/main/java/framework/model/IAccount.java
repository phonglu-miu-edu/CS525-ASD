package framework.model;

import java.util.Collection;

public interface IAccount {
    void addInterest();

    void setBalance(double amount);

    double getBalance();

    INotification<String> getNotification();

    void setNotification(INotification<String> notification);

    Collection<Entry> getEntryHistory();
}
