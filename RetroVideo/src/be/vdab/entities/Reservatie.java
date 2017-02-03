package be.vdab.entities;

import java.time.LocalDate;

public class Reservatie {

    long klantid, filmdid;
    LocalDate datum;

    public Reservatie(long klantid, long filmdid, LocalDate datum) {
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    
    
}
