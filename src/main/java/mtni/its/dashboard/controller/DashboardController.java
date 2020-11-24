package mtni.its.dashboard.controller;


import mtni.its.dashboard.Exception.InputFileErrorException;
import mtni.its.dashboard.domain.ENRSH;
import mtni.its.dashboard.service.ENRSHService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private ENRSHService enrshService;

    @Autowired
    public void setEnrshService(ENRSHService enrshService) {
        this.enrshService = enrshService;
    }

    @PostMapping(value = "/enrsh/upload")
    @ResponseBody
    public List<ENRSH> loadFile(@RequestBody MultipartFile file) {
        logger.info("Got file: " + file.getOriginalFilename());
        try {
            return enrshService.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
