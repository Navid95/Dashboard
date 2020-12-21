package mtni.its.dashboard.UnitTests;

import mtni.its.dashboard.domain.stats.DailyStats;
import mtni.its.dashboard.service.Stats.Stats;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class StatsImpl_Test {

    @Autowired
    private Stats stats;
    private static Logger logger = LoggerFactory.getLogger(StatsImpl_Test.class);

    @Test
    public void getDailyStatsFromTo() throws Exception {
//        LocalDate from ,to;
//        from = LocalDate.parse("2020-12-07");
//        to = LocalDate.parse("2020-12-10");
//        List<DailyStats> dailyStatsList = stats.getDailyStatsFromTo(from , to);
//        dailyStatsList.forEach(stat ->{
//            logger.info(stat.toString());
//        });
//        Assertions.assertEquals(4 , dailyStatsList.size());
//        Assertions.assertThrows(Exception.class , (Executable) stats.getDailyStatsFromTo(to,from));
    }
}
