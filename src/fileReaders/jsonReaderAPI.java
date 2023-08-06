package fileReaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class jsonReaderAPI extends dataCollector{
    // replace # with desired page number
    private String URL_API_ROOT = "https://seeclickfix.com/api/v2/issues?page=#&per_page=100";
    private final String REPLACE_ADDRESS = "(REPLACE_ADDRESS)";
    private String CENSUS_TRACT_URL = "https://geocoding.geo.census.gov/geocoder/geographies/address?street=(REPLACE_ADDRESS)&city=Newark&state=NJ&benchmark=Public_AR_Census2020&vintage=Census2020_Census2020&layers=10&format=json";

    public String parseCensusTract(String address){
        String[] addrSplit = address.split(" ");
        String census_address = "";
        String tractNum = "N/A";

        if (addrSplit.length < 4) {
            return tractNum;
        }
        for (int i = 0; i < 4; i++){

            if (addrSplit[i].equals("Newark")){ return tractNum; }
            if (addrSplit[i + 1].equals("Newark") || addrSplit[i + 1].equals("Newark,") || addrSplit[i + 1].equals("NJ")
                || addrSplit[i + 1].equals("NJ,")){
                census_address += addrSplit[i];
                break;
            }
            census_address += addrSplit[i] + "+";
        }

        try{
            URL url = new URL (CENSUS_TRACT_URL.replace(REPLACE_ADDRESS, census_address));

            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.connect();

            int responsecode = connect.getResponseCode();
            if (responsecode != 200) {
                System.out.println();
                System.out.println(CENSUS_TRACT_URL.replace(REPLACE_ADDRESS, census_address));
                System.out.println(census_address);
                System.out.println();

                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);


                JSONObject res = (JSONObject) data_obj.get("result");
                JSONArray arr = (JSONArray) res.get("addressMatches");
                if (!(arr.size() == 0)){
                    for (int i = 0; i < arr.size(); i++){
                        JSONObject obj = (JSONObject) arr.get(i);
                        JSONObject geo = (JSONObject) obj.get("geographies");
                        JSONArray blockArr = (JSONArray) geo.get("Census Blocks");
                        if (!(blockArr.size() == 0)){
                            for (int j = 0; j < blockArr.size(); j++){
                                JSONObject block = (JSONObject) blockArr.get(j);
                                tractNum = (String) block.get("TRACT");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tractNum;
    }

    public jsonReaderAPI(){}

    public void parseDataAPI(String pageNumber) throws IOException, ParseException {
        //creates URL with new page number to retrieve data from
        try{
            URL url = new URL (URL_API_ROOT.replace("#", pageNumber));

            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.connect();

            int responsecode = connect.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parser = new JSONParser();
                JSONObject data_obj = (JSONObject) parser.parse(inline);

                //Get the required object from the read json file
                JSONArray arr = (JSONArray) data_obj.get("issues");

                for (int i = 0; i < arr.size(); i++){
                    JSONObject newarkObj = (JSONObject) arr.get(i);
                    JSONObject requestType = (JSONObject) newarkObj.get("request_type");
                    if (!(requestType.get("organization") == null)){
                        String organization = requestType.get("organization").toString();
                        if (organization.equals("City of Newark")){
                            System.out.println(organization);
                            addToObjArray(newarkObj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadAndParseFromFileReader(jsonFileReader fileReader) throws IOException, ParseException {
        fileReader.importOldJSON();
        for (int i = 0; i < fileReader.getObjHolderSize(); i++){
            JSONObject fileObj = fileReader.getObjHolderObject(i);
            addToObjArray(fileObj);
        }
    }



}
