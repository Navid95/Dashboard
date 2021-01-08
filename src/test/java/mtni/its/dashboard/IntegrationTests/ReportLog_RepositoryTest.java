package mtni.its.dashboard.IntegrationTests;

import mtni.its.dashboard.domain.ReportLog;
import mtni.its.dashboard.repository.ReportLogRepo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@RunWith(SpringRunner.class)
//@DataJpaTest
public class ReportLog_RepositoryTest {

//    private static Logger logger = LoggerFactory.getLogger(ReportLog_RepositoryTest.class);
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private ReportLogRepo reportLogRepo;
//
//    @Test
//    public void existsByReportDate_Test(){
//
//        logger.info("Today's date: "+LocalDate.parse("2021-01-01" , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        ReportLog reportLog = new ReportLog(LocalDate.parse("2020-12-22" , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//
//        entityManager.persist(reportLog);
//        entityManager.flush();
//        Assertions.assertTrue(reportLogRepo.existsByReportDate(LocalDate.parse("2020-12-22" , DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//        Assertions.assertFalse(reportLogRepo.existsByReportDate(LocalDate.parse("2020-12-23" , DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//    }
}
