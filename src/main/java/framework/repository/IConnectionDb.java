package framework.repository;

import org.json.simple.JSONObject;

public interface IConnectionDb {
    public JSONObject read(String path);
    public void write(String path, JSONObject jsonObject);

}
