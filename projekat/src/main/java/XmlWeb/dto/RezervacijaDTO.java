package XmlWeb.dto;

import java.util.Date;

public class RezervacijaDTO {

    private Long idSobe;

    private Long idKorisnika;

    private Date pocetnoVreme;

    private Date krajnjeVreme;

    public RezervacijaDTO() {
    }

    public Long getIdSobe() {
        return idSobe;
    }

    public void setIdSobe(Long idSobe) {
        this.idSobe = idSobe;
    }

    public Long getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(Long idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public Date getPocetnoVreme() {
        return pocetnoVreme;
    }

    public void setPocetnoVreme(Date pocetnoVreme) {
        this.pocetnoVreme = pocetnoVreme;
    }

    public Date getKrajnjeVreme() {
        return krajnjeVreme;
    }

    public void setKrajnjeVreme(Date krajnjeVreme) {
        this.krajnjeVreme = krajnjeVreme;
    }
}
