package framework.model;

public interface ICustomer {
    void addAccount(Account account);

    String getName();

    String getStreet();

    String getCity();

    String getState();

    Integer getZip();

    String getEmail();
}
