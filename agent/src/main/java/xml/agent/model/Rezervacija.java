package xml.agent.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import xml.agent.model.Enums.StatusRezevacije;


@Entity
public class Rezervacija {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusRezevacije status;

    @ManyToOne
    private Soba soba;

    @ManyToOne
    private Smestaj smestaj;

    @ManyToOne
    private Korisnik rezervisao;

    private int ocena;

    private boolean ocenio;

    private Date datumOd;

    private Date datumDo;

    public Rezervacija() {
    }

    public Smestaj getSmestaj() {
        return smestaj;
    }

    public void setSmestaj(Smestaj smestaj) {
        this.smestaj = smestaj;
    }

    public boolean isOcenio() {
        return ocenio;
    }

    public void setOcenio(boolean ocenio) {
        this.ocenio = ocenio;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusRezevacije getStatus() {
        return status;
    }

    public void setStatus(StatusRezevacije status) {
        this.status = status;
    }

    public Korisnik getRezervisao() {
        return rezervisao;
    }

    public void setRezervisao(Korisnik rezervisao) {
        this.rezervisao = rezervisao;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(java.util.Date date) {
        this.datumOd = date;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }
}
