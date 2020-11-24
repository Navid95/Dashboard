package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.ENRSH;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses apache.commons.csv to read CSV file (ENRSH)
 */
@Service
public class CSVReader {

    /**
     *
     * @param file CSV file
     * @param uploadDate the date that the file contents were captured on Network
     * @return List of ENRSH
     * @throws IOException
     */
    public List<ENRSH> readENRSHFile(File file , LocalDate uploadDate) throws IOException {
        Reader reader = new FileReader(file);
        List<ENRSH> list = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord csvRecord : csvRecords){
            list.add(new ENRSH(csvRecord.get("msisdn_nsk"),csvRecord.get("account_link_code_n")
                    ,csvRecord.get("cra_ref_num_v"),csvRecord.get("shahkar_id"),csvRecord.get("status_code_v"),
                    csvRecord.get("contract_type_v"),csvRecord.get("profile_type_v"),csvRecord.get("dms_verified_flag_v"),
                    csvRecord.get("sim_category_code_v"),csvRecord.get("registration_date"),csvRecord.get("id_type_v"),
                    csvRecord.get("registration_by"),csvRecord.get("user_code_n"),csvRecord.get("chanel_nam"),
                    uploadDate));
        }
        return list;
    }
}
