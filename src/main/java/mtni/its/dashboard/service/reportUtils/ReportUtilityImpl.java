package mtni.its.dashboard.service.reportUtils;

import mtni.its.dashboard.domain.ReportLog;
import mtni.its.dashboard.service.RepoImpl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReportUtilityImpl implements ReportUtilities {

    private static  Logger logger = LoggerFactory.getLogger(ReportUtilityImpl.class);

    private static List<String> reportsName = new ArrayList<>(Arrays.asList("EDW_ABL_SHWG_DIF_","EDW_NO_MSISDN_ABLT_","EDW_No_RE_ABL_","EDW_No_RE_ER_","EDW_No_RE_SHWG_"));

    @Value("${reportDirectoryPath}")
    private String reportDirectoryPath ;

    @Autowired
    private ServiceEASHD serviceEASHD;

    @Autowired
    private ServiceENMA serviceENMA;

    @Autowired
    private ServiceENRA serviceENRA;

    @Autowired
    private ServiceENRE serviceENRE;

    @Autowired
    private ServiceENRSH serviceENRSH;

    @Autowired
    private CSVReader csvReader;

    @Autowired
    private ServiceReportLog serviceReportLog;

// ****************************************************************************************

    public Map<String, String> extractReportInfo(File file) {
        String fileFullName = file.getName();
        String reportName="";
        String reportDate ="";
        Pattern pattern = Pattern.compile("^("+reportsName.get(0)+"|"+reportsName.get(1)+"|"+reportsName.get(2)+"|"+reportsName.get(3)+"|"+reportsName.get(4)+")" +
                "20([2-9][0-9])((01|03|05|07|08|10|12)(0?[1-9]|[1-2][0-9]|3[0-1])|((04|06|09|11)(0?[1-9]|[1-2][0-9]|30))|(02)(0?[1-9]|[1-2][0-9])).*.csv");
        Matcher matcher = pattern.matcher(fileFullName);
        if (matcher.find()){
            if (fileFullName.startsWith(reportsName.get(0))){
                reportName = reportsName.get(0);
            }else if (fileFullName.startsWith(reportsName.get(1))){
                reportName = reportsName.get(1);
            } else if (fileFullName.startsWith(reportsName.get(2))){
                reportName = reportsName.get(2);
            } else if (fileFullName.startsWith(reportsName.get(3))){
                reportName = reportsName.get(3);
            } else if (fileFullName.startsWith(reportsName.get(4))){
                reportName = reportsName.get(4);
            }
            reportDate = fileFullName.split("_")[4];
            logger.info("Report date is: "+ reportDate);
            Map map = new HashMap();
            map.put(reportName ,reportDate);
            return map;
        }
        return null;
    }

// ****************************************************************************************

    public void loadFromTempFiles() throws IOException {
//        Resource resource = new ClassPathResource("tempFiles/EDW_ABL_SHWG_DIF_20201204_10_46_25.csv");
//        logger.error(((ClassPathResource) resource).getPath());
        File file = new File("/home/navid/shakar");
       if (file.isDirectory()){
           logger.error("YYYYYYYEEEEEEEEESSSSSSS");
           for (File path : file.listFiles()){
//               logger.error(path.getName());
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
               Map map = new HashMap();
               map = extractReportInfo(path);
//               logger.error(map.keySet().toString());
               if (map.containsKey(String.valueOf("EDW_ABL_SHWG_DIF_"))){
                   serviceEASHD.saveAll(csvReader.read_EASHD_File(path , LocalDate.parse(map.get("EDW_ABL_SHWG_DIF_").toString() , formatter)));
               } else if (map.containsKey(String.valueOf("EDW_NO_MSISDN_ABLT_"))){
                   serviceENMA.saveAll(csvReader.read_ENMA_File(path , LocalDate.parse(map.get("EDW_NO_MSISDN_ABLT_").toString() , formatter)));
               } else if (map.containsKey(String.valueOf("EDW_No_RE_ABL_"))){
                   serviceENRA.saveAll(csvReader.read_ENRA_File(path , LocalDate.parse(map.get("EDW_No_RE_ABL_").toString() , formatter)));
               } else if (map.containsKey(String.valueOf("EDW_No_RE_ER_"))){
                   serviceENRE.saveAll(csvReader.read_ENRE_File(path , LocalDate.parse(map.get("EDW_No_RE_ER_").toString() , formatter)));
               }else if (map.containsKey(String.valueOf("EDW_No_RE_SHWG_"))){
                   serviceENRSH.saveAll(csvReader.read_ENRSH_File(path , LocalDate.parse(map.get("EDW_No_RE_SHWG_").toString() , formatter)));
               }
           }
       }else {
           logger.error(String.valueOf(file.isFile()));
           logger.error("FFUUUUUUUUUUCK");
       }
    }

// ****************************************************************************************
    public List<Path> checkPathForTodayReports() {
        logger.info("Reports directory path is: " + reportDirectoryPath);
        LocalDate today = LocalDate.now();
        File reportDir = new File(reportDirectoryPath);
        List<Boolean> reportList = new ArrayList<>(Arrays.asList(false, false, false, false, false));
        List<Path> foundReportFiles = new ArrayList<>(Arrays.asList(Paths.get(""), Paths.get(""), Paths.get(""), Paths.get(""), Paths.get("")));
        List<File> files = Arrays.asList(reportDir.listFiles());
//        Sort based on file last modified time
        files.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Long.valueOf(o1.lastModified()).compareTo(o2.lastModified());
            }
        });
        int reportsFound = 0;
        logger.info("Searching for reports on: " + reportDir.getPath());
        for (File report : files) {
                reportsFound = 0;
                for (Boolean availableReport : reportList)
                    if (availableReport == true)
                        reportsFound++;
                if (reportsFound < reportsName.size()) {
                    logger.info("Found reports' count on path (" + reportDir + ") is: " + reportsFound + ". Searching through directory for more reports...");
                    Map reportInfo = extractReportInfo(report);
                    logger.info("ReportInfo: " + reportInfo);
                    if (reportInfo == null) {
                        logger.info("File '" + report.getName() + "' is not a report.");
                        continue;
                    } else {
                        for (String reportName : reportsName) {
                            logger.info("reportInfo.containsKey(String.valueOf(reportName): " +
                                    reportInfo.containsKey(String.valueOf(reportName)) +
                                    " reportInfo.containsValue(today.format(DateTimeFormatter.BASIC_ISO_DATE)): " +
                                    reportInfo.containsValue(today.format(DateTimeFormatter.BASIC_ISO_DATE)));
                            if (reportInfo.containsKey(String.valueOf(reportName)) && reportInfo.containsValue(today.format(DateTimeFormatter.BASIC_ISO_DATE))) {
                                logger.info("Found report '" + report.getName() + "'. Changing report checklist index " + reportsName.indexOf(reportName) + " to True.");
                                if (!reportList.get(reportsName.indexOf(reportName))) {
                                    reportList.set(reportsName.indexOf(reportName), true);
                                    foundReportFiles.set(reportsName.indexOf(reportName), report.toPath());
                                } else {
//                                     TODO usually never reaches here
                                    logger.warn("Found duplicated file for report '" + report.getName() + "'.");
                                    return null;
                                }
                                break;
                            } else {
                                logger.info("File " + report.getName() + " is not of report type " + reportName + ".");
                            }
                        }
                    }
                } else {
                    logger.info("Found all reports on path " + reportDir + ".");
                    return foundReportFiles;
                }
            }
            reportsFound = 0;
            for (Boolean availableReport : reportList)
                if (availableReport == true)
                    reportsFound++;
            if (reportsFound == 5) {
                logger.info("Found all reports for today. " + foundReportFiles);
                return foundReportFiles;
            } else {
                logger.warn("Could not find reports for today. " + foundReportFiles);
                return null;
            }
    }
// ****************************************************************************************
    @Override
    @Scheduled(cron = "${cron.expression}")
    public void getTodayReports() {
        if (!checkReportLog()){
            List<Path> reports = checkReportFilesAvailability();
            if (reports != null) {
                if (persistReportFiles(reports)) {
                    updateReportLog();
                } else {
                    logger.error("Failed to persist reports");
                }
            } else {
                logger.warn("Today's reports are not still available on path or there are multiple files per report");
            }
        } else {
            logger.info("Today's ("+LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)+") reports have already persisted to DB");
        }
    }
// ****************************************************************************************
    public boolean checkReportLog(){
        return serviceReportLog.existsByReportDate(LocalDate.now());
    }
// ****************************************************************************************
    private List<Path> checkReportFilesAvailability(){
        return checkPathForTodayReports();
    }
// ****************************************************************************************
    private boolean persistReportFiles(List<Path> reports){
        logger.info("Persisting report list: "+reports);
        for (Path path : reports){
            Map<String, String> reportInfo = extractReportInfo(path.toFile());
            logger.info("Got report info for file "+path+": "+reportInfo);
            try {
                logger.info("Persisting report file: "+reportInfo);
                persistReport(reportInfo,path);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
// ****************************************************************************************
    private void updateReportLog(){
        serviceReportLog.save(new ReportLog(LocalDate.now()));
    }
// ****************************************************************************************
    private void persistReport(Map<String,String> map , Path path) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        if (map.containsKey(String.valueOf(reportsName.get(0)))){
            serviceEASHD.saveAll(csvReader.read_EASHD_File(path.toFile() , LocalDate.parse(map.get(reportsName.get(0)).toString() , formatter)));
        } else if (map.containsKey(String.valueOf(reportsName.get(1)))){
            serviceENMA.saveAll(csvReader.read_ENMA_File(path.toFile() , LocalDate.parse(map.get(reportsName.get(1)).toString() , formatter)));
        } else if (map.containsKey(String.valueOf(reportsName.get(2)))){
            serviceENRA.saveAll(csvReader.read_ENRA_File(path.toFile() , LocalDate.parse(map.get(reportsName.get(2)).toString() , formatter)));
        } else if (map.containsKey(String.valueOf(reportsName.get(3)))){
            serviceENRE.saveAll(csvReader.read_ENRE_File(path.toFile() , LocalDate.parse(map.get(reportsName.get(3)).toString() , formatter)));
        }else if (map.containsKey(String.valueOf(reportsName.get(4)))){
            serviceENRSH.saveAll(csvReader.read_ENRSH_File(path.toFile() , LocalDate.parse(map.get(reportsName.get(4)).toString() , formatter)));
        } else {
            logger.error("Report file "+path+" was not detected as any of the report types: "+path+" - info: "+map);
        }
    }
// ****************************************************************************************
}
