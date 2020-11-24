package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.ENRSH;
import mtni.its.dashboard.repository.ENRSHRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService<T> {

//    @Autowired
//    ENRSHRepo repository;
//
//    public void save(MultipartFile file){
//        try {
//            List<ENRSH> ENRSHES = CsvUtility.csvToTutorials(file.getInputStream());
//            repository.saveAll(ENRSHES);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load() {
//        List<ENRSH> ENRSHES = repository.findAll();
//        ByteArrayInputStream in = CsvUtility.tutorialsToCSV(ENRSHES);
//        return in;
//    }
//
//    public List<ENRSH> getAllTutorials() {
//
//        return new ArrayList<>(repository.findAll());
//    }
}
