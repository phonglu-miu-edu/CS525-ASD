package framework.repository;

import framework.model.Account;
import framework.model.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IRepository {
    public void load(String path);
    public void write(String path);
    public void loadAccount(Customer customer, JSONArray jsonArray);
    public void loadEntry(Account customer, JSONArray jsonArray);
    public void loadCustomer(JSONObject jsonObject);
    public void setRepoPath(String path);
    public String getRepoPath();
}
