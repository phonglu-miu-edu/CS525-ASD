package framework.database;

import framework.models.Account;
import framework.models.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IRepository {
    void load();
    void load(String path);
    void write();
    void write(String path);
    void loadAccount(Customer customer, JSONArray jsonArray);
    void loadEntry(Account customer, JSONArray jsonArray);
    void loadCustomer(JSONObject jsonObject);
    void setRepoPath(String path);
    String getRepoPath();
}
