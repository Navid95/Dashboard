package mtni.its.dashboard.service.reportUtils;

import mtni.its.dashboard.service.RepoImpl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class ReportUtilityImpl implements ReportUtilities {

    private static Logger logger = LoggerFactory.getLogger(ReportUtilityImpl.class);

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

    @Override
    public Map<String, String> extractReportInfo(File file) {
        String fileFullName = file.getName();
        String reportName="";
        String reportDate ="";
        Pattern pattern = Pattern.compile("^(EDW_ABL_SHWG_DIF_|EDW_NO_MSISDN_ABLT_|EDW_No_RE_ABL_|EDW_No_RE_ER_|EDW_No_RE_SHWG_)" +
                "20([2-9][0-9])((01|03|05|07|08|10|12)(0?[1-9]|[1-2][0-9]|3[0-1])|((04|06|09|11)(0?[1-9]|[1-2][0-9]|30))|(02)(0?[1-9]|[1-2][0-9])).*.csv");
        Matcher matcher = pattern.matcher(fileFullName);
        if (matcher.find()){
            if (fileFullName.startsWith("EDW_ABL_SHWG_DIF_")){
                reportName = "EDW_ABL_SHWG_DIF_";
            }else if (fileFullName.startsWith("EDW_NO_MSISDN_ABLT_")){
                reportName = "EDW_NO_MSISDN_ABLT_";
            } else if (fileFullName.startsWith("EDW_No_RE_ABL_")){
                reportName = "EDW_No_RE_ABL_";
            } else if (fileFullName.startsWith("EDW_No_RE_ER_")){
                reportName = "EDW_No_RE_ER_";
            } else if (fileFullName.startsWith("EDW_No_RE_SHWG_")){
                reportName = "EDW_No_RE_SHWG_";
            }
            reportDate = fileFullName.split("_")[4];
            Map map = new HashMap();
            map.put(reportName ,reportDate);
            return map;
        }
        return null;
    }

    @Override
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
}
