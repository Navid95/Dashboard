package mtni.its.dashboard.repository;

import mtni.its.dashboard.domain.ENRSH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ENRSHRepo extends JpaRepository<ENRSH, Long> {

    List<ENRSH> findAllByReportDateEquals(LocalDate reportDate);

    /**
     * includes start and end date
     * @param start
     * @param end
     * @return
     */
    List<ENRSH> findAllByReportDateIsBetween(LocalDate start , LocalDate end);

    int countAllByReportDateEquals(LocalDate reportDate);

    int countAllByReportDateBetween(LocalDate start , LocalDate end);
}
