package be.vdab.entities;

import java.math.BigDecimal;

public class Film {

    private long id, genreId, voorraad, gereserveerd;
    private String titel;
    private BigDecimal prijs;

    public Film(long id, long genreId, String titel, long voorraad, long gereserveerd, BigDecimal prijs) {
	this.id = id;
	this.genreId = genreId;
	this.titel = titel;
	this.voorraad = voorraad;
	this.gereserveerd = gereserveerd;
	this.prijs = prijs;
    }
    
//  public Film(long genreId, String titel, long voorraad, long gereserveerd, BigDecimal prijs) {
//	this.genreId = genreId;
//	this.titel = titel;
//	this.voorraad = voorraad;
//	this.gereserveerd = gereserveerd;
//	this.prijs = prijs;
//  }
    
//    public Film(String titel) {
//	this.titel = titel;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public long getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(long voorraad) {
        this.voorraad = voorraad;
    }

    public long getGereserveerd() {
        return gereserveerd;
    }

    public void setGereserveerd(long gereserveerd) {
        this.gereserveerd = gereserveerd;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }
    
    

}
