package ccard.framework;

public interface ICustomer {
    void addAccount(IAccount account);

    String getName();

    String getPhone();

    String getStreet();

    String getCity();

    String getState();

    String getZip();

    String getEmail();
}
