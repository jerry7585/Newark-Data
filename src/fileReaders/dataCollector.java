package fileReaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class dataCollector {
    //depends on user, change accordingly, whichever computer you are on
    private final String dirPath = "/Users/alan/Senior-Design/externalFiles/jsonFiles/newarkData.json";
    private String filePathForIssues = "/Users/alan/Senior-Design/externalFiles/SeeClickFix Report For Sharing.xlsx";
    private JSONArray objHolder;

    public dataCollector(){
        objHolder = new JSONArray();
    }

    public void addToObjArray(JSONObject newarkObj){
        objHolder.add(newarkObj);
    }

    public JSONObject getObjHolderObject(int pos){
        return (JSONObject) objHolder.get(pos);
    }

    public String getFilePathForIssues(){ return filePathForIssues; }

    public int getObjHolderSize(){
        return objHolder.size();
    }

    public void transferDataToJSON(){
        try {
            FileWriter file = new FileWriter(dirPath);
            file.write("{\n\"issues\": [ \n");

            for (int i = 0; i < objHolder.size() - 1; i++){
                JSONObject addObj = (JSONObject) objHolder.get(i);
                file.write(addObj.toJSONString() + ",\n\n");
            }
            if (objHolder.size() > 0){
                file.write(((JSONObject) objHolder.get(objHolder.size() - 1)).toJSONString() + "\n\n");
            }
            file.write("]\n}");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
