package mtni.its.dashboard.domain;

import javax.persistence.*;

@Entity
@Table
public class EDW_NO_RE_SHWG {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String msisdn_nsk;

    public EDW_NO_RE_SHWG(String msisdn_nsk) {
        this.msisdn_nsk = msisdn_nsk;
    }

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

    @Override
    public String toString() {
        return "EDW_NO_RE_SHWG{" +
                "id=" + id +
                ", msisdn_nsk='" + msisdn_nsk + '\'' +
                '}';
    }
}
