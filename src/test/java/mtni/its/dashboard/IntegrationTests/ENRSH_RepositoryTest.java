package mtni.its.dashboard.IntegrationTests;


import mtni.its.dashboard.domain.ENRSH;
import mtni.its.dashboard.repository.ENRSHRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ENRSH_RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ENRSHRepo repo;

    @Test
    public void saveAllTest(){
        long id =1;
        List<ENRSH> enrshList1 , enrshList2;
        ENRSH enrsh1, enrsh2, enrsh3, enrsh4, enrsh5, found;
        enrsh1 = new ENRSH();
        enrsh2 = new ENRSH();
        enrsh3 = new ENRSH();
        enrsh4 = new ENRSH();
        enrsh5 = new ENRSH();
        enrsh1.setRegistration_by("Navid");
        enrsh2.setRegistration_by("Kamran");
        enrsh3.setRegistration_by("Amin");
        enrsh4.setRegistration_by("Tara");
        enrsh5.setRegistration_by("Kian");

        enrshList1= new ArrayList<>();
        enrshList1.add(enrsh1);
        enrshList1.add(enrsh2);
        enrshList1.add(enrsh3);
        enrshList1.add(enrsh4);
        enrshList1.add(enrsh5);
        repo.saveAll(enrshList1);
        found = entityManager.find(ENRSH.class ,id);
        System.out.println(found);
        Assertions.assertEquals(enrsh1.getRegistration_by(),found.getRegistration_by());
    }
}
