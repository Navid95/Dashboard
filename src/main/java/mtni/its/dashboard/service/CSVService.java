package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.EDW_NO_RE_SHWG;
import mtni.its.dashboard.repository.EDW_NO_RE_SHWG_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    EDW_NO_RE_SHWG_Repo repository;

    public void save(MultipartFile file){
        try {
            List<EDW_NO_RE_SHWG> edw_no_re_shwgs = CsvUtility.csvToTutorials(file.getInputStream());
            repository.saveAll(edw_no_re_shwgs);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<EDW_NO_RE_SHWG> edw_no_re_shwgs = repository.findAll();
        ByteArrayInputStream in = CsvUtility.tutorialsToCSV(edw_no_re_shwgs);
        return in;
    }

    public List<EDW_NO_RE_SHWG> getAllTutorials() {
        return repository.findAll();
    }
}
