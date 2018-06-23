package xml.agent.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import xml.agent.dto.SmestajDTO;
import xml.agent.model.Enums.DodatneUsluge;
import xml.agent.model.Enums.KategorijaSmestaja;
import xml.agent.model.Enums.TipSmestaja;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Smestaj")
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
	
	public boolean validateCategory(List<KategorijaSmestaja> tips) {
		if(tips.size()==0) return true;
		for(KategorijaSmestaja t:tips) {
			if(t.getKategorija().equals(kategorija.getKategorija())) {
				return true;
			}
		}
		return false;
	}
	
	@Transient
	private Long minCena;
	@Transient
	private Long maxCena;
	
	

	public Long getMinCena() {
		return minCena;
	}

	public void setMinCena(Long minCena) {
		this.minCena = minCena;
	}

	public Long getMaxCena() {
		return maxCena;
	}

	public void setMaxCena(Long maxCena) {
		this.maxCena = maxCena;
	}

	@XmlElement(required = true)
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;
    @XmlElement(required = true)
    private String naziv;
    @XmlElement(required = true)
    private String adresa;
    @XmlElement(required = true)
    private String grad;
    @XmlElement(required = true)
    private String drzava;

    @Column(length = 2084)
    private String gmapUrl;
    /*@XmlElement(required = true)
    @Max(5)
	@Min(0)
    private Integer zvezdice;*/
    @XmlElement(required = true)
    @ManyToOne
    private KategorijaSmestaja kategorija;

    @XmlElement(required = true)
    @ManyToMany
    private List<DodatneUsluge> dodatneUsluge;

    @XmlElement(required = true)
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Slika> slike;

    @XmlElement(required = true)
    @Column(length = 2084)
    private String opis;

    @XmlElement(required = true)
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Soba> sobe;

    @XmlElement(required = true)
    @ManyToOne
    private TipSmestaja tip;

    @XmlElement(required = true)
    @ManyToOne
    private Korisnik vlasnik;

    @XmlElement(required = false)
    private float rejting;

    @XmlElement(required = false)
    private int brojOcena;

    
    
    public KategorijaSmestaja getKategorija() {
		return kategorija;
	}

	public void setKategorija(KategorijaSmestaja kategorija) {
		this.kategorija = kategorija;
	}

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

    /*public Integer getZvezdice() {
        return zvezdice;
    }

    public void setZvezdice(Integer zvezdice) {
        this.zvezdice = zvezdice;
    }*/

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

	public void generateFromDTO(SmestajDTO sdto) {
		this.setAdresa(sdto.getAdresa());
		this.setNaziv(sdto.getNaziv());
		this.setDrzava(sdto.getDrzava());
		this.setGrad(sdto.getGrad());
		this.setBrojOcena(0);
		this.setDodatneUsluge(sdto.getDodatneUsluge());	
		this.setGmapUrl(sdto.getGmapUrl());
		this.setKategorija(sdto.getKategorija());	
		this.setMaxCena(sdto.getMaxCena());	
		this.setMinCena(sdto.getMinCena());	
		this.setOpis(sdto.getOpis());
		this.setRejting(0);
		this.setSlike(new ArrayList<Slika>());
		this.setSobe(new ArrayList<Soba>());
		this.setTip(sdto.getTip());
		this.setVlasnik(null);
		
	}
}
