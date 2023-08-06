package fileReaders;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class jsonFileReader extends dataCollector {
    //depends on user, change accordingly, whichever computer you are on
    private String filePath = "/Users/alan/Senior-Design/externalFiles/jsonFiles/newarkData.json";
    private JSONParser parser;

    public jsonFileReader(){
        parser = new JSONParser();
    }

    public void importOldJSON() throws IOException, ParseException {
        FileReader reader = new FileReader(filePath);
        JSONObject entireObj = (JSONObject) parser.parse(reader);
        JSONArray oldObj = (JSONArray) entireObj.get("issues");

        for (int i = 0; i < oldObj.size(); i++){
            JSONObject readObj = (JSONObject) oldObj.get(i);
            addToObjArray(readObj);
        }

        reader.close();
    }



}
