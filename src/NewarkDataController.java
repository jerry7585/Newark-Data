import fileReaders.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class NewarkDataController {
    public static void main (String[] args) throws IOException, ParseException {
//        jsonReaderAPI readerAPI = new jsonReaderAPI();
//        jsonFileReader fileReader = new jsonFileReader();
//
//        readerAPI.loadAndParseFromFileReader(fileReader);
//
//        Scanner in  = new Scanner(System.in);
//
//        System.out.println("Enter pages you want to parse: ");
//        String pages = in.nextLine();
//
//        int pageNumber = Integer.parseInt(pages);
//        for (int i = pageNumber; i >= pageNumber - 100; i--){
//            String pageString = "" + i;
//            readerAPI.parseDataAPI(pageString);
//        }
//
//        readerAPI.transferDataToJSON();

        //original
//        xlsxManager xlsx = new xlsxManager();
//        xlsx.readXlsxIssues();
//        xlsx.writeXlsx();

        binManager bins = new binManager();
        bins.parseXlsxIntoBins();
    }
}
