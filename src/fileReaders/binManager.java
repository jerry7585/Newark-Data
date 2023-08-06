package fileReaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Math;

public class binManager extends xlsxManager{
    private Map<Double, Double> censusIncome;
    private final String NOT_HISPANIC_WHITE_STRING = "Not Hispanic or Latino: - White alone";
    private final String NOT_HISPANIC_BLACK_STRING= "Not Hispanic or Latino: - Black or African American alone";
    private final String NOT_HISPANIC_AMERICAN_INDIAN_STRING = "Not Hispanic or Latino: - American Indian and Alaska Native alone";
    private final String NOT_HISPANIC_ASIAN = "Not Hispanic or Latino: - Asian alone";
    private final String INCOME_STRING = "income";
    private final String RACE_STRING = "race";
    private Map<Double, Double> notHispanicWhite;
    private Map<Double, Double> notHispanicBlack;
    private Map<Double, Double> notHispanicAmericanIndian;
    private Map<Double, Double> notHispanicAsian;
    public binManager() throws IOException {
        censusIncome = parseCensusIncomeXlsx();
        notHispanicWhite = parseRaceXlsx(NOT_HISPANIC_WHITE_STRING);
        notHispanicBlack = parseRaceXlsx(NOT_HISPANIC_BLACK_STRING);
        notHispanicAmericanIndian = parseRaceXlsx(NOT_HISPANIC_AMERICAN_INDIAN_STRING);
        notHispanicAsian = parseRaceXlsx(NOT_HISPANIC_ASIAN);

    }


    public void sortAndCalculateBins(List<xlsxIssueObj> objList, String fileName, String userInput, String filePath) throws IOException {
        List<xlsxIssueObj> bin1, bin2, bin3, bin4, bin5;
        bin1 = new ArrayList<>();
        bin2 = new ArrayList<>();
        bin3 = new ArrayList<>();
        bin4 = new ArrayList<>();
        bin5 = new ArrayList<>();

        for (xlsxIssueObj obj : objList){
            //if no tract or issue isn't resolved
            if (obj.getTractNum().equals("N/A") || obj.getStatus().equals("Open")){
                continue;
            }

            double censusTract = Double.parseDouble(obj.getTractNum())/100;
            //if census tract information doesn't exit
            if (!censusIncome.containsKey(censusTract)){
                continue;
            }
            double censusTractIncome = censusIncome.get(censusTract);

            if(userInput.equals("income")){
                if (censusTractIncome >= 0 && censusTractIncome < 25000){
                    bin1.add(obj);
                } else if (censusTractIncome >= 25000 && censusTractIncome < 50000){
                    bin2.add(obj);
                } else if (censusTractIncome >= 50000 && censusTractIncome < 75000){
                    bin3.add(obj);
                } else if (censusTractIncome >= 75000 && censusTractIncome < 100000){
                    bin4.add(obj);
                } else if (censusTractIncome >= 100000){
                    bin5.add(obj);
                }
            } else if (userInput.equals("race")){
                if(obj.getRacePercent() >= 0 && obj.getRacePercent() < 0.2){
                    bin1.add(obj);
                }
                else if(obj.getRacePercent() >= 0.2 && obj.getRacePercent() < 0.4){
                    bin2.add(obj);
                }
                else if(obj.getRacePercent() >= 0.4 && obj.getRacePercent() < 0.6){
                    bin3.add(obj);
                }
                else if(obj.getRacePercent() >= 0.6 && obj.getRacePercent() < 0.8){
                    bin4.add(obj);
                }
                else if(obj.getRacePercent() >= 0.8){
                    bin5.add(obj);
                }
            }
        }

        hypothesisTest(bin1, bin2, bin3, bin4, bin5, fileName, filePath);
    }

    public void hypothesisTest(List<xlsxIssueObj> bin1,  List<xlsxIssueObj> bin2, List<xlsxIssueObj> bin3, List<xlsxIssueObj> bin4, List<xlsxIssueObj> bin5, String fileName, String filePath) throws IOException {
        double totalAverageTime = 0, bin1AverageTime = 0, bin2AverageTime = 0, bin3AverageTime = 0, bin4AverageTime = 0, bin5AverageTime = 0;
        int totalAmountObj = 0, bin1AmountObj = 0, bin2AmountObj = 0, bin3AmountObj = 0, bin4AmountObj = 0, bin5AmountObj = 0;
        double time;

        for(xlsxIssueObj obj : bin1){
            time = obj.getMinutesAcknowledged() + obj.getMinutesToClosed();
            if(time == 0){
                continue;
            }
            bin1AmountObj++;
            bin1AverageTime += time;
        }
        for(xlsxIssueObj obj : bin2) {
            time = obj.getMinutesAcknowledged() + obj.getMinutesToClosed();
            if (time == 0) {
                continue;
            }
            bin2AmountObj++;
            bin2AverageTime += time;
        }
        for(xlsxIssueObj obj : bin3){
            time = obj.getMinutesAcknowledged() + obj.getMinutesToClosed();
            if(time == 0){
                continue;
            }
            bin3AmountObj++;
            bin3AverageTime += time;
        }
        for(xlsxIssueObj obj : bin4){
            time = obj.getMinutesAcknowledged() + obj.getMinutesToClosed();
            if(time == 0){
                continue;
            }
            bin4AmountObj++;
            bin4AverageTime += time;
        }
        for(xlsxIssueObj obj : bin5){
            time = obj.getMinutesAcknowledged() + obj.getMinutesToClosed();
            if(time == 0){
                continue;
            }
            bin5AmountObj++;
            bin5AverageTime += time;
        }

        totalAmountObj = bin1AmountObj + bin2AmountObj + bin3AmountObj + bin4AmountObj + bin5AmountObj;
        totalAverageTime = bin1AverageTime + bin2AverageTime + bin3AverageTime + bin4AverageTime + bin5AverageTime;

        //PRINT THESE AVERAGE TIME VALUES TO MAKE GRAPH
        //Prevent divide by 0
        if(totalAmountObj != 0) totalAverageTime /= totalAmountObj;
        if(bin1AmountObj != 0) bin1AverageTime /= bin1AmountObj;
        if(bin2AmountObj != 0) bin2AverageTime /= bin2AmountObj;
        if(bin3AmountObj != 0) bin3AverageTime /= bin3AmountObj;
        if(bin4AmountObj != 0) bin4AverageTime /= bin4AmountObj;
        if(bin5AmountObj != 0) bin5AverageTime /= bin5AmountObj;

        List<Double> binTimes = new ArrayList<>();
        binTimes.add(bin1AverageTime);
        binTimes.add(bin2AverageTime);
        binTimes.add(bin3AverageTime);
        binTimes.add(bin4AverageTime);
        binTimes.add(bin5AverageTime);

        double binAverage = 0;
        int binsize = binTimes.size();
        for (int i = 0; i < binsize; i++) {
            binAverage += binTimes.get(i);
        }
        binAverage /= binTimes.size();

        double stdDev = 0;
        for (int i = 0; i < binTimes.size(); i++){
            stdDev += Math.pow(binTimes.get(i) - binAverage, 2);
        }
        stdDev = Math.sqrt(stdDev / binTimes.size());

        double bin1_zScore = (bin1AverageTime - ((binAverage*binsize)-bin1AverageTime)/(binsize-1))/(stdDev/Math.sqrt(binTimes.size()));
        double bin2_zScore = (bin2AverageTime - ((binAverage*binsize)-bin2AverageTime)/(binsize-1))/(stdDev/Math.sqrt(binTimes.size()));
        double bin3_zScore = (bin3AverageTime - ((binAverage*binsize)-bin3AverageTime)/(binsize-1))/(stdDev/Math.sqrt(binTimes.size()));
        double bin4_zScore = (bin4AverageTime - ((binAverage*binsize)-bin4AverageTime)/(binsize-1))/(stdDev/Math.sqrt(binTimes.size()));
        double bin5_zScore = (bin5AverageTime - ((binAverage*binsize)-bin5AverageTime)/(binsize-1))/(stdDev/Math.sqrt(binTimes.size()));

        //if Greater reject Null hypothesis and accept Alternate which means it is not fair
        //if true, then that income level is being treated fairly
        //if false, then that income level isn't being treated fairly
        boolean bin1Fair = bin1_zScore < 1.645 && bin1_zScore > -1.645;
        boolean bin2Fair = bin2_zScore < 1.645 && bin2_zScore > -1.645;
        boolean bin3Fair = bin3_zScore < 1.645 && bin3_zScore > -1.645;
        boolean bin4Fair = bin4_zScore < 1.645 && bin4_zScore > -1.645;
        boolean bin5Fair = bin5_zScore < 1.645 && bin5_zScore > -1.645;

        //if bin is empty default to true
        if(bin1AverageTime == 0) bin1Fair = true;
        if(bin2AverageTime == 0) bin2Fair = true;
        if(bin3AverageTime == 0) bin3Fair = true;
        if(bin4AverageTime == 0) bin4Fair = true;
        if(bin5AverageTime == 0) bin5Fair = true;

        //if all bins are being treated fairly then that issue is fair throughout the city
        boolean isFair = bin1Fair && bin2Fair && bin3Fair && bin4Fair && bin5Fair;

        List<Boolean> binFairness = new ArrayList<>();
        binFairness.add(bin1Fair);
        binFairness.add(bin2Fair);
        binFairness.add(bin3Fair);
        binFairness.add(bin4Fair);
        binFairness.add(bin5Fair);


//        System.out.println(fileName);
//        System.out.println("StanDev: " +  stdDev);
//        System.out.println("Denominator: " + stdDev/Math.sqrt(binTimes.size()));
//
//        System.out.println("Total Average Time: " + totalAverageTime);
//        System.out.println("Bin1 Average Time: " + bin1AverageTime);
//        System.out.println("Bin2 Average Time: " + bin2AverageTime);
//        System.out.println("Bin3 Average Time: " + bin3AverageTime);
//        System.out.println("Bin4 Average Time: " + bin4AverageTime);
//        System.out.println("Bin5 Average Time: " + bin5AverageTime);
//
//        System.out.println("Overall Fairness: " + isFair);
//        System.out.println("Bin1 Fairness: " + bin1Fair);
//        System.out.println("Bin2 Fairness: " + bin2Fair);
//        System.out.println("Bin3 Fairness: " + bin3Fair);
//        System.out.println("Bin4 Fairness: " + bin4Fair);
//        System.out.println("Bin5 Fairness: " + bin5Fair);
//
//        System.out.println("Bin1 zScore: " + bin1_zScore);
//        System.out.println("Bin2 zScore: " + bin2_zScore);
//        System.out.println("Bin3 zScore: " + bin3_zScore);
//        System.out.println("Bin4 zScore: " + bin4_zScore);
//        System.out.println("Bin5 zScore: " + bin5_zScore);

        //System.out.println();

        parseBinFairnessIntoXlsx(fileName, binTimes, binFairness, totalAverageTime, isFair, filePath);
    }


    public List<xlsxIssueObj> changeRacePercent( List<xlsxIssueObj> objList, String raceType ){
        for (xlsxIssueObj obj: objList){
            if (obj.getTractNum().equals("N/A")) continue;
            double censusTract = Double.parseDouble(obj.getTractNum())/100;
            double racePercent = 0;
            switch (raceType){
                case NOT_HISPANIC_ASIAN:
                    if (!notHispanicAsian.containsKey(censusTract)) continue;
                    racePercent = notHispanicAsian.get(censusTract);break;
                case NOT_HISPANIC_WHITE_STRING :
                    if (!notHispanicWhite.containsKey(censusTract)) continue;
                    racePercent = notHispanicWhite.get(censusTract);break;
                case NOT_HISPANIC_AMERICAN_INDIAN_STRING :
                    if (!notHispanicAmericanIndian.containsKey(censusTract)) continue;
                    racePercent = notHispanicAmericanIndian.get(censusTract);break;
                case NOT_HISPANIC_BLACK_STRING:
                    if (!notHispanicBlack.containsKey(censusTract)) continue;
                    racePercent = notHispanicBlack.get(censusTract);break;
                default:break;
            }

            obj.setRacePercent(racePercent);
        }
        return objList;
    }

    public void parseRacePercentIntoXlsx(List<xlsxIssueObj> objList, String fileName) throws IOException {
        objList = changeRacePercent(objList, NOT_HISPANIC_ASIAN);
        writeSpecificXlsxList(objList, fileName, getCategorizedAsianPercentPath());
        objList = changeRacePercent(objList, NOT_HISPANIC_AMERICAN_INDIAN_STRING);
        writeSpecificXlsxList(objList, fileName, getCategorizedAmericanIndianPath());
        objList = changeRacePercent(objList, NOT_HISPANIC_WHITE_STRING);
        writeSpecificXlsxList(objList, fileName, getCategorizedWhitePercentPath());
        objList = changeRacePercent(objList, NOT_HISPANIC_BLACK_STRING);
        writeSpecificXlsxList(objList, fileName, getCategorizedBlackPercentPath());
    }

    public void parseRacePercentIntoBins(List<xlsxIssueObj> objList, String fileName) throws IOException {
        objList = changeRacePercent(objList, NOT_HISPANIC_ASIAN);
        sortAndCalculateBins(objList, fileName, RACE_STRING, getCategorizedAsianFairnessPath());
        objList = changeRacePercent(objList, NOT_HISPANIC_AMERICAN_INDIAN_STRING);
        sortAndCalculateBins(objList, fileName, RACE_STRING, getCategorizedAmericanIndianFairnessPath());
        objList = changeRacePercent(objList, NOT_HISPANIC_WHITE_STRING);
        sortAndCalculateBins(objList, fileName, RACE_STRING, getCategorizedWhiteFairnessPath());
        objList = changeRacePercent(objList, NOT_HISPANIC_BLACK_STRING);
        sortAndCalculateBins(objList, fileName, RACE_STRING, getCategorizedBlackFairnessPath());
    }

    public void parseXlsxIntoBins() throws IOException {
        List<xlsxIssueObj> abandonedPropertyList = parseSpecificXlsx(getAbandonedPropertyPath());
        parseRacePercentIntoXlsx(abandonedPropertyList, "abandonedProperty");
        parseRacePercentIntoBins(abandonedPropertyList, "abandonedProperty");
        sortAndCalculateBins(abandonedPropertyList, "abandonedProperty", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> animalComplaintList = parseSpecificXlsx(getAnimalComplaintsPath());
        parseRacePercentIntoXlsx(animalComplaintList, "animalComplaint");
        parseRacePercentIntoBins(animalComplaintList, "animalComplaint");
        sortAndCalculateBins(animalComplaintList, "animalComplaint", INCOME_STRING,getXlsxFairnessPathName());

        List<xlsxIssueObj> businessComplaintList = parseSpecificXlsx(getBusinessComplaints());
        parseRacePercentIntoXlsx(businessComplaintList, "businessComplaints");
        parseRacePercentIntoBins(businessComplaintList, "businessComplaints");
        sortAndCalculateBins(businessComplaintList, "businessComplaints", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> damagedSidewalk = parseSpecificXlsx(getDamagedSidewalkPath());
        parseRacePercentIntoXlsx(damagedSidewalk, "damagedSidewalk");
        parseRacePercentIntoBins(damagedSidewalk, "damagedSidewalk");
        sortAndCalculateBins(damagedSidewalk, "damagedSidewalk", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> environmentalComplaint = parseSpecificXlsx(getEnvironmentalComplaintPath());
        parseRacePercentIntoXlsx(environmentalComplaint, "environmentalComplaint");
        parseRacePercentIntoBins(environmentalComplaint, "environmentalComplaint");
        sortAndCalculateBins(environmentalComplaint, "environmentalComplaint", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> fireCodeViolationList = parseSpecificXlsx(getFireCodeViolationPath());
        parseRacePercentIntoXlsx(fireCodeViolationList, "fireCodeVio");
        parseRacePercentIntoBins(fireCodeViolationList, "fireCodeVio");
        sortAndCalculateBins(fireCodeViolationList, "fireCodeVio", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> graffitiIssuesList = parseSpecificXlsx(getGraffitiIssuePath());
        parseRacePercentIntoXlsx(graffitiIssuesList, "graffiti");
        parseRacePercentIntoBins(graffitiIssuesList, "graffiti");
        sortAndCalculateBins(graffitiIssuesList, "graffiti", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> heatIssuesList = parseSpecificXlsx(getHeatIssuePath());
        parseRacePercentIntoXlsx(heatIssuesList, "heat");
        parseRacePercentIntoBins(heatIssuesList, "heat");
        sortAndCalculateBins(heatIssuesList, "heat", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> homeInspectionList = parseSpecificXlsx(getHomeInspectionPath());
        parseRacePercentIntoXlsx(homeInspectionList, "homeInspection");
        parseRacePercentIntoBins(homeInspectionList, "homeInspection");
        sortAndCalculateBins(homeInspectionList, "homeInspection", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> illegalActivityList = parseSpecificXlsx(getIllegalActivityPath());
        parseRacePercentIntoXlsx(illegalActivityList, "illegalActivity");
        parseRacePercentIntoBins(illegalActivityList, "illegalActivity");
        sortAndCalculateBins(illegalActivityList, "illegalActivity", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> illegalConstructionList = parseSpecificXlsx(getIllegalConstructionPath());
        parseRacePercentIntoXlsx(illegalConstructionList, "illegalConstruction");
        parseRacePercentIntoBins(illegalConstructionList, "illegalConstruction");
        sortAndCalculateBins(illegalConstructionList, "illegalConstruction", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> illegalDumpingList = parseSpecificXlsx(getIllegalDumpingPath());
        parseRacePercentIntoXlsx(illegalDumpingList, "illegalDumping");
        parseRacePercentIntoBins(illegalDumpingList, "illegalDumping");
        sortAndCalculateBins(illegalDumpingList, "illegalDumping", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> leadServiceIssueList = parseSpecificXlsx(getLeadServicePath());
        parseRacePercentIntoXlsx(leadServiceIssueList, "leadService");
        parseRacePercentIntoBins(leadServiceIssueList, "leadService");
        sortAndCalculateBins(leadServiceIssueList, "leadService", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> manholeIssueList = parseSpecificXlsx(getManholeIssuePath());
        parseRacePercentIntoXlsx(manholeIssueList, "manhole");
        parseRacePercentIntoBins(manholeIssueList, "manhole");
        sortAndCalculateBins(manholeIssueList, "manhole", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> missedPickupList = parseSpecificXlsx(getMissedPickupIssuePath());
        parseRacePercentIntoXlsx(missedPickupList, "missedPickup");
        parseRacePercentIntoBins(missedPickupList, "missedPickup");
        sortAndCalculateBins(missedPickupList, "missedPickup", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> noiseIssueList = parseSpecificXlsx(getNoiseIssuePath());
        parseRacePercentIntoXlsx(noiseIssueList, "noise");
        parseRacePercentIntoBins(noiseIssueList, "noise");
        sortAndCalculateBins(noiseIssueList, "noise", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> openHydrantIssueList = parseSpecificXlsx(getOpenHydrantIssuePath());
        parseRacePercentIntoXlsx(openHydrantIssueList, "openHydrant");
        parseRacePercentIntoBins(openHydrantIssueList, "openHydrant");
        sortAndCalculateBins(openHydrantIssueList, "openHydrant", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> otherIssueList = parseSpecificXlsx(getOtherIssuePath());
        parseRacePercentIntoXlsx(otherIssueList, "other");
        parseRacePercentIntoBins(otherIssueList, "other");
        sortAndCalculateBins(otherIssueList, "other", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> parkingViolationList = parseSpecificXlsx(getParkingViolationPath());
        parseRacePercentIntoXlsx(parkingViolationList, "parkingVio");
        parseRacePercentIntoBins(parkingViolationList, "parkingVio");
        sortAndCalculateBins(parkingViolationList, "parkingVio", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> parkingStructureList = parseSpecificXlsx(getParkStructurePath());
        parseRacePercentIntoXlsx(parkingStructureList, "parkStructure");
        parseRacePercentIntoBins(parkingStructureList, "parkStructure");
        sortAndCalculateBins(parkingStructureList, "parkStructure", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> potholeList = parseSpecificXlsx(getPotholeIssuesPath());
        parseRacePercentIntoXlsx(potholeList, "pothole");
        parseRacePercentIntoBins(potholeList, "pothole");
        sortAndCalculateBins(potholeList, "pothole", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> rodentInfestationList = parseSpecificXlsx(getRodentInfestationPath());
        parseRacePercentIntoXlsx(rodentInfestationList, "rodentInfestation");
        parseRacePercentIntoBins(rodentInfestationList, "rodentInfestation");
        sortAndCalculateBins(rodentInfestationList, "rodentInfestation", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> sewerIssueList = parseSpecificXlsx(getSewerIssuesPath());
        parseRacePercentIntoXlsx(sewerIssueList, "sewer");
        parseRacePercentIntoBins(sewerIssueList, "sewer");
        sortAndCalculateBins(sewerIssueList, "sewer", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> streetCleanupList = parseSpecificXlsx(getStreetCleanupPath());
        parseRacePercentIntoXlsx(streetCleanupList, "streetCleanUp");
        parseRacePercentIntoBins(streetCleanupList, "streetCleanUp");
        sortAndCalculateBins(streetCleanupList, "streetCleanUp", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> trafficIssueList = parseSpecificXlsx(getTrafficIssuePath());
        parseRacePercentIntoXlsx(trafficIssueList, "traffic");
        parseRacePercentIntoBins(trafficIssueList, "traffic");
        sortAndCalculateBins(trafficIssueList, "traffic", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> treeRemovalIssueList = parseSpecificXlsx(getTreeRemovalIssuePath());
        parseRacePercentIntoXlsx(treeRemovalIssueList, "treeRemoval");
        parseRacePercentIntoBins(treeRemovalIssueList, "treeRemoval");
        sortAndCalculateBins(treeRemovalIssueList, "treeRemoval", INCOME_STRING, getXlsxFairnessPathName());

        List<xlsxIssueObj> waterIssueList = parseSpecificXlsx(getWaterIssuesPath());
        parseRacePercentIntoXlsx(waterIssueList, "water");
        parseRacePercentIntoBins(waterIssueList, "water");
        sortAndCalculateBins(waterIssueList, "water", INCOME_STRING, getXlsxFairnessPathName());

    }

}
