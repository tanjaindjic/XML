package XmlWeb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Komentar {

    @Id
    @GeneratedValue
    private Long id;

    private String tekst;

    private boolean odobreno;

    @ManyToOne
    private Rezervacija rezervacija; // ZA OVO ILI ZA SMESTAJ MOZDA ?

    public Komentar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public boolean isOdobreno() {
        return odobreno;
    }

    public void setOdobreno(boolean odobreno) {
        this.odobreno = odobreno;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }
}
