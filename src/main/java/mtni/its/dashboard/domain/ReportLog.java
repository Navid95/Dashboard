package mtni.its.dashboard.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ReportLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @Column(unique = true , nullable = false)
    private LocalDate reportDate;

    public ReportLog(LocalDate reportDate) {
        this.reportDate = reportDate; }

    public ReportLog() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }
}
