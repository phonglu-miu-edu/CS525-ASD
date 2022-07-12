package framework.database;

import org.json.simple.JSONObject;

public interface IDBConnection {
    public JSONObject read(String path);
    public void write(String path, JSONObject jsonObject);
}