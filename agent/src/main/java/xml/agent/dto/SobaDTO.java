package xml.agent.dto;

import java.util.ArrayList;
import java.util.List;

import xml.agent.model.Smestaj;
import xml.agent.model.Enums.DodatneUsluge;
import xml.agent.model.Enums.KategorijaSmestaja;

public class SobaDTO {
	private int brojLezaja;
	private ArrayList<DodatneUsluge> opcija;
	private KategorijaSmestaja kategorija;
	private Long cena;
	private Smestaj smestaj;
	public int getBrojLezaja() {
		return brojLezaja;
	}
	public ArrayList<DodatneUsluge> getOpcija() {
		return opcija;
	}
	public KategorijaSmestaja getKategorija() {
		return kategorija;
	}
	public Long getCena() {
		return cena;
	}
	public Smestaj getSmestaj() {
		return smestaj;
	}
	public void setBrojLezaja(int brojLezaja) {
		this.brojLezaja = brojLezaja;
	}
	public void setOpcija(ArrayList<DodatneUsluge> opcija) {
		this.opcija = opcija;
	}
	public void setKategorija(KategorijaSmestaja kategorija) {
		this.kategorija = kategorija;
	}
	public void setCena(Long cena) {
		this.cena = cena;
	}
	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	public SobaDTO() {
	}
	
	
}
