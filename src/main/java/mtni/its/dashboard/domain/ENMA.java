package mtni.its.dashboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ENMA extends ReportRecord {

    @Column
    private String msisdn_nsk;
    @Column
    private String shahkar_i;

    public ENMA() {
    }

    public ENMA(String msisdn_nsk, String shahkar_i , LocalDate reportDate) {
        this.msisdn_nsk = msisdn_nsk;
        this.shahkar_i = shahkar_i;
        this.reportDate = reportDate;
    }

    public String getMsisdn_nsk() {
        return msisdn_nsk;
    }

    public void setMsisdn_nsk(String msisdn_nsk) {
        this.msisdn_nsk = msisdn_nsk;
    }

    public String getShahkar_i() {
        return shahkar_i;
    }

    public void setShahkar_i(String shahkar_i) {
        this.shahkar_i = shahkar_i;
    }

    @Override
    public String toString() {
        return "ENMA{" +
                "msisdn_nsk='" + msisdn_nsk + '\'' +
                ", shahkar_i='" + shahkar_i + '\'' +
                ", id=" + id +
                ", version=" + version +
                ", reportDate=" + reportDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ENMA)) return false;
        ENMA enma = (ENMA) o;
        return Objects.equals(msisdn_nsk, enma.msisdn_nsk) &&
                Objects.equals(shahkar_i, enma.shahkar_i) &&
                Objects.equals(reportDate , enma.reportDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(msisdn_nsk, shahkar_i ,reportDate);
    }
}