package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.stats.DailyStats;
import mtni.its.dashboard.service.Stats.Stats;
import mtni.its.dashboard.service.reportUtils.ReportUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

    private static Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private Stats stats;

    @Autowired
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @PostMapping(value = "/stats/daily")
    @ResponseBody
    public DailyStats getDailyStats(@RequestBody LocalDate date) {
        logger.info("Request for daily Stats: "+date);
        return stats.getDailyStatsByDate(date);
    }

    @PostMapping(value = "/stats/dailyFromTo")
    @ResponseBody
    public List<DailyStats> getDailyStatsFromTo(@RequestBody LocalDate[] dates){
        try {
            logger.info("Request for daily stats from "+dates[0]+" to "+ dates[1]);
            return stats.getDailyStatsFromTo(dates[0] , dates[1]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("")
    public String dashboard(){
        return "dashboard";
    }
//
//    @GetMapping("/load")
////    public void LoadFile(@RequestBody Multi file) throws IOException {
//    public void LoadFile() throws IOException {
////        Map map = reportUtilities.extractReportInfo(file);
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
////        if(map.containsKey("EDW_ABL_SHWG_DIF_")){
////            serviceEASHD.saveAll(csvReader.read_EASHD_File(file , LocalDate.parse((CharSequence) map.get("EDW_ABL_SHWG_DIF_"),formatter)));
//        reportUtilities.loadFromTempFiles();
//        }
//    }
}