package fileReaders;
import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsxManager extends dataCollector{
    private String CATEGORIZED_ASIAN_PERCENT_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedAsianRace/?_asianPercent.xlsx";
    private String CATEGORIZED_AMERICAN_INDIAN_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedAmericanIndianRace/?_americanIndianPercent.xlsx";
    private String CATEGORIZED_WHITE_PERCENT_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedWhiteRace/?_whitePercent.xlsx";
    private String CATEGORIZED_BLACK_PERCENT_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedBlackRace/?_blackPercent.xlsx";
    private String CATEGORIZED_ASIAN_FAIRNESS_PATH = "/Users/alan/Senior-Design/externalFiles/asianRaceFairness/?_asianFairness.xlsx";
    private String CATEGORIZED_AMERICAN_INDIAN_FAIRNESS_PATH = "/Users/alan/Senior-Design/externalFiles/americanIndianRaceFairness/?_americanIndianFairness.xlsx";
    private String CATEGORIZED_WHITE_FAIRNESS_PATH = "/Users/alan/Senior-Design/externalFiles/whiteRaceFairness/?_whiteFairness.xlsx";
    private String CATEGORIZED_BLACK_FAIRNESS_PATH = "/Users/alan/Senior-Design/externalFiles/blackRaceFairness/?_blackFairness.xlsx";
    private Map<String, List<xlsxIssueObj>> storeXlsx = new HashMap<String, List<xlsxIssueObj>>();
    private String xlsxCategorizedIssuesFilePathName = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/?_issues.xlsx";
    private String xlsxFairnessPathName = "/Users/alan/Senior-Design/externalFiles/categorizedFairness/?_fairness.xlsx";
    private final String CENSUS_INCOME_PATH = "/Users/alan/Senior-Design/externalFiles/census/census_income.xlsx";
    private final String CENSUS_RACE_PATH = "/Users/alan/Senior-Design/externalFiles/census/census_tract_race.xlsx";
    private final String ABANDONED_PROPERTY_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/abandonedProperty_issues.xlsx";
    private final String ANIMAL_COMPLAINTS_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/animalComplaint_issues.xlsx";
    private final String BUSINESS_COMPLAINTS_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/businessComplaints_issues.xlsx";

    public String getXlsxFairnessPathName(){ return xlsxFairnessPathName;}
    public String getCategorizedAsianFairnessPath() { return CATEGORIZED_ASIAN_FAIRNESS_PATH;}
    public String getCategorizedAmericanIndianFairnessPath(){ return CATEGORIZED_AMERICAN_INDIAN_FAIRNESS_PATH;}
    public String getCategorizedWhiteFairnessPath(){ return CATEGORIZED_WHITE_FAIRNESS_PATH;}
    public String getCategorizedBlackFairnessPath(){ return CATEGORIZED_BLACK_FAIRNESS_PATH;}
    public String getCategorizedAsianPercentPath(){ return CATEGORIZED_ASIAN_PERCENT_PATH;}
    public String getCategorizedAmericanIndianPath(){ return CATEGORIZED_AMERICAN_INDIAN_PATH;}
    public String getCategorizedWhitePercentPath() { return CATEGORIZED_WHITE_PERCENT_PATH;}
    public String getCategorizedBlackPercentPath() {return CATEGORIZED_BLACK_PERCENT_PATH;}
    public String getAnimalComplaintsPath() {
        return ANIMAL_COMPLAINTS_PATH;
    }

    public String getBusinessComplaints() {
        return BUSINESS_COMPLAINTS_PATH;
    }

    public String getDamagedSidewalkPath() {
        return DAMAGED_SIDEWALK_PATH;
    }

    public String getEnvironmentalComplaintPath() {
        return ENVIRONMENTAL_COMPLAINT_PATH;
    }

    public String getFireCodeViolationPath() {
        return FIRE_CODE_VIOLATION_PATH;
    }

    public String getGraffitiIssuePath() {
        return GRAFFITI_ISSUE_PATH;
    }

    public String getHeatIssuePath() {
        return HEAT_ISSUE_PATH;
    }

    public String getHomeInspectionPath() {
        return HOME_INSPECTION_PATH;
    }

    public String getIllegalActivityPath() {
        return ILLEGAL_ACTIVITY_PATH;
    }

    public String getIllegalConstructionPath() {
        return ILLEGAL_CONSTRUCTION_PATH;
    }

    public String getIllegalDumpingPath() {
        return ILLEGAL_DUMPING_PATH;
    }

    public String getLeadServicePath() {
        return LEAD_SERVICE_PATH;
    }

    public String getManholeIssuePath() {
        return MANHOLE_ISSUE_PATH;
    }

    public String getMissedPickupIssuePath() {
        return MISSED_PICKUP_ISSUE_PATH;
    }

    public String getNoiseIssuePath() {
        return NOISE_ISSUE_PATH;
    }

    public String getOpenHydrantIssuePath() {
        return OPEN_HYDRANT_ISSUE_PATH;
    }

    public String getOtherIssuePath() {
        return OTHER_ISSUE_PATH;
    }

    public String getParkingViolationPath() {
        return PARKING_VIOLATION_PATH;
    }

    public String getParkStructurePath() {
        return PARK_STRUCTURE_PATH;
    }

    public String getPotholeIssuesPath() {
        return POTHOLE_ISSUES_PATH;
    }

    public String getRodentInfestationPath() {
        return RODENT_INFESTATION_PATH;
    }

    public String getSewerIssuesPath() {
        return SEWER_ISSUES_PATH;
    }

    public String getStreetCleanupPath() {
        return STREET_CLEANUP_PATH;
    }

    public String getTrafficIssuePath() {
        return TRAFFIC_ISSUE_PATH;
    }

    public String getTreeRemovalIssuePath() {
        return TREE_REMOVAL_ISSUE_PATH;
    }

    public String getWaterIssuesPath() {
        return WATER_ISSUES;
    }

    //depends on user, change accordingly, whichever computer you are on
    private final String DAMAGED_SIDEWALK_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/damagedSidewalk_issues.xlsx";
    private final String ENVIRONMENTAL_COMPLAINT_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/environmentalComplaint_issues.xlsx";
    private final String FIRE_CODE_VIOLATION_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/fireCodeVio_issues.xlsx";
    private final String GRAFFITI_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/graffiti_issues.xlsx";
    private final String HEAT_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/heat_issues.xlsx";
    private final String HOME_INSPECTION_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/homeInspection_issues.xlsx";
    private final String ILLEGAL_ACTIVITY_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/illegalActivity_issues.xlsx";
    private final String ILLEGAL_CONSTRUCTION_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/illegalConstruction_issues.xlsx";
    private final String ILLEGAL_DUMPING_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/illegalDumping_issues.xlsx";
    private final String LEAD_SERVICE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/leadService_issues.xlsx";
    private final String MANHOLE_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/manhole_issues.xlsx";
    private final String MISSED_PICKUP_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/missedPickup_issues.xlsx";
    private final String NOISE_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/noise_issues.xlsx";
    private final String OPEN_HYDRANT_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/openHydrant_issues.xlsx";
    private final String OTHER_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/other_issues.xlsx";
    private final String PARKING_VIOLATION_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/parkingVio_issues.xlsx";
    private final String PARK_STRUCTURE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/parkStructure_issues.xlsx";
    private final String POTHOLE_ISSUES_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/pothole_issues.xlsx";
    private final String RODENT_INFESTATION_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/rodentInfestation_issues.xlsx";
    private final String SEWER_ISSUES_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/sewer_issues.xlsx";
    private final String STREET_CLEANUP_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/streetCleanUp_issues.xlsx";
    private final String TRAFFIC_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/traffic_issues.xlsx";
    private final String TREE_REMOVAL_ISSUE_PATH = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/treeRemoval_issues.xlsx";
    private final String WATER_ISSUES = "/Users/alan/Senior-Design/externalFiles/categorizedIssues/water_issues.xlsx";

    private jsonReaderAPI readerAPI = new jsonReaderAPI();

    //Other
    private List<xlsxIssueObj> otherIssues = new ArrayList<>();
    //Parking Violations (non-hazardous)
    private List<xlsxIssueObj> parkingViolationIssues = new ArrayList<>();
    //Water Issues
    private List<xlsxIssueObj> waterIssues = new ArrayList<>();
    //Animal Complaint
    private List<xlsxIssueObj> animalComplaintIssues = new ArrayList<>();
    //Sewer/ Catch Basin
    private List<xlsxIssueObj> sewerIssues = new ArrayList<>();
    //Pothole Complaint
    private List<xlsxIssueObj> potholeIssues = new ArrayList<>();
    //Missed Pick-Up Complaint (Recycling/ Garbage/ Bulk)
    private List<xlsxIssueObj> missedPickupIssues = new ArrayList<>();
    //Rodent Infestation (Exterior Only)
    private List<xlsxIssueObj> rodentIssues = new ArrayList<>();
    //Open Fire Hydrant Complaint
    private List<xlsxIssueObj> fireHydrantIssues = new ArrayList<>();
    //Lead Service Line Request
    private List<xlsxIssueObj> leadServiceIssues = new ArrayList<>();
    //Graffiti Removal Request
    private List<xlsxIssueObj> graffitiIssues = new ArrayList<>();
    //Tree Trim Removal Request
    private  List<xlsxIssueObj> treeIssues = new ArrayList<>();
    //Traffic: Signal, Signage, Light Pole and Striping Maintenance Issues
    private List<xlsxIssueObj> trafficIssues = new ArrayList<>();
    //Environmental Complaint (Garbage & Debris, Weeds/ Vegetation, etc.)
    private  List<xlsxIssueObj> environmentIssues = new ArrayList<>();
    //Illegal Dumping Complaint
    private List<xlsxIssueObj> illegalDumpingIssues = new ArrayList<>();
    //Manhole
    private List<xlsxIssueObj> manholeIssues = new ArrayList<>();
    //Home Inspection Request (INTERIOR ONLY- Mold, Damaged Ceilings, Rodent Infestation, etc.)
    private List<xlsxIssueObj> homeInspectionIssues = new ArrayList<>();
    //Street Clean-Up Request
    private List<xlsxIssueObj> streetCleanupIssues = new ArrayList<>();
    //Heat/ Hot Water
    private List<xlsxIssueObj> heatIssues = new ArrayList<>();
    //Abandoned Property
    private List<xlsxIssueObj> abandonedPropertyIssues = new ArrayList<>();
    //Illegal Construction
    private  List<xlsxIssueObj> illegalConstructionIssues = new ArrayList<>();
    //Illegal Activity
    private List<xlsxIssueObj> illegalActivityIssues = new ArrayList<>();
    //Damaged Sidewalk
    private List<xlsxIssueObj> damagedSidewalkIssues = new ArrayList<>();
    //Fire Code Violations
    private List<xlsxIssueObj> fireCodeIssues = new ArrayList<>();
    //Business Complaints
    private List<xlsxIssueObj> businessComplaintIssues = new ArrayList<>();
    //Parks- Structural Deficiencies (Broken Benches, Hole in Ground/ Sidewalk)
    private List<xlsxIssueObj> parkStructureIssues = new ArrayList<>();
    //Noise Disturbance
    private List<xlsxIssueObj> noiseDisturbanceIssues = new ArrayList<>();

    public xlsxManager(){
    }

    public String getAbandonedPropertyPath(){
        return  ABANDONED_PROPERTY_PATH;
    }

    public void readXlsxIssues() throws IOException {
        FileInputStream issueReader = new FileInputStream(getFilePathForIssues());
        XSSFWorkbook xlsxWorkbook = new XSSFWorkbook(issueReader);

        XSSFSheet issueSheet = xlsxWorkbook.getSheetAt(0);
        for (int j = 1; j < issueSheet.getPhysicalNumberOfRows(); j++){
            double latitude = 0, longitude = 0;
            double issueNumber = 0, rating = 0, agencyID = 0, requestTypeID = 0, slaHours = 0, agentID = 0, minutesAcknowledged = 0, minutesToClosed = 0;
            String status = "", summary = "", address = "", description = "", agencyName = "", exportedTags = "", requestType = "";
            String updatedAtLocal = "", createdAtLocal = "", acknowledgedAtLocal = "", reopenedAtLocal = "", closedAtLocal = "";
            String assigneeName = "", streetAddress = "", category = "", slaExpiresAtLocal = "", agentName = "", reportMethod = "";
            String reporterName = "", dueAtLocal = "", reportSoruce = "", reportMethodCode = "", tractNum = "";
            boolean createdByMember = false;
            for (int i = 0; i < issueSheet.getRow(0).getPhysicalNumberOfCells(); i++){
                switch (i){
                    case 0: issueNumber = issueSheet.getRow(j).getCell(i).getNumericCellValue();break;
                    case 1: status = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 2: summary = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 3: rating = issueSheet.getRow(j).getCell(i).getNumericCellValue(); break;
                    case 4: address = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 5: description = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 6: agencyName = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 7: agencyID = issueSheet.getRow(j).getCell(i).getNumericCellValue();break;
                    case 8: requestTypeID = issueSheet.getRow(j).getCell(i).getNumericCellValue();break;
                    case 9: latitude = issueSheet.getRow(j).getCell(i).getNumericCellValue();break;
                    case 10: longitude = issueSheet.getRow(j).getCell(i).getNumericCellValue();break;
                    case 11: exportedTags = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 12: requestType = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 13: updatedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 14: createdAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 15: acknowledgedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    case 16: reopenedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 17:
                        if (issueSheet.getRow(j).getCell(i) != null) closedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 18:
                        if (issueSheet.getRow(j).getCell(i) != null) minutesAcknowledged = issueSheet.getRow(j).getCell(i).getNumericCellValue(); break;
                    case 19:
                        if (issueSheet.getRow(j).getCell(i) != null) minutesToClosed = issueSheet.getRow(j).getCell(i).getNumericCellValue(); break;
                    case 20:
                        if (issueSheet.getRow(j).getCell(i) != null) assigneeName = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 21:
                        if (issueSheet.getRow(j).getCell(i).getCellTypeEnum() == CellType.STRING) streetAddress = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        else { }break;
                    case 22: category = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 23: slaHours = issueSheet.getRow(j).getCell(i).getNumericCellValue(); break;
                    case 24: slaExpiresAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 25:
                        if (issueSheet.getRow(j).getCell(i) != null) agentName = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 26:
                        if (issueSheet.getRow(j).getCell(i) != null) agentID = issueSheet.getRow(j).getCell(i).getNumericCellValue(); break;
                    case 27: reportMethodCode = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 28:
                        if (issueSheet.getRow(j).getCell(i) != null)  reportMethod = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 29: reporterName = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 30:
                        if (issueSheet.getRow(j).getCell(i) != null) dueAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 31: if (issueSheet.getRow(j).getCell(i) != null) reportSoruce = issueSheet.getRow(j).getCell(i).getStringCellValue(); break;
                    case 32: createdByMember = issueSheet.getRow(j).getCell(i).getBooleanCellValue();break;
                    case 33: tractNum = issueSheet.getRow(j).getCell(i).getStringCellValue();break;
                    default: break;
                }
            }
            xlsxIssueObj obj = new xlsxIssueObj(issueNumber, status, summary, rating, address, description,
                    agencyName, agencyID, requestTypeID, latitude, longitude, exportedTags, requestType, updatedAtLocal, createdAtLocal,
                    acknowledgedAtLocal, reopenedAtLocal, closedAtLocal, minutesAcknowledged, minutesToClosed, assigneeName, streetAddress,
                    category, slaHours, slaExpiresAtLocal, agentName, agentID, reportMethodCode, reportMethod, reporterName, dueAtLocal,
                    reportSoruce, createdByMember, tractNum);

            switch(category){
                case "Other": otherIssues.add(obj);break;
                case "Parking Violations (non-hazardous)": parkingViolationIssues.add(obj);break;
                case "Water Issues": waterIssues.add(obj);break;
                case "Animal Complaint": animalComplaintIssues.add(obj);break;
                case "Rodent Infestation (Exterior Only)": rodentIssues.add(obj);break;
                case "Sewer/ Catch Basin": sewerIssues.add(obj);break;
                case "Missed Pick-Up Complaint (Recycling/ Garbage/ Bulk)": missedPickupIssues.add(obj);break;
                case "Open Fire Hydrant Complaint": fireHydrantIssues.add(obj);break;
                case "Pothole Complaint": potholeIssues.add(obj);break;
                case "Lead Service Line Request": leadServiceIssues.add(obj);break;
                case "Graffiti Removal Request": graffitiIssues.add(obj);break;
                case "Tree Trim/ Removal Request": treeIssues.add(obj);break;
                case "Traffic: Signal, Signage, Light Pole and Striping Maintenance Issues": trafficIssues.add(obj);break;
                case "Environmental Complaint (Garbage & Debris, Weeds/ Vegetation, etc.)": environmentIssues.add(obj);break;
                case "Illegal Dumping Complaint": illegalDumpingIssues.add(obj);break;
                case "Manhole": manholeIssues.add(obj);break;
                case "Home Inspection Request (INTERIOR ONLY- Mold, Damaged Ceilings, Rodent Infestation, etc.)": homeInspectionIssues.add(obj);break;
                case "Street Clean-Up Request":  streetCleanupIssues.add(obj);break;
                case "Heat/ Hot Water": heatIssues.add(obj);break;
                case "Illegal Construction": illegalConstructionIssues.add(obj);break;
                case "Abandoned Property": abandonedPropertyIssues.add(obj);break;
                case "Illegal Activity (Alcohol, Gambling, Drugs, Prostitution)": illegalActivityIssues.add(obj);break;
                case "Damaged Sidewalk": damagedSidewalkIssues.add(obj);break;
                case "Fire Code Violations": fireCodeIssues.add(obj);break;
                case "Business Complaints": businessComplaintIssues.add(obj);break;
                case "Parks- Structural Deficiencies (Broken Benches, Hole in Ground/ Sidewalk)": parkStructureIssues.add(obj);break;
                case "Noise Disturbance":noiseDisturbanceIssues.add(obj);break;
                default: break;
            }

            storeXlsx.put("Other", otherIssues);
            storeXlsx.put("Parking Violations (non-hazardous)", parkingViolationIssues);
            storeXlsx.put("Water Issues", waterIssues);
            storeXlsx.put("Animal Complaint", animalComplaintIssues);
            storeXlsx.put("Rodent Infestation (Exterior Only)", rodentIssues);
            storeXlsx.put("Sewer/ Catch Basin", sewerIssues);
            storeXlsx.put("Missed Pick-Up Complaint (Recycling/ Garbage/ Bulk)", missedPickupIssues);
            storeXlsx.put("Open Fire Hydrant Complaint", fireHydrantIssues);
            storeXlsx.put("Pothole Complaint", potholeIssues);
            storeXlsx.put("Lead Service Line Request", leadServiceIssues);
            storeXlsx.put("Graffiti Removal Request", graffitiIssues);
            storeXlsx.put("Tree Trim/ Removal Request", treeIssues);
            storeXlsx.put("Traffic: Signal, Signage, Light Pole and Striping Maintenance Issues", trafficIssues);
            storeXlsx.put("Environmental Complaint (Garbage & Debris, Weeds/ Vegetation, etc.)", environmentIssues);
            storeXlsx.put("Illegal Dumping Complaint", illegalDumpingIssues);
            storeXlsx.put("Manhole", manholeIssues);
            storeXlsx.put("Home Inspection Request (INTERIOR ONLY- Mold, Damaged Ceilings, Rodent Infestation, etc.)", homeInspectionIssues);
            storeXlsx.put("Street Clean-Up Request",  streetCleanupIssues);
            storeXlsx.put("Heat/ Hot Water", heatIssues);
            storeXlsx.put("Illegal Construction", illegalConstructionIssues);
            storeXlsx.put("Abandoned Property", abandonedPropertyIssues);
            storeXlsx.put("Illegal Activity (Alcohol, Gambling, Drugs, Prostitution)", illegalActivityIssues);
            storeXlsx.put("Damaged Sidewalk", damagedSidewalkIssues);
            storeXlsx.put("Fire Code Violations", fireCodeIssues);
            storeXlsx.put("Business Complaints", businessComplaintIssues);
            storeXlsx.put("Parks- Structural Deficiencies (Broken Benches, Hole in Ground/ Sidewalk)", parkStructureIssues);
            storeXlsx.put("Noise Disturbance", noiseDisturbanceIssues);

        }



    }

    public Map<Double, Double> parseRaceXlsx(String raceString) throws IOException {
        Map<Double, Double> censusPopulationPercent = new HashMap<>();
        FileInputStream issueReader = new FileInputStream(CENSUS_RACE_PATH);
        XSSFWorkbook xlsxWorkbook = new XSSFWorkbook(issueReader);
        XSSFSheet issueSheet = xlsxWorkbook.getSheetAt(0);

        int raceIndex = 0;
        switch (raceString){
            case "Not Hispanic or Latino: - White alone": raceIndex = 5; break;
            case "Not Hispanic or Latino: - Black or African American alone": raceIndex = 6; break;
            case "Not Hispanic or Latino: - American Indian and Alaska Native alone": raceIndex = 7; break;
            case "Not Hispanic or Latino: - Asian alone": raceIndex = 8; break;
        }

        for (int i = 2; i < issueSheet.getPhysicalNumberOfRows(); i++) {
            String censusTract = issueSheet.getRow(i).getCell(2).getStringCellValue();
            String[] splitTractString = censusTract.split(" ");
            String tractWithComma = splitTractString[2];
            censusTract = tractWithComma.substring(0, tractWithComma.length() - 1);
            Double censusTractDouble = Double.parseDouble(censusTract);

            double totalPop = issueSheet.getRow(i).getCell(3).getNumericCellValue();
            double racePop = issueSheet.getRow(i).getCell(raceIndex).getNumericCellValue();
            double racePercent = racePop / totalPop;
            censusPopulationPercent.put(censusTractDouble, racePercent);
        }


        return censusPopulationPercent;
    }

    public Map<Double, Double> parseCensusIncomeXlsx() throws IOException {
        Map<Double, Double> censusIncome = new HashMap<>();
        FileInputStream issueReader = new FileInputStream(CENSUS_INCOME_PATH);
        XSSFWorkbook xlsxWorkbook = new XSSFWorkbook(issueReader);

        XSSFSheet issueSheet = xlsxWorkbook.getSheetAt(0);
        for (int j = 0; j < issueSheet.getPhysicalNumberOfRows(); j++) {
            String censusTract = issueSheet.getRow(j).getCell(1).getStringCellValue();
            double income = issueSheet.getRow(j).getCell(2).getNumericCellValue();

            String[] splitTractString = censusTract.split(" ");
            String tractWithComma = splitTractString[2];
            censusTract = tractWithComma.substring(0, tractWithComma.length() - 1);
            Double censusTractDouble = Double.parseDouble(censusTract);
            censusIncome.put(censusTractDouble, income);
        }
        return censusIncome;
    }


    public List<xlsxIssueObj> parseSpecificXlsx(String categoryXlsxFilePath) throws IOException {
        FileInputStream issueReader = new FileInputStream(categoryXlsxFilePath);
        XSSFWorkbook xlsxWorkbook = new XSSFWorkbook(issueReader);
        List<xlsxIssueObj> objList = new ArrayList<>();
        String tractNum = "";
        XSSFSheet issueSheet = xlsxWorkbook.getSheetAt(0);
        for (int j = 1; j < issueSheet.getPhysicalNumberOfRows(); j++) {
            double latitude = 0, longitude = 0;
            double issueNumber = 0, rating = 0, agencyID = 0, requestTypeID = 0, slaHours = 0, agentID = 0, minutesAcknowledged = 0, minutesToClosed = 0;
            String status = "", summary = "", address = "", description = "", agencyName = "", exportedTags = "", requestType = "";
            String updatedAtLocal = "", createdAtLocal = "", acknowledgedAtLocal = "", reopenedAtLocal = "", closedAtLocal = "";
            String assigneeName = "", streetAddress = "", category = "", slaExpiresAtLocal = "", agentName = "", reportMethod = "";
            String reporterName = "", dueAtLocal = "", reportSoruce = "", reportMethodCode = "";
            boolean createdByMember = false;
            for (int i = 0; i < issueSheet.getRow(0).getPhysicalNumberOfCells(); i++) {
                switch (i) {
                    case 0:
                        issueNumber = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 1:
                        status = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 2:
                        summary = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 3:
                        rating = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 4:
                        address = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 5:
                        description = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 6:
                        agencyName = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 7:
                        agencyID = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 8:
                        requestTypeID = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 9:
                        latitude = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 10:
                        longitude = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 11:
                        exportedTags = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 12:
                        requestType = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 13:
                        updatedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 14:
                        createdAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 15:
                        acknowledgedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 16:
                        reopenedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 17:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            closedAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 18:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            minutesAcknowledged = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 19:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            minutesToClosed = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 20:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            assigneeName = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 21:
                        if (issueSheet.getRow(j).getCell(i).getCellTypeEnum() == CellType.STRING)
                            streetAddress = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        else {
                        }
                        break;
                    case 22:
                        category = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 23:
                        slaHours = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 24:
                        slaExpiresAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 25:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            agentName = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 26:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            agentID = issueSheet.getRow(j).getCell(i).getNumericCellValue();
                        break;
                    case 27:
                        reportMethodCode = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 28:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            reportMethod = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 29:
                        reporterName = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 30:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            dueAtLocal = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 31:
                        if (issueSheet.getRow(j).getCell(i) != null)
                            reportSoruce = issueSheet.getRow(j).getCell(i).getStringCellValue();
                        break;
                    case 32:
                        createdByMember = issueSheet.getRow(j).getCell(i).getBooleanCellValue();
                        break;
                    case 33:
                        tractNum = issueSheet.getRow(j).getCell(i).getStringCellValue();
                    default:
                        break;
                }
            }
            xlsxIssueObj obj = new xlsxIssueObj(issueNumber, status, summary, rating, address, description,
                    agencyName, agencyID, requestTypeID, latitude, longitude, exportedTags, requestType, updatedAtLocal, createdAtLocal,
                    acknowledgedAtLocal, reopenedAtLocal, closedAtLocal, minutesAcknowledged, minutesToClosed, assigneeName, streetAddress,
                    category, slaHours, slaExpiresAtLocal, agentName, agentID, reportMethodCode, reportMethod, reporterName, dueAtLocal,
                    reportSoruce, createdByMember, tractNum);
            objList.add(obj);
        }
        return objList;
    }

    public void writeSpecificXlsxList(List<xlsxIssueObj> objList, String fileName, String filePath) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet");
        parseListIntoXlsx(wb, sheet, objList, fileName, filePath);
    }

    public void writeXlsx() throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet other = wb.createSheet("Other");
        parseIntoXlsx(wb, other, "Other", "other",xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb2 = new XSSFWorkbook();
        XSSFSheet parkingViolation = wb2.createSheet("Parking Violations (non-hazardous)");
        parseIntoXlsx(wb2, parkingViolation, "Parking Violations (non-hazardous)", "parkingVio", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb3 = new XSSFWorkbook();
        XSSFSheet water = wb3.createSheet("Water Issues");
        parseIntoXlsx(wb3, water, "Water Issues", "water", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb4 = new XSSFWorkbook();
        XSSFSheet animalComplaint = wb4.createSheet("Animal Complaint");
        parseIntoXlsx(wb4, animalComplaint, "Animal Complaint", "animalComplaint", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb5 = new XSSFWorkbook();
        XSSFSheet sewer = wb5.createSheet("Sewer Catch Basin");
        parseIntoXlsx(wb5, sewer, "Sewer/ Catch Basin", "sewer", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb6 = new XSSFWorkbook();
        XSSFSheet pothole = wb6.createSheet("Pothole Complaint");
        parseIntoXlsx(wb6, pothole, "Pothole Complaint", "pothole", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb7 = new XSSFWorkbook();
        XSSFSheet missedPickup = wb7.createSheet("Missed Pick Up Complaint");
        parseIntoXlsx(wb7, missedPickup, "Missed Pick-Up Complaint (Recycling/ Garbage/ Bulk)", "missedPickup", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb8 = new XSSFWorkbook();
        XSSFSheet rodent = wb8.createSheet("Rodent Infestation");
        parseIntoXlsx(wb8, rodent, "Rodent Infestation (Exterior Only)", "rodentInfestation", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb9 = new XSSFWorkbook();
        XSSFSheet fireHydrant = wb9.createSheet("Open Fire Hydrant Complaint");
        parseIntoXlsx(wb9, fireHydrant, "Open Fire Hydrant Complaint", "openHydrant", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb10 = new XSSFWorkbook();
        XSSFSheet leadService = wb10.createSheet("Lead Service Line Request");
        parseIntoXlsx(wb10, leadService, "Lead Service Line Request", "leadService", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb11 = new XSSFWorkbook();
        XSSFSheet graffiti = wb11.createSheet("Graffiti Removal Request");
        parseIntoXlsx(wb11, graffiti, "Graffiti Removal Request", "graffiti", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb12 = new XSSFWorkbook();
        XSSFSheet tree = wb12.createSheet("Tree Removal Request");
        parseIntoXlsx(wb12, tree, "Tree Trim/ Removal Request", "treeRemoval", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb13 = new XSSFWorkbook();
        XSSFSheet traffic = wb13.createSheet("Traffic");
        parseIntoXlsx(wb13, traffic, "Traffic: Signal, Signage, Light Pole and Striping Maintenance Issues", "traffic", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb14 = new XSSFWorkbook();
        XSSFSheet environmental = wb14.createSheet("Environmental Complaint");
        parseIntoXlsx(wb14, environmental, "Environmental Complaint (Garbage & Debris, Weeds/ Vegetation, etc.)", "environmentalComplaint", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb15 = new XSSFWorkbook();
        XSSFSheet illegalDumping = wb15.createSheet("Illegal Dumping Complaint");
        parseIntoXlsx(wb15, illegalDumping, "Illegal Dumping Complaint", "illegalDumping",xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb16 = new XSSFWorkbook();
        XSSFSheet manhole = wb16.createSheet("Manhole");
        parseIntoXlsx(wb16, manhole, "Manhole", "manhole", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb17 = new XSSFWorkbook();
        XSSFSheet homeInspection = wb17.createSheet("Home Inspection Request");
        parseIntoXlsx(wb17, homeInspection, "Home Inspection Request (INTERIOR ONLY- Mold, Damaged Ceilings, Rodent Infestation, etc.)", "homeInspection",xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb18 = new XSSFWorkbook();
        XSSFSheet streetCleanup = wb18.createSheet("Street Clean Up");
        parseIntoXlsx(wb18, streetCleanup, "Street Clean-Up Request", "streetCleanUp",xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb19 = new XSSFWorkbook();
        XSSFSheet heat = wb19.createSheet("Heat Hot Water");
        parseIntoXlsx(wb19, heat, "Heat/ Hot Water", "heat", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb20 = new XSSFWorkbook();
        XSSFSheet abandonedProperty = wb20.createSheet("Abandoned Property");
        parseIntoXlsx(wb20, abandonedProperty, "Abandoned Property", "abandonedProperty", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb21 = new XSSFWorkbook();
        XSSFSheet illegalConstruction = wb21.createSheet("Illegal Construction");
        parseIntoXlsx(wb21, illegalConstruction, "Illegal Construction", "illegalConstruction", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb22 = new XSSFWorkbook();
        XSSFSheet illegalActivity = wb22.createSheet("Illegal Activity");
        parseIntoXlsx(wb22, illegalActivity, "Illegal Activity (Alcohol, Gambling, Drugs, Prostitution)", "illegalActivity", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb23 = new XSSFWorkbook();
        XSSFSheet damagedSidewalk = wb23.createSheet("Damaged Sidewalk");
        parseIntoXlsx(wb23, damagedSidewalk, "Damaged Sidewalk", "damagedSidewalk", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb24 = new XSSFWorkbook();
        XSSFSheet fireCode = wb24.createSheet("Fire Code Violations");
        parseIntoXlsx(wb24, fireCode, "Fire Code Violations", "fireCodeVio", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb25 = new XSSFWorkbook();
        XSSFSheet businessComplaints = wb25.createSheet("Business Complaints");
        parseIntoXlsx(wb25, businessComplaints, "Business Complaints", "businessComplaints", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb26 = new XSSFWorkbook();
        XSSFSheet parkConstruction = wb26.createSheet("Park Structure");
        parseIntoXlsx(wb26, parkConstruction, "Parks- Structural Deficiencies (Broken Benches, Hole in Ground/ Sidewalk)", "parkStructure", xlsxCategorizedIssuesFilePathName);

        XSSFWorkbook wb27 = new XSSFWorkbook();
        XSSFSheet noiseDisturbance = wb27.createSheet("Noise Disturbance");
        parseIntoXlsx(wb27, noiseDisturbance, "Noise Disturbance", "noise", xlsxCategorizedIssuesFilePathName);






    }

    public void parseIntoXlsx(XSSFWorkbook wb, XSSFSheet sheet, String issueName, String fileName, String filePath) throws IOException {
        Map<Integer, Object[]> sheetData = new TreeMap<Integer, Object[]>();
        XSSFRow row;
        int rowId = 0, posId = 2;
        //initialize the first row
        sheetData.put( 1, new Object[] { "Agbon Generated ID", "Status", "Summary", "Rating", "Address", "Description",
                "Agency Name", "Agency ID",	"Request type", "Lat", "Lng", "Export tagged places", "Request type answers as text",
                "Updated at local", "Created at local", "Acknowledged at local", "Reopened at local", "Closed at local",
                "Minutes to acknowledged", "Minutes to closed", "Assignee name", "Parsed street and number", "Category",
                "Sla in hours",	"Sla expires at local",	"Agent name", "Agent id", "Report method code", "Report method",
                "Reporter Name", "Due at local", "Report Source", "Created by Member" , "Census Tract Number", "Race Percent"});

        for (xlsxIssueObj obj: storeXlsx.get(issueName)){
           sheetData.put(posId++, new Object[]{ obj.getIssueNumber(), obj.getStatus(), obj.getSummary(), obj.getRating(),
                   obj.getAddress(), obj.getDescription(), obj.getAgencyName(), obj.getAgencyID(), obj.getRequestTypeID(),
                   obj.getLatitude(), obj.getLongitude(), obj.getExportedTags(), obj.getRequestType(), obj.getUpdatedAtLocal(),
                   obj.getCreatedAtLocal(), obj.getAcknowledgedAtLocal(), obj.getReopenedAtLocal(), obj.getClosedAtLocal(),
                   obj.getMinutesAcknowledged(), obj.getMinutesToClosed(), obj.getAssigneeName(), obj.getStreetAddress(),
                   obj.getCategory(), obj.getSlaHours(), obj.getSlaExpiresAtLocal(), obj.getAgentName(), obj.getAgentID(),
                   obj.getReportMethodCode(), obj.getReportMethod(), obj.getReporterName(), obj.getDueAtLocal(), obj.getReportSoruce(),
                   obj.getCreatedByMember(), obj.getTractNum(), obj.getRacePercent()});
        }

        Set<Integer> keyid = sheetData.keySet();
        for (int key: keyid){
            row = sheet.createRow(rowId++);
            Object[] objectArr = sheetData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                if (obj instanceof Double){
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Boolean){
                    cell.setCellValue((boolean) obj);
                }else {
                    cell.setCellValue((String) obj);
                }
            }
        }

        FileOutputStream out = new FileOutputStream(filePath.replace("?", fileName));

        wb.write(out);
        out.close();
    }

    public void parseListIntoXlsx(XSSFWorkbook wb, XSSFSheet sheet, List<xlsxIssueObj> objList, String fileName, String filePath) throws IOException {
        Map<Integer, Object[]> sheetData = new TreeMap<Integer, Object[]>();

        XSSFRow row;
        int rowId = 0, posId = 2;
        //initialize the first row
        sheetData.put( 1, new Object[] { "Agbon Generated ID", "Status", "Summary", "Rating", "Address", "Description",
                "Agency Name", "Agency ID",	"Request type", "Lat", "Lng", "Export tagged places", "Request type answers as text",
                "Updated at local", "Created at local", "Acknowledged at local", "Reopened at local", "Closed at local",
                "Minutes to acknowledged", "Minutes to closed", "Assignee name", "Parsed street and number", "Category",
                "Sla in hours",	"Sla expires at local",	"Agent name", "Agent id", "Report method code", "Report method",
                "Reporter Name", "Due at local", "Report Source", "Created by Member" , "Census Tract Number", "Race Percent"});

        for (xlsxIssueObj obj: objList){
            double racePercent = 0;
            if (obj.getTractNum().equals("N/A")) racePercent = 0;
            else racePercent = obj.getRacePercent();

            sheetData.put(posId++, new Object[]{ obj.getIssueNumber(), obj.getStatus(), obj.getSummary(), obj.getRating(),
                    obj.getAddress(), obj.getDescription(), obj.getAgencyName(), obj.getAgencyID(), obj.getRequestTypeID(),
                    obj.getLatitude(), obj.getLongitude(), obj.getExportedTags(), obj.getRequestType(), obj.getUpdatedAtLocal(),
                    obj.getCreatedAtLocal(), obj.getAcknowledgedAtLocal(), obj.getReopenedAtLocal(), obj.getClosedAtLocal(),
                    obj.getMinutesAcknowledged(), obj.getMinutesToClosed(), obj.getAssigneeName(), obj.getStreetAddress(),
                    obj.getCategory(), obj.getSlaHours(), obj.getSlaExpiresAtLocal(), obj.getAgentName(), obj.getAgentID(),
                    obj.getReportMethodCode(), obj.getReportMethod(), obj.getReporterName(), obj.getDueAtLocal(), obj.getReportSoruce(),
                    obj.getCreatedByMember(), obj.getTractNum(), racePercent});
        }

        Set<Integer> keyid = sheetData.keySet();
        for (int key: keyid){
            row = sheet.createRow(rowId++);
            Object[] objectArr = sheetData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                if (obj instanceof Double){
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Boolean){
                    cell.setCellValue((boolean) obj);
                }else {
                    cell.setCellValue((String) obj);
                }
            }
        }

        FileOutputStream out = new FileOutputStream(filePath.replace("?", fileName));

        wb.write(out);
        out.close();
    }

    public void parseBinFairnessIntoXlsx( String categoryName, List<Double> averageTimes, List<Boolean> fairnessList, double avgTimeTotal, boolean fairnessFlag, String filePath) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet categorySheet = wb.createSheet(categoryName);

        Map<Integer, Object[]> sheetData = new TreeMap<Integer, Object[]>();
        XSSFRow row;
        int rowId = 0, posId = 2;
        //initialize the first row
        sheetData.put( 1, new Object[] { "Bin Number", " Average Time ", " Fair? " });

        for (int i = 0; i < averageTimes.size(); i++){
            sheetData.put(posId++, new Object[]{ i + 1, averageTimes.get(i), fairnessList.get(i)});
        }

        sheetData.put(posId++, new Object[]{ "Total", avgTimeTotal, fairnessFlag });

        Set<Integer> keyid = sheetData.keySet();
        for (int key: keyid){
            row = categorySheet.createRow(rowId++);
            Object[] objectArr = sheetData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                if (obj instanceof Double){
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Boolean){
                    cell.setCellValue((boolean) obj);
                } else if(obj instanceof Integer){
                    cell.setCellValue((Integer)obj);
                } else {
                    cell.setCellValue((String) obj);
                }
            }
        }

        FileOutputStream out = new FileOutputStream(filePath.replace("?", categoryName.replace(" ", "_")));

        wb.write(out);
        out.close();
    }

}
