package mtni.its.dashboard.service.Stats;

import mtni.its.dashboard.domain.stats.DailyStats;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface Stats {
    DailyStats getDailyStatsByDate(LocalDate date);
}
