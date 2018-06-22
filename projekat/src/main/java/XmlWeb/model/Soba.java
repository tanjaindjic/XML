package XmlWeb.model;

import XmlWeb.model.Enums.KategorijaSmestaja;
import XmlWeb.model.Enums.StatusRezevacije;
import XmlWeb.repository.RezervacijaRepository;
import XmlWeb.model.Enums.DodatneUsluge;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Soba {

    @Id
    @GeneratedValue
    private Long id;
    
    @Transient
    private Long cena;
    
    

    public Long getCena() {
		return cena;
	}


	public void setCena(Long cena) {
		this.cena = cena;
	}

	private int brojLezaja;

    //ovo polje sluzi samo za prikaz dodatnih usluga, pretragu radimo po dodatnim uslugama u smestaju
    @ManyToMany
    private List<DodatneUsluge> opcija;

    //ovo polje je nebitno i moze se zanemariti za sad, jer imamo polje zvezdice u smestaju
    @ManyToOne
    private KategorijaSmestaja kategorija;

    //ovo polje sluzi definisanje intervala u kojima je iznajmljivanje moguce, ukoliko vremenski interval nije ovde naveden, racuna se kao da 
    //nije moguce rezervisati
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Iznajmljivanje> iznajmljivanja;

    @OneToMany
    @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
    private List<Rezervacija> rezervisano;

    @OneToMany
    private List<Cenovnik> cene;

    public Soba() {
    }


    public Soba(int brojLezaja, List<DodatneUsluge> opcija, List<Cenovnik> cene) {
		super();
		this.brojLezaja = brojLezaja;
		this.opcija = opcija;
		this.cene = cene;
	}


	public List<Rezervacija> getRezervisano() {
        return rezervisano;
    }

    public void setRezervisano(List<Rezervacija> rezervisano) {
        this.rezervisano = rezervisano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBrojLezaja() {
        return brojLezaja;
    }

    public void setBrojLezaja(int brojLezaja) {
        this.brojLezaja = brojLezaja;
    }

    public List<DodatneUsluge> getOpcija() {
        return opcija;
    }

    public void setOpcija(List<DodatneUsluge> opcija) {
        this.opcija = opcija;
    }

    public KategorijaSmestaja getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaSmestaja kategorija) {
        this.kategorija = kategorija;
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

    public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
    }

    public List<Cenovnik> getCene() {
        return cene;
    }

    public void setCene(List<Cenovnik> cene) {
        this.cene = cene;
    }
    
    public boolean validateDates(Date pocetak, Date kraj, RezervacijaRepository rezRepo) {
    	ArrayList<Rezervacija> rezBaz = (ArrayList<Rezervacija>) rezRepo.findBySobaId(id);
    	//System.out.println("Usao sam u sobu: "+id+"broj rezervacija je: "+rezBaz.size());
    	for(Rezervacija r:rezBaz) {
    		//System.out.println("Usao sam u rezervaciju---"+r.getDatumOd()+r.getDatumDo());
    		if(r.getDatumDo().compareTo(pocetak)>=0&&r.getDatumOd().compareTo(kraj)<=0&&(!r.getStatus().equals(StatusRezevacije.REJECTED))&&(!r.getStatus().equals(StatusRezevacije.CANCELED))) {
    			//System.out.println("Invalid datum because od RESERVATIONS");
    			return false;
    		}
    	}
    	
    	cena = Long.MAX_VALUE;
    	boolean nasao = false;
    	for(Iznajmljivanje i:iznajmljivanja) {
    		//System.out.println("Usao sam u iznajmljivanje---"+i.getDatumOd()+i.getDatumDo());
    		if((i.getDatumDo().compareTo(kraj)>=0&&i.getDatumOd().compareTo(pocetak)<=0&&i.getMozePojedinacno()==true)||
    				(i.getDatumDo().compareTo(kraj)==00&&i.getDatumOd().compareTo(pocetak)==0&&i.getMozePojedinacno()==false))
    		{
    			nasao = true;
    			if(i.getCena()<cena) cena = i.getCena();
    		}
    	}
    	//System.out.println("Nasao sam iznajmljivanje i ono je: "+nasao);
    	return nasao;
    }
}
