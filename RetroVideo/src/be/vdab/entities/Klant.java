package be.vdab.entities;

public class Klant {

    private long id;
    private String familienaam, voornaam, straatNummer, postcode, gemeente;

    public Klant(long id, String familienaam, String voornaam, String straatNummer, String postcode, String gemeente) {
	this.id = id;
	this.familienaam = familienaam;
	this.voornaam = voornaam;
	this.straatNummer = straatNummer;
	this.postcode = postcode;
	this.gemeente = gemeente;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getFamilienaam() {
	return familienaam;
    }

    public void setFamilienaam(String familienaam) {
	this.familienaam = familienaam;
    }

    public String getVoornaam() {
	return voornaam;
    }

    public void setVoornaam(String voornaam) {
	this.voornaam = voornaam;
    }

    public String getStraatNummer() {
	return straatNummer;
    }

    public void setStraatNummer(String straatNummer) {
	this.straatNummer = straatNummer;
    }

    public String getPostcode() {
	return postcode;
    }

    public void setPostcode(String postcode) {
	this.postcode = postcode;
    }

    public String getGemeente() {
	return gemeente;
    }

    public void setGemeente(String gemeente) {
	this.gemeente = gemeente;
    }
    
    public String getNaam() {
	return voornaam + " " + familienaam;
    }

}
