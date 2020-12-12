package mtni.its.dashboard;

import mtni.its.dashboard.domain.ENRA;
import mtni.its.dashboard.domain.ENRE;
import mtni.its.dashboard.service.RepoImpl.ServiceENRA;
import mtni.its.dashboard.service.RepoImpl.ServiceENRE;
import mtni.its.dashboard.service.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DashboardApplication {

    @Autowired
    private ServiceENRA repo;
    @Autowired
    private CSVReader csvReader;

    public static void main(String[] args) {

        SpringApplication.run(DashboardApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
//        String path1 = "/home/navid/Documents/Shakar/22/EDW_No_RE_ABL_20201122_12_52_14.csv",path2="/home/navid/Documents/Shakar/23/EDW_No_RE_ABL_20201123_10_52_16.csv";
//        File file1 = new File(path1) , file2 = new File(path2);
//        try {
//             List<ENRA> list1= csvReader.read_ENRA_File(file1 , LocalDate.parse("2020-11-22"));
//             List<ENRA> list2= csvReader.read_ENRA_File(file2 , LocalDate.parse("2020-11-23"));
//             System.err.println("List 1 size: "+list1.size());
//             System.err.println("List 2 size: "+list2.size());
//             final int batchSize = 500;
////             for (int i=0;i<list1.size()/batchSize;i++){
////                 repo.saveAll(list1.subList((i*batchSize)+i , ((i+1)*batchSize)+i));
////             }
//             repo.saveAll(list1.subList(0, batchSize));
//             repo.saveAll(list1);
//             repo.saveAll(list2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
