package api.appointment;

import java.math.BigDecimal;
import java.util.Date;

public class AppointmentCandidate {
 Date startdate;
 Date enddate;
 boolean  avaible;
 BigDecimal id;

    public boolean isAvaible() {
        return avaible;
    }
    public void setAvaible(boolean avaible) {
        this.avaible = avaible;
    }
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

}
