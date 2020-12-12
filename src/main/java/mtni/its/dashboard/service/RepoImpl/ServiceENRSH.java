package mtni.its.dashboard.service.RepoImpl;

import mtni.its.dashboard.domain.ENRSH;
import mtni.its.dashboard.repository.ENRSHRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceENRSH {

    private static Logger logger = LoggerFactory.getLogger(ServiceENRSH.class);
    private final int batchSize = 1000;
    private ENRSHRepo repo;

    @Autowired
    public void setRepo(ENRSHRepo repo) {
        this.repo = repo;
    }

    public void saveAll(List<ENRSH> list){
        logger.info("Got List of size: "+list.size());
        int i;
        for (i=0;i<list.size()/batchSize;i++){
            logger.info("Persisting from index: "+(i*batchSize) +" till index: "+((i+1)*batchSize));
            repo.saveAll(list.subList((i*batchSize), ((i+1)*batchSize)));
        }
        logger.info("Persisting the last manual batch from index: "+((i)*batchSize) +" till index: "+(list.size()));
        repo.saveAll(list.subList((i)*batchSize,list.size()));
    }

    public List<ENRSH> findAllByReportDate(LocalDate upLoadDate){
        return repo.findAllByReportDateEquals(upLoadDate);
    }
    /**
     * includes start and end date
     * @param start
     * @param end
     * @return
     */
    public List<ENRSH> findAllBetween(LocalDate start , LocalDate end) {
        return repo.findAllByReportDateIsBetween(start, end);
    }

    public int countAllByReportDate(LocalDate uploadDate){
        return repo.countAllByReportDateEquals(uploadDate);
    }

    public int countAllBetween(LocalDate start , LocalDate end){
        return repo.countAllByReportDateBetween(start , end);
    }
}
