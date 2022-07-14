package framework.model;

import java.util.Collection;

public interface ICustomer {
    void addAccount(IAccount account);

    String getName();

    String getStreet();

    String getCity();

    String getState();

    Integer getZip();

    String getEmail();

    Collection<IAccount> getAccounts();
}
