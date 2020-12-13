package mtni.its.dashboard.service.reportUtils;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public interface ReportUtilities {
    Map<String,String> extractReportInfo(File file);
    void loadFromTempFiles() throws IOException;
}
