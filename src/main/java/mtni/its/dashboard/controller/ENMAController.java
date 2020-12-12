package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.ENMA;
import mtni.its.dashboard.domain.ENRA;
import mtni.its.dashboard.service.RepoImpl.ServiceENMA;
import mtni.its.dashboard.service.RepoImpl.ServiceENRA;
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
@RequestMapping(value = "/api/enma")
public class ENMAController {
    private static Logger logger = LoggerFactory.getLogger(ENMAController.class);
    private ServiceENMA serviceENMA;

    @Autowired
    public void setServiceENMA(ServiceENMA serviceENMA) {
        this.serviceENMA = serviceENMA;
    }

    @PostMapping(value = "/find/allbydate")
    public List<ENMA> findAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceENMA.findAllByReportDate(reportDate);
    }

    @PostMapping(value = "/find/allbetween")
    public List<ENMA> findAllBetween(@RequestBody List<LocalDate> dates){
        return serviceENMA.findAllBetween(dates.get(0) , dates.get(1));
    }

    @PostMapping(value = "/count/allbydate")
    public int countAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceENMA.countAllByReportDate(reportDate);
    }

    @PostMapping(value = "/count/allbetween")
    public int countAllBetween(@RequestBody List<LocalDate> dates){
        return serviceENMA.countAllBetween(dates.get(0) , dates.get(1));
    }

}
