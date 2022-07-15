package framework.repository;

import framework.model.IAccount;
import framework.model.ICustomer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IRepository {
    void load();
    void write();
    void loadAccount(ICustomer customer, JSONArray jsonArray);
    void loadEntry(IAccount customer, JSONArray jsonArray);
    void loadCustomer(JSONObject jsonObject);
    void setRepoPath(String path);
    String getRepoPath();
}
