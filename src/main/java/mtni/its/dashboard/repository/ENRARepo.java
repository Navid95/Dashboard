package mtni.its.dashboard.repository;

import mtni.its.dashboard.domain.ENRA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ENRARepo extends JpaRepository<ENRA , Long> {
    List<ENRA> findAllByReportDateEquals(LocalDate reportDate);
    List<ENRA> findAllByReportDateIsBetween(LocalDate start , LocalDate end);
    int countAllByReportDateEquals(LocalDate reportDate);
    int countAllByReportDateBetween(LocalDate start , LocalDate end);
}
