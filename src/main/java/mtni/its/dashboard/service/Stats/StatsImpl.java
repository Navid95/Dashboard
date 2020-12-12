package mtni.its.dashboard.service.Stats;

import mtni.its.dashboard.domain.stats.DailyStats;
import mtni.its.dashboard.service.RepoImpl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatsImpl implements Stats {

    private static Logger logger = LoggerFactory.getLogger(StatsImpl.class);
    private ServiceENRSH enrsh;
    private ServiceEASHD eashd;
    private ServiceENMA enma;
    private ServiceENRA enra;
    private ServiceENRE enre;

    @Autowired
    public void setEashd(ServiceEASHD eashd) {
        this.eashd = eashd;
    }

    @Autowired
    public void setEnma(ServiceENMA enma) {
        this.enma = enma;
    }

    @Autowired
    public void setEnra(ServiceENRA enra) {
        this.enra = enra;
    }

    @Autowired
    public void setEnre(ServiceENRE enre) {
        this.enre = enre;
    }

    @Autowired
    public void setEnrsh(ServiceENRSH enrsh) {
        this.enrsh = enrsh;
    }

    @Override
    public DailyStats getDailyStatsByDate(LocalDate date) {
        DailyStats dailyStats = new DailyStats();
        dailyStats.setCount_EDW_ABL_SHWG_DIF(eashd.countAllByReportDate(date));
        dailyStats.setCount_EDW_No_RE_ABL(enra.countAllByReportDate(date));
        dailyStats.setCount_EDW_No_RE_SHWG(enrsh.countAllByReportDate(date));
        dailyStats.setCount_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER(enma.countAllByReportDate(date) + enre.countAllByReportDate(date));
        dailyStats.setStatDate(date);
        return dailyStats;
    }
}
