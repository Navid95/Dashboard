package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.stats.DailyStats;
import mtni.its.dashboard.service.Stats.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

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
        logger.error("provided date: "+date);
        logger.warn("provided date: "+date);
        logger.info("provided date: "+date);
        return stats.getDailyStatsByDate(date);
    }

    @GetMapping
    public String dashboard(Model model){
        return "test";
    }

    @GetMapping(value = "/date")
    @ResponseBody
    public LocalDate getDate(){
        logger.warn("date");
        logger.warn("date");
        logger.warn("date");
        return LocalDate.now();
    }
}