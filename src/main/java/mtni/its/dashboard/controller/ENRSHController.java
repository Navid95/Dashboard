package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.ENRSH;
import mtni.its.dashboard.service.RepoImpl.ServiceENRSH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/api/enrsh")
public class ENRSHController {

    private static Logger logger = LoggerFactory.getLogger(ENRSHController.class);
    private ServiceENRSH enrshRepo;

    @Autowired
    public void setEnrshRepo(ServiceENRSH enrshRepo) {
        this.enrshRepo = enrshRepo;
    }

    //  LocalDate and Date passed in @RequestBody should be in simple format String: "yyyy-MM-dd"
    @PostMapping(value = "/find/allbydate")
    public List<ENRSH> findAllByReportDate(@RequestBody LocalDate uploadDate){
        return enrshRepo.findAllByReportDate(uploadDate);
    }

    //  LocalDate and Date passed in @RequestBody should be in simple format String: "yyyy-MM-dd"
    @PostMapping(value = "/count/allbydate")
    public int countAllByReportDate(@RequestBody LocalDate uploadDate){
        return enrshRepo.countAllByReportDate(uploadDate);
    }

    //  LocalDate passed in @RequestBody should be in
    @PostMapping(value = "/find/allbetween")
    public List<ENRSH> findAllBetween(@RequestBody List<LocalDate> dates){
        return enrshRepo.findAllBetween(dates.get(0) , dates.get(1));
    }

    //  LocalDate and Date passed in @RequestBody should be in simple format String: "yyyy-MM-dd"
    @PostMapping(value = "/count/allbetween")
    public int countAllBetween(@RequestBody List<LocalDate> dates){
        return enrshRepo.countAllBetween(dates.get(0) , dates.get(1));
    }
}
