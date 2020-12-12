package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses apache.commons.csv to read CSV file
 */
@Service
public class CSVReader {

    private static Logger logger = LoggerFactory.getLogger(CSVReader.class);
    /**
     *
     * @param file CSV file
     * @param uploadDate the date that the report is generated
     * @return List of ENRSHs
     * @throws IOException
     */
    public List<ENRSH> read_ENRSH_File(File file , LocalDate uploadDate) throws IOException {
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
        reader.close();
        return list;
    }

    public List<ENRE> read_ENRE_File(File file , LocalDate reportDate) throws IOException {
        Reader reader = new FileReader(file);
        List<ENRE> list = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord csvRecord : csvRecords){
            list.add(new ENRE(csvRecord.get("msisdn_nsk"), csvRecord.get("account_link_code_n"),
                    csvRecord.get("cra_ref_num_v"),csvRecord.get("shahkar_id"),
                    csvRecord.get("status_code_v"),csvRecord.get("contract_type_v"),
                    csvRecord.get("profile_type_v"),csvRecord.get("dms_verified_flag_v"),
                    csvRecord.get("sim_category_code_v"),csvRecord.get("registration_dat"), reportDate));
        }
        reader.close();
        return list;
    }

    public List<ENRA> read_ENRA_File(File file , LocalDate reportDate) throws IOException {
        Reader reader = new FileReader(file);
        List<ENRA> list = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord csvRecord : csvRecords){
            ENRA enra = new ENRA(csvRecord.get("msisdn_nsk"), csvRecord.get("account_link_code_n"),
                    csvRecord.get("cra_ref_num_v"),csvRecord.get("shahkar_id"),
                    csvRecord.get("status_code_v"),csvRecord.get("contract_type_v"),
                    csvRecord.get("profile_type_v"),csvRecord.get("dms_verified_flag_v"),
                    csvRecord.get("sim_category_code_v"),csvRecord.get("registration_date"),
                    csvRecord.get("id_type_v"),csvRecord.get("registration_by"),
                    csvRecord.get("user_code_n"),csvRecord.get("chanel_nam"),reportDate);
            list.add(enra);
        }
        reader.close();
        return list;
    }

    public List<ENMA> read_ENMA_File(File file , LocalDate reportDate) throws IOException {
        Reader reader = new FileReader(file);
        List<ENMA> list = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord csvRecord : csvRecords){
            ENMA enma = new ENMA(csvRecord.get("msisdn_nsk") , csvRecord.get("shahkar_i") , reportDate);
            list.add(enma);
        }
        reader.close();
        return list;
    }

    public List<EASHD> read_EASHD_File(File file , LocalDate reportDate) throws IOException {
        Reader reader = new FileReader(file);
        List<EASHD> list = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord csvRecord : csvRecords){
            EASHD eashd = new EASHD(csvRecord.get("msisdn_nsk"),csvRecord.get("account_link_code_n"),
                    csvRecord.get("cra_ref_num_v"),csvRecord.get("shahkar_id"),
                    csvRecord.get("status_code_v"), csvRecord.get("contract_type_v"),
                    csvRecord.get("profile_type_v"),csvRecord.get("dms_verified_flag_v"),
                    csvRecord.get("sim_category_code_v"),csvRecord.get("registration_dat"),
                    reportDate);
            list.add(eashd);
        }
        reader.close();
        return list;
    }
}
