package xml.agent.dto;

import java.util.List;

import javax.persistence.Transient;

import xml.agent.model.Slika;
import xml.agent.model.Enums.DodatneUsluge;
import xml.agent.model.Enums.KategorijaSmestaja;
import xml.agent.model.Enums.TipSmestaja;

public class SmestajDTO {
	private String naziv;
    private String adresa;
    private String grad;
    private String drzava;
    private String gmapUrl;
    private KategorijaSmestaja kategorija;
    private List<DodatneUsluge> dodatneUsluge;
    private List<String> slike;
    private String opis;
    private TipSmestaja tip;
    private String username;
	private Long minCena;
	private Long maxCena;


	public String getNaziv() {
		return naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public String getGrad() {
		return grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public String getGmapUrl() {
		return gmapUrl;
	}
	public KategorijaSmestaja getKategorija() {
		return kategorija;
	}
	public List<DodatneUsluge> getDodatneUsluge() {
		return dodatneUsluge;
	}
	public List<String> getSlike() {
		return slike;
	}
	public String getOpis() {
		return opis;
	}
	public TipSmestaja getTip() {
		return tip;
	}
	public String getUsername() {
		return username;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public void setGmapUrl(String gmapUrl) {
		this.gmapUrl = gmapUrl;
	}
	public void setKategorija(KategorijaSmestaja kategorija) {
		this.kategorija = kategorija;
	}
	public void setDodatneUsluge(List<DodatneUsluge> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}
	public void setSlike(List<String> slike) {
		this.slike = slike;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public void setTip(TipSmestaja tip) {
		this.tip = tip;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Long getMinCena() {
		return minCena;
	}
	public Long getMaxCena() {
		return maxCena;
	}
	public void setMinCena(Long minCena) {
		this.minCena = minCena;
	}
	public void setMaxCena(Long maxCena) {
		this.maxCena = maxCena;
	}
	public SmestajDTO() {
	}
    
    
}
