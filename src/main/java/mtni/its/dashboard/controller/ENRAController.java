package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.ENRA;
import mtni.its.dashboard.domain.ENRE;
import mtni.its.dashboard.service.RepoImpl.ServiceENRA;
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
@RequestMapping(value = "/api/enra")
public class ENRAController {
    private static Logger logger = LoggerFactory.getLogger(ENRAController.class);
    private ServiceENRA serviceENRA;

    @Autowired
    public void setServiceENRE(ServiceENRA serviceENRA) {
        this.serviceENRA = serviceENRA;
    }

    @PostMapping(value = "/find/allbydate")
    public List<ENRA> findAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceENRA.findAllByReportDate(reportDate);
    }

    @PostMapping(value = "/find/allbetween")
    public List<ENRA> findAllBetween(@RequestBody List<LocalDate> dates){
        return serviceENRA.findAllBetween(dates.get(0) , dates.get(1));
    }

    @PostMapping(value = "/count/allbydate")
    public int countAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceENRA.countAllByReportDate(reportDate);
    }

    @PostMapping(value = "/count/allbetween")
    public int countAllBetween(@RequestBody List<LocalDate> dates){
        return serviceENRA.countAllBetween(dates.get(0) , dates.get(1));
    }

}
