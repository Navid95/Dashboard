package mtni.its.dashboard.UnitTests;

import mtni.its.dashboard.domain.ReportLog;
import mtni.its.dashboard.service.reportUtils.ReportUtilityImpl;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.config.BootstrapMode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ReportUtilityImpl_Test {

    @Autowired
    private ReportUtilityImpl utility;

    private List<String> reportsName = new ArrayList<>(Arrays.asList("EDW_ABL_SHWG_DIF_","EDW_NO_MSISDN_ABLT_","EDW_No_RE_ABL_","EDW_No_RE_ER_","EDW_No_RE_SHWG_"));
//    private static List<String> reportsName = new ArrayList<>(Arrays.asList("EDW_ABL_SHWG_DIF_","EDW_NO_MSISDN_ABLT_","EDW_No_RE_ABL_","EDW_No_RE_ER_","EDW_No_RE_SHWG_"));
//
    private Logger logger = LoggerFactory.getLogger(ReportUtilityImpl_Test.class);
//
    @Value("${reportDirectoryPath}")
    private String reportPath;
//// ****************************************************************************************
//    @Test
//    public void getFileName(){}
//// ****************************************************************************************
//    @Test
//    public void checkPathForTodayReports_Test() {
//        logger.warn("Report Directory is: "+ reportPath);
//        List<File> files = new ArrayList<>(5);
//        List<Path> filesPath = new ArrayList<>();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//            try {
//                files.get(i).createNewFile();
//                filesPath.add(files.get(i).toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Assertions.assertEquals(filesPath,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//            if (i !=0) {
//                try {
//                    files.get(i).createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Assertions.assertEquals(null,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//            if (i !=1) {
//                try {
//                    files.get(i).createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Assertions.assertEquals(null,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//            if (i !=2) {
//                try {
//                    files.get(i).createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Assertions.assertEquals(null,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//            if (i !=3) {
//                try {
//                    files.get(i).createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Assertions.assertEquals(null,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//            if (i !=4) {
//                try {
//                    files.get(i).createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        Assertions.assertEquals(null,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//        for (int i=0; i<5;i++){
//            files.add(i,new File(reportPath+reportsName.get(i)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_..csv"));
//                try {
//                    files.get(i).createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
//        files.add(5 ,new File(reportPath+reportsName.get(4)+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_.558899.csv"));
//        try {
//            files.get(5).createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Assertions.assertEquals(null,utility.checkPathForTodayReports());
//        removeFiles(files);
//        files.clear();
//    }
//// ****************************************************************************************
//    public void removeFiles(List<File> files) {
//        for (File file : files) {
//            try {
//                Files.delete(Paths.get(file.getPath()));
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
//    }
//
//// ****************************************************************************************
//    @Test
//    public void getTodayReports_Test() throws InterruptedException {
//        utility.getTodayReports();
//        Assertions.assertTrue(true);
//    }
// ****************************************************************************************
//    @Test
//    public void checkReportLog_Test(){
//        Assertions.assertFalse(utility.checkReportLog());
//    }
// ****************************************************************************************
// ****************************************************************************************
// ****************************************************************************************
}