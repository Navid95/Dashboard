package mtni.its.dashboard.repository;

import mtni.its.dashboard.domain.ENRE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ENRERepo extends JpaRepository<ENRE ,Long> {

    List<ENRE> findAllByReportDateEquals(LocalDate reportDate);
    List<ENRE> findAllByReportDateIsBetween(LocalDate start , LocalDate end);
    int countAllByReportDateEquals(LocalDate reportDate);
    int countAllByReportDateBetween(LocalDate start , LocalDate end);

}
