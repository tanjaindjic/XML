package XmlWeb.dto;

public class KomentarDTO {

    private String tekst;

    private Long rezervacijaId;

    public KomentarDTO() {
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    public void setRezervacijaId(Long rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }
}
