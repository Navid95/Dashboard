package mtni.its.dashboard.domain.stats;

import java.time.LocalDate;

public class DailyStats {

    private int count_EDW_No_RE_SHWG;
    private int count_EDW_ABL_SHWG_DIF;
    private int count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER;
    private int count_EDW_No_RE_ABL;
    private LocalDate statDate;

    public DailyStats(int count_EDW_No_RE_SHWG, int count_EDW_No_RE_ABL,
                      int count_EDW_ABL_SHWG_DIF, int count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER,
                      LocalDate statDate) {
        this.count_EDW_No_RE_SHWG = count_EDW_No_RE_SHWG;
        this.count_EDW_No_RE_ABL = count_EDW_No_RE_ABL;
        this.count_EDW_ABL_SHWG_DIF = count_EDW_ABL_SHWG_DIF;
        this.count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER = count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER;
        this.statDate = statDate;
    }

    public DailyStats() {}

    public int getCount_EDW_No_RE_SHWG() {
        return count_EDW_No_RE_SHWG;
    }

    public void setCount_EDW_No_RE_SHWG(int count_EDW_No_RE_SHWG) {
        this.count_EDW_No_RE_SHWG = count_EDW_No_RE_SHWG;
    }

    public int getCount_EDW_No_RE_ABL() {
        return count_EDW_No_RE_ABL;
    }

    public void setCount_EDW_No_RE_ABL(int count_EDW_No_RE_ABL) {
        this.count_EDW_No_RE_ABL = count_EDW_No_RE_ABL;
    }

    public int getCount_EDW_ABL_SHWG_DIF() {
        return count_EDW_ABL_SHWG_DIF;
    }

    public void setCount_EDW_ABL_SHWG_DIF(int count_EDW_ABL_SHWG_DIF) { this.count_EDW_ABL_SHWG_DIF = count_EDW_ABL_SHWG_DIF; }

    public int getCount_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER() {
        return count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER;
    }

    public void setCount_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER(int count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER) {
        this.count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER = count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER;
    }

    public LocalDate getStatDate() {
        return statDate;
    }

    public void setStatDate(LocalDate statDate) { this.statDate = statDate; }
}