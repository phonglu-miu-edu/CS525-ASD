package framework.database;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBConnection implements IDBConnection {
    private String path = "./db.json";

    @Override
    public JSONObject read(String path) {
        JSONParser jsonParser = new JSONParser();

        if (path == null) {
            path = this.path;
        }

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(path));

            System.out.println(jsonObject);

            return jsonObject;
        } catch (IOException | ParseException e) {
//            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void write(String path, JSONObject jsonObject) {
        if (path == null) {
            path = this.path;
        }

        try {
            FileWriter file = new FileWriter(path);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
    }
}
