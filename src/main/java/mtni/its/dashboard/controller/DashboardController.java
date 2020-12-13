package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.stats.DailyStats;
import mtni.its.dashboard.service.RepoImpl.*;
import mtni.its.dashboard.service.Stats.Stats;
import mtni.its.dashboard.service.reportUtils.CSVReader;
import mtni.its.dashboard.service.reportUtils.ReportUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

    private static Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private Stats stats;
    private ReportUtilities reportUtilities;

//    @Autowired
//    private ServiceEASHD serviceEASHD;
//
//    @Autowired
//    private ServiceENMA serviceENMA;
//
//    @Autowired
//    private ServiceENRA serviceENRA;
//
//    @Autowired
//    private ServiceENRE serviceENRE;
//
//    @Autowired
//    private ServiceENRSH serviceENRSH;
//
//    @Autowired
//    private CSVReader csvReader;

    @Autowired
    public void setReportUtilities(ReportUtilities reportUtilities) {
        this.reportUtilities = reportUtilities;
    }

    @Autowired
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @PostMapping(value = "/stats/daily")
    @ResponseBody
    public DailyStats getDailyStats(@RequestBody LocalDate date) {
        logger.error("provided date: "+date);
        logger.warn("provided date: "+date);
        logger.info("provided date: "+date);
        return stats.getDailyStatsByDate(date);
    }

    @GetMapping("/load")
    @ResponseBody
    public String LoadFile() throws IOException {
        reportUtilities.loadFromTempFiles();
        return "true";
    }
}