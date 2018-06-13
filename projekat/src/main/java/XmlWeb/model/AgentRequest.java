package XmlWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AgentRequest {
	
	 @Id
	 @GeneratedValue
	private Long id;
	
	@OneToOne
	private Korisnik korisnik;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public AgentRequest(Long id, Korisnik korisnik) {
		super();
		this.id = id;
		this.korisnik = korisnik;
	}

	public AgentRequest() {
		super();
	}
	
	

}
