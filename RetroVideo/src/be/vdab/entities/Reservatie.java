package be.vdab.entities;

import java.time.LocalDateTime;

public class Reservatie {

    long klantid, filmdid;
    LocalDateTime datum;

    public Reservatie(long klantid, long filmdid, LocalDateTime datum) {
	this.klantid = klantid;
	this.filmdid = filmdid;
	this.datum = datum;
    }

    public long getKlantid() {
        return klantid;
    }

    public void setKlantid(long klantid) {
        this.klantid = klantid;
    }

    public long getFilmdid() {
        return filmdid;
    }

    public void setFilmdid(long filmdid) {
        this.filmdid = filmdid;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    
    
}
