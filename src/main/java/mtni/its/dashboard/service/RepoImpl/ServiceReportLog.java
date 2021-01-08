package mtni.its.dashboard.service.RepoImpl;

import mtni.its.dashboard.domain.ReportLog;
import mtni.its.dashboard.repository.ReportLogRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ServiceReportLog {

    private static Logger logger = LoggerFactory.getLogger(ServiceReportLog.class);
    private ReportLogRepo repo;
    private final int batchSize = 1000;

    @Autowired
    public void setRepo(ReportLogRepo repo) {
        this.repo = repo;
    }

    public void saveAll(List<ReportLog> list){
        logger.info("Got List of size: "+list.size());
        int i;
        for (i=0;i<list.size()/batchSize;i++){
            logger.info("Persisting from index: "+(i*batchSize) +" till index: "+((i+1)*batchSize));
            repo.saveAll(list.subList((i*batchSize), ((i+1)*batchSize)));
        }
        logger.info("Persisting the last manual batch from index: "+((i)*batchSize) +" till index: "+(list.size()));
        repo.saveAll(list.subList((i)*batchSize,list.size()));
    }

    public boolean existsByReportDate(LocalDate reportDate){
        boolean exists = repo.existsByReportDate(reportDate);
        logger.info("Reports log status for date("+reportDate.format(DateTimeFormatter.ISO_LOCAL_DATE)+") is: "+ exists);
        return exists;
    }

    public void save(ReportLog reportLog){
        repo.save(reportLog);
    }
}
