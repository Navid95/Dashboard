package mtni.its.dashboard.service.reportUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;


public interface ReportUtilities {
//    Map<String,String> extractReportInfo(File file);
//    List<Path> checkPathForTodayReports();
    void getTodayReports();
}
