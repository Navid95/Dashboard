package mtni.its.dashboard.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ENRE extends ReportRecord {

    @Column
    private String msisdn_nsk;
    @Column
    private String account_link_code_n;
    @Column
    private String cra_ref_num_v;
    @Column
    private String shahkar_id;
    @Column
    private String status_code_v;
    @Column
    private String contract_type_v;
    @Column
    private String profile_type_v;
    @Column
    private String dms_verified_flag_v;
    @Column
    private String sim_category_code_v;
    @Column
    private String registration_dat;


    public ENRE(String msisdn_nsk, String account_link_code_n, String cra_ref_num_v,
                String shahkar_id, String status_code_v, String contract_type_v,
                String profile_type_v, String dms_verified_flag_v, String sim_category_code_v,
                String registration_dat, LocalDate reportDate) {
        this.msisdn_nsk = msisdn_nsk;
        this.account_link_code_n = account_link_code_n;
        this.cra_ref_num_v = cra_ref_num_v;
        this.shahkar_id = shahkar_id;
        this.status_code_v = status_code_v;
        this.contract_type_v = contract_type_v;
        this.profile_type_v = profile_type_v;
        this.dms_verified_flag_v = dms_verified_flag_v;
        this.sim_category_code_v = sim_category_code_v;
        this.registration_dat = registration_dat;
        this.reportDate = reportDate;
    }

    public ENRE() {
    }

    public String getMsisdn_nsk() {
        return msisdn_nsk;
    }

    public void setMsisdn_nsk(String msisdn_nsk) {
        this.msisdn_nsk = msisdn_nsk;
    }

    public String getAccount_link_code_n() {
        return account_link_code_n;
    }

    public void setAccount_link_code_n(String account_link_code_n) {
        this.account_link_code_n = account_link_code_n;
    }

    public String getCra_ref_num_v() {
        return cra_ref_num_v;
    }

    public void setCra_ref_num_v(String cra_ref_num_v) {
        this.cra_ref_num_v = cra_ref_num_v;
    }

    public String getShahkar_id() {
        return shahkar_id;
    }

    public void setShahkar_id(String shahkar_id) {
        this.shahkar_id = shahkar_id;
    }

    public String getStatus_code_v() {
        return status_code_v;
    }

    public void setStatus_code_v(String status_code_v) {
        this.status_code_v = status_code_v;
    }

    public String getContract_type_v() {
        return contract_type_v;
    }

    public void setContract_type_v(String contract_type_v) {
        this.contract_type_v = contract_type_v;
    }

    public String getProfile_type_v() {
        return profile_type_v;
    }

    public void setProfile_type_v(String profile_type_v) {
        this.profile_type_v = profile_type_v;
    }

    public String getDms_verified_flag_v() {
        return dms_verified_flag_v;
    }

    public void setDms_verified_flag_v(String dms_verified_flag_v) {
        this.dms_verified_flag_v = dms_verified_flag_v;
    }

    public String getSim_category_code_v() {
        return sim_category_code_v;
    }

    public void setSim_category_code_v(String sim_category_code_v) {
        this.sim_category_code_v = sim_category_code_v;
    }

    public String getRegistration_dat() {
        return registration_dat;
    }

    public void setRegistration_dat(String registration_dat) {
        this.registration_dat = registration_dat;
    }

    @Override
    public String toString() {
        return "ENRE{" +
                "msisdn_nsk='" + msisdn_nsk + '\'' +
                ", account_link_code_n='" + account_link_code_n + '\'' +
                ", cra_ref_num_v='" + cra_ref_num_v + '\'' +
                ", shahkar_id='" + shahkar_id + '\'' +
                ", status_code_v='" + status_code_v + '\'' +
                ", contract_type_v='" + contract_type_v + '\'' +
                ", profile_type_v='" + profile_type_v + '\'' +
                ", dms_verified_flag_v='" + dms_verified_flag_v + '\'' +
                ", sim_category_code_v='" + sim_category_code_v + '\'' +
                ", registration_dat='" + registration_dat + '\'' +
                ", id=" + id +
                ", version=" + version +
                ", reportDate=" + reportDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ENRE)) return false;
        ENRE enre = (ENRE) o;
        return Objects.equals(msisdn_nsk, enre.msisdn_nsk) &&
                Objects.equals(account_link_code_n, enre.account_link_code_n) &&
                Objects.equals(cra_ref_num_v, enre.cra_ref_num_v) &&
                Objects.equals(shahkar_id, enre.shahkar_id) &&
                Objects.equals(status_code_v, enre.status_code_v) &&
                Objects.equals(contract_type_v, enre.contract_type_v) &&
                Objects.equals(profile_type_v, enre.profile_type_v) &&
                Objects.equals(dms_verified_flag_v, enre.dms_verified_flag_v) &&
                Objects.equals(sim_category_code_v, enre.sim_category_code_v) &&
                Objects.equals(registration_dat, enre.registration_dat) &&
                Objects.equals(reportDate , enre.reportDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(msisdn_nsk, account_link_code_n, cra_ref_num_v, shahkar_id, status_code_v, contract_type_v, profile_type_v, dms_verified_flag_v, sim_category_code_v, registration_dat , reportDate);
    }
}
