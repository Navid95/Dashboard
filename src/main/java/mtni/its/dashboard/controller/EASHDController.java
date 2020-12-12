package mtni.its.dashboard.controller;

import mtni.its.dashboard.domain.EASHD;
import mtni.its.dashboard.service.RepoImpl.ServiceEASHD;
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
@RequestMapping(value = "/api/eashd")
public class EASHDController {
    private static Logger logger = LoggerFactory.getLogger(EASHDController.class);
    private ServiceEASHD serviceEASHD;

    @Autowired
    public void setServiceEASHD(ServiceEASHD serviceEASHD) {
        this.serviceEASHD = serviceEASHD;
    }

    @PostMapping(value = "/find/allbydate")
    public List<EASHD> findAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceEASHD.findAllByReportDate(reportDate);
    }

    @PostMapping(value = "/find/allbetween")
    public List<EASHD> findAllBetween(@RequestBody List<LocalDate> dates){
        return serviceEASHD.findAllBetween(dates.get(0) , dates.get(1));
    }

    @PostMapping(value = "/count/allbydate")
    public int countAllByReportDate(@RequestBody LocalDate reportDate){
        return serviceEASHD.countAllByReportDate(reportDate);
    }

    @PostMapping(value = "/count/allbetween")
    public int countAllBetween(@RequestBody List<LocalDate> dates){
        return serviceEASHD.countAllBetween(dates.get(0) , dates.get(1));
    }
}
