package mtni.its.dashboard.UnitTests;

import mtni.its.dashboard.service.reportUtils.ReportUtilities;
import mtni.its.dashboard.service.reportUtils.ReportUtilityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class ReportUtilityImpl_Test {

    @Autowired
    private ReportUtilities utility;

    @Test
    public void getFileName(){
        File file = new File("static/tempFiles/EDW_ABL_SHWG_DIF_20201208_11_16_22.csv");
        Map map = new HashMap();
        map.put("EDW_ABL_SHWG_DIF_" ,"20201208");
        Assertions.assertEquals(map,utility.extractReportInfo(file));
    }
}
