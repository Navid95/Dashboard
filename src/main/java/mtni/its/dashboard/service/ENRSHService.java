package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.ENRSH;
import mtni.its.dashboard.repository.ENRSHRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ENRSHService {

    private Logger logger = LoggerFactory.getLogger(ENRSHService.class);
    private ENRSHRepo repo;
    private CSVReader csvReader;

    @Autowired
    public void setRepo(ENRSHRepo repo) {
        this.repo = repo;
    }
    @Autowired
    public void setCsvReader(CSVReader csvReader) {
        this.csvReader = csvReader;
    }


    /**
     *Calls checkFileDate to extract Date of report based on it's name, then reads the contents using CSVReader and saves the data to DB using ENRSHRepo.
     *
     * @param file CSV file
     * @return list of added ENRSHs if successful, Null if duplicate
     *
     */
    public List<ENRSH> save(File file) throws IOException {
        LocalDate upDate = checkFileDate(file);
        List<ENRSH> list = csvReader.readENRSHFile(file , upDate);
        if (!isDuplicate(list , upDate)) {
            return repo.saveAll(list);
        }
        else {
            return null;
        }
    }

    public List<ENRSH> save(MultipartFile multipartFile) throws IOException {
        File file = new File("src/main/resources/tempFiles/"+multipartFile.getOriginalFilename());
        logger.info("Got File: "+multipartFile.getOriginalFilename());
//        TODO check for file existance
        file.createNewFile();
        if(true){
            LocalDate upDate = checkFileDate(file);
            List<ENRSH> list = CsvUtility.csvToTutorials(multipartFile.getInputStream() , upDate);
            if (!isDuplicate(list , upDate)) {
                logger.info(list.toString());
                return new ArrayList<ENRSH>(repo.saveAll(list));
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public List<ENRSH> getAllEDW_NO_RE_SHWGs() {
        return repo.findAll();
    }

    private LocalDate checkFileDate(File file){
//        TODO extract from file name
        return LocalDate.now();
    }

    private boolean isDuplicate(List<ENRSH> list , LocalDate uploadDate){
//        TODO query DB
        return false;
    }

}
