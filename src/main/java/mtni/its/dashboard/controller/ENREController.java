package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.ENRE;
import mtni.its.dashboard.service.RepoImpl.ServiceENRE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/enre")
public class ENREController {

    private static Logger logger = LoggerFactory.getLogger(ENREController.class);
    private ServiceENRE serviceENRE;

    @Autowired
    public void setServiceENRE(ServiceENRE serviceENRE) {
        this.serviceENRE = serviceENRE;
    }

    @PostMapping(value = "/find/allbydate")
    public List<ENRE> findAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceENRE.findAllByReportDate(reportDate);
    }

    @PostMapping(value = "/find/allbetween")
    public List<ENRE> findAllBetween(@RequestBody List<LocalDate> dates){
        return serviceENRE.findAllBetween(dates.get(0) , dates.get(1));
    }

    @PostMapping(value = "/count/allbydate")
    public int countAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceENRE.countAllByReportDate(reportDate);
    }

    @PostMapping(value = "/count/allbetween")
    public int countAllBetween(@RequestBody List<LocalDate> dates){
        return serviceENRE.countAllBetween(dates.get(0) , dates.get(1));
    }

}
