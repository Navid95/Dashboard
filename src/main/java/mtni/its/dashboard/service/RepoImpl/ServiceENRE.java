package mtni.its.dashboard.service.RepoImpl;

import mtni.its.dashboard.domain.ENRE;
import mtni.its.dashboard.repository.ENRERepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceENRE {

    private static Logger logger = LoggerFactory.getLogger(ServiceENRE.class);
    private ENRERepo repo;
    private final int batchSize = 1000;

    @Autowired
    public void setRepo(ENRERepo repo) {
        this.repo = repo;
    }

    public void saveAll(List<ENRE> list){
        logger.info("Got List of size: "+list.size());
        int i;
        for (i=0;i<list.size()/batchSize;i++){
            logger.info("Persisting from index: "+(i*batchSize) +" till index: "+((i+1)*batchSize));
            repo.saveAll(list.subList((i*batchSize), ((i+1)*batchSize)));
        }
        logger.info("Persisting the last manual batch from index: "+((i)*batchSize) +" till index: "+(list.size()));
        repo.saveAll(list.subList((i)*batchSize,list.size()));
    }

    public List<ENRE> findAllByReportDate(LocalDate reportDate){
        return repo.findAllByReportDateEquals(reportDate);
    }
    public List<ENRE> findAllBetween(LocalDate start , LocalDate end){
        return repo.findAllByReportDateIsBetween(start,end);
    }
    public int countAllByReportDate(LocalDate reportDate){
        return repo.countAllByReportDateEquals(reportDate);
    }
    public int countAllBetween(LocalDate start , LocalDate end){
        return repo.countAllByReportDateBetween(start,end);
    }
}
