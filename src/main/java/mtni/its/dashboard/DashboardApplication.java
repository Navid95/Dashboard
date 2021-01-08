package mtni.its.dashboard;

import mtni.its.dashboard.service.RepoImpl.ServiceReportLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:configuration/custom.properties")
public class DashboardApplication {

    private static Logger logger = LoggerFactory.getLogger(DashboardApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){

//        ReportLog reportLog = new ReportLog(LocalDate.parse("2020-12-22" , DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        try{
//            serviceReportLog.save(reportLog);
//        } catch (Exception e){
//
//        }
//        logger.info("Report Log test: "+serviceReportLog.existsByReportDate(LocalDate.parse("2020-12-22" , DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
//        logger.info("Report Log test: "+serviceReportLog.existsByReportDate(LocalDate.parse("2020-12-23" , DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNamse = context.getBeanDefinitionNames();
//            Arrays.sort(beanNamse);
//            for (String beanname : beanNamse){
//                System.out.println(beanname);
//            }
        };
    }
}
