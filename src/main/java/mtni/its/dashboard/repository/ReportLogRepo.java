package mtni.its.dashboard.repository;

import mtni.its.dashboard.domain.ReportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReportLogRepo extends JpaRepository<ReportLog , Long> {
    boolean existsByReportDate(LocalDate reportDate);
}
