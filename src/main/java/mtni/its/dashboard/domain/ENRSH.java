package mtni.its.dashboard.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class ENRSH {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
    private String registration_date;
    @Column
    private String id_type_v;
    @Column
    private String registration_by;
    @Column
    private String user_code_n;
    @Column
    private String chanel_nam;
    @Column
    private LocalDate uploadDate;

    /**
     * Default constructor
     */
    public ENRSH() {}

    /**
     * Parametrized constructor
     *
     * @param msisdn_nsk
     * @param account_link_code_n
     * @param cra_ref_num_v
     * @param shahkar_id
     * @param status_code_v
     * @param contract_type_v
     * @param profile_type_v
     * @param dms_verified_flag_v
     * @param sim_category_code_v
     * @param registration_date
     * @param id_type_v
     * @param registration_by
     * @param user_code_n
     * @param chanel_nam
     * @param uploadDate Date that this record was captured on Network
     */
    public ENRSH(String msisdn_nsk, String account_link_code_n, String cra_ref_num_v,
                 String shahkar_id, String status_code_v, String contract_type_v, String profile_type_v,
                 String dms_verified_flag_v, String sim_category_code_v, String registration_date,
                 String id_type_v, String registration_by, String user_code_n, String chanel_nam , LocalDate uploadDate) {
        this.msisdn_nsk = msisdn_nsk;
        this.account_link_code_n = account_link_code_n;
        this.cra_ref_num_v = cra_ref_num_v;
        this.shahkar_id = shahkar_id;
        this.status_code_v = status_code_v;
        this.contract_type_v = contract_type_v;
        this.profile_type_v = profile_type_v;
        this.dms_verified_flag_v = dms_verified_flag_v;
        this.sim_category_code_v = sim_category_code_v;
        this.registration_date = registration_date;
        this.id_type_v = id_type_v;
        this.registration_by = registration_by;
        this.user_code_n = user_code_n;
        this.chanel_nam = chanel_nam;
        this.uploadDate = uploadDate;
    }


    // ******************************************************************

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getId_type_v() {
        return id_type_v;
    }

    public void setId_type_v(String id_type_v) {
        this.id_type_v = id_type_v;
    }

    public String getRegistration_by() {
        return registration_by;
    }

    public void setRegistration_by(String registration_by) {
        this.registration_by = registration_by;
    }

    public String getUser_code_n() {
        return user_code_n;
    }

    public void setUser_code_n(String user_code_n) {
        this.user_code_n = user_code_n;
    }

    public String getChanel_nam() {
        return chanel_nam;
    }

    public void setChanel_nam(String chanel_nam) {
        this.chanel_nam = chanel_nam;
    }

    @Override
    public String toString() {
        return "ENRSH{" +
                "id=" + id +
                ", msisdn_nsk='" + msisdn_nsk + '\'' +
                ", account_link_code_n='" + account_link_code_n + '\'' +
                ", cra_ref_num_v='" + cra_ref_num_v + '\'' +
                ", shahkar_id='" + shahkar_id + '\'' +
                ", status_code_v='" + status_code_v + '\'' +
                ", contract_type_v='" + contract_type_v + '\'' +
                ", profile_type_v='" + profile_type_v + '\'' +
                ", dms_verified_flag_v='" + dms_verified_flag_v + '\'' +
                ", sim_category_code_v='" + sim_category_code_v + '\'' +
                ", registration_date='" + registration_date + '\'' +
                ", id_type_v='" + id_type_v + '\'' +
                ", registration_by='" + registration_by + '\'' +
                ", user_code_n='" + user_code_n + '\'' +
                ", chanel_nam='" + chanel_nam + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ENRSH enrsh = (ENRSH) o;
        return Objects.equals(msisdn_nsk, enrsh.msisdn_nsk) &&
                Objects.equals(account_link_code_n, enrsh.account_link_code_n) &&
                Objects.equals(cra_ref_num_v, enrsh.cra_ref_num_v) &&
                Objects.equals(shahkar_id, enrsh.shahkar_id) &&
                Objects.equals(status_code_v, enrsh.status_code_v) &&
                Objects.equals(contract_type_v, enrsh.contract_type_v) &&
                Objects.equals(profile_type_v, enrsh.profile_type_v) &&
                Objects.equals(dms_verified_flag_v, enrsh.dms_verified_flag_v) &&
                Objects.equals(sim_category_code_v, enrsh.sim_category_code_v) &&
                Objects.equals(registration_date, enrsh.registration_date) &&
                Objects.equals(id_type_v, enrsh.id_type_v) &&
                Objects.equals(registration_by, enrsh.registration_by) &&
                Objects.equals(user_code_n, enrsh.user_code_n) &&
                Objects.equals(chanel_nam, enrsh.chanel_nam) &&
                Objects.equals(uploadDate, enrsh.uploadDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(msisdn_nsk, account_link_code_n, cra_ref_num_v, shahkar_id, status_code_v, contract_type_v, profile_type_v, dms_verified_flag_v, sim_category_code_v, registration_date, id_type_v, registration_by, user_code_n, chanel_nam, uploadDate);
    }
}

