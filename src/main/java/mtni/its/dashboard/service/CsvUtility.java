package mtni.its.dashboard.service;

import mtni.its.dashboard.domain.EDW_NO_RE_SHWG;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtility {

//    @Value("${shit}")
//    private static String shit;
//
//    public static String printShit(){
//        return shit;
//    }

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"msisdn_nsk"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }
        return false;
    }

    public static List<EDW_NO_RE_SHWG> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<EDW_NO_RE_SHWG> edw_no_re_shwgs = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                EDW_NO_RE_SHWG edw_no_re_shwg= new EDW_NO_RE_SHWG(csvRecord.get("msisdn_nsk"));
                edw_no_re_shwgs.add(edw_no_re_shwg);
            }
            return edw_no_re_shwgs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


    public static ByteArrayInputStream tutorialsToCSV(List<EDW_NO_RE_SHWG> edw_no_re_shwgs) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (EDW_NO_RE_SHWG edw_no_re_shwg : edw_no_re_shwgs) {
                List<String> data = Arrays.asList(
                        String.valueOf(edw_no_re_shwg.getId()),
                        edw_no_re_shwg.getMsisdn_nsk()
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
