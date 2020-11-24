package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.ENRSH;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtility {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"msisdn_nsk"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }
        return false;
    }

    public static List<ENRSH> csvToTutorials(InputStream is , LocalDate uploadDate) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<ENRSH> ENRSHES = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                ENRSH ENRSH = new ENRSH(csvRecord.get("msisdn_nsk"),csvRecord.get("account_link_code_n")
                        ,csvRecord.get("cra_ref_num_v"),csvRecord.get("shahkar_id"),csvRecord.get("status_code_v"),
                        csvRecord.get("contract_type_v"),csvRecord.get("profile_type_v"),csvRecord.get("dms_verified_flag_v"),
                        csvRecord.get("sim_category_code_v"),csvRecord.get("registration_date"),csvRecord.get("id_type_v"),
                        csvRecord.get("registration_by"),csvRecord.get("user_code_n"),csvRecord.get("chanel_nam"),
                        uploadDate);
                ENRSHES.add(ENRSH);
            }
            return ENRSHES;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<ENRSH> ENRSHES) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (ENRSH ENRSH : ENRSHES) {
                List<String> data = Arrays.asList(
                        String.valueOf(ENRSH.getId()),
                        ENRSH.getMsisdn_nsk()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

}
