package XmlWeb.model;

import XmlWeb.model.Enums.DodatneUsluge;
import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.model.Enums.TipSmestaja;

import javax.persistence.*;
import java.util.List;

@Entity
public class Smestaj {
	
	public boolean validateCategories(List<DodatneUsluge> cats) {
		if(cats.size()==0) return true;
		boolean flag = false;
		int rez = 0;
		for(DodatneUsluge d:cats) {
			flag = false;
			for(DodatneUsluge d1:dodatneUsluge) {
				if(d.getOpcija().equals(d1.getOpcija())) {
					flag = true;
				}
			}
			if(flag) rez++;
		}
		if(cats.size()==rez) {
			return true;
		}
		return false;
	}
	
	public boolean validateTypes(List<TipSmestaja> tips) {
		if(tips.size()==0) return true;
		for(TipSmestaja t:tips) {
			if(t.getTip().equals(tip.getTip())) {
				return true;
			}
		}
		return false;
	}

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String naziv;

    private String adresa;

    private String grad;

    private String drzava;

    @Column(length = 2084)
    private String gmapUrl;

    private Integer zvezdice;

    @ManyToMany
    private List<DodatneUsluge> dodatneUsluge;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Slika> slike;

    @Column(length = 2084)
    private String opis;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Soba> sobe;

    @ManyToOne
    private TipSmestaja tip;

    @ManyToOne
    private Korisnik vlasnik;

    private float rejting;

    private int brojOcena;

    public Smestaj() {
    }

    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGmapUrl() {
        return gmapUrl;
    }

    public void setGmapUrl(String gmapUrl) {
        this.gmapUrl = gmapUrl;
    }

    public Integer getZvezdice() {
        return zvezdice;
    }

    public void setZvezdice(Integer zvezdice) {
        this.zvezdice = zvezdice;
    }

    public List<DodatneUsluge> getDodatneUsluge() {
        return dodatneUsluge;
    }

    public void setDodatneUsluge(List<DodatneUsluge> dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public List<Slika> getSlike() {
        return slike;
    }

    public void setSlike(List<Slika> slike) {
        this.slike = slike;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Soba> getSobe() {
        return sobe;
    }

    public void setSobe(List<Soba> sobe) {
        this.sobe = sobe;
    }

    public TipSmestaja getTip() {
        return tip;
    }

    public void setTip(TipSmestaja tip) {
        this.tip = tip;
    }

    public Korisnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }

    public float getRejting() {
        return rejting;
    }

    public void setRejting(float rejting) {
        this.rejting = rejting;
    }

    public int getBrojOcena() {
        return brojOcena;
    }

    public void setBrojOcena(int brojOcena) {
        this.brojOcena = brojOcena;
    }
}
