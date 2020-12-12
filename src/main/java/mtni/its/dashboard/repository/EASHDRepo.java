package mtni.its.dashboard.repository;

import mtni.its.dashboard.domain.EASHD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EASHDRepo extends JpaRepository<EASHD,Long> {
    List<EASHD> findAllByReportDateEquals(LocalDate reportDate);
    List<EASHD> findAllByReportDateIsBetween(LocalDate start , LocalDate end);
    int countAllByReportDateEquals(LocalDate reportDate);
    int countAllByReportDateBetween(LocalDate start , LocalDate end);
}
