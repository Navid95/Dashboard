package mtni.its.dashboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");
            String[] beanNamse = context.getBeanDefinitionNames();
            Arrays.sort(beanNamse);
            for (String beanname : beanNamse){
                System.out.println(beanname);
            }
        };
    }

}
