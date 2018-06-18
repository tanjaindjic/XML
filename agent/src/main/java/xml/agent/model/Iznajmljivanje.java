package xml.agent.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Iznajmljivanje {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Soba soba;

    private Date datumOd;

    private Date datumDo;

    private Long cena;

    private Boolean mozePojedinacno;


    public Iznajmljivanje() {
    }
    
    

    public Iznajmljivanje(Date datumOd, Date datumDo, Long cena, Boolean mozePojedinacno) {
		super();
		this.datumOd = datumOd;
		this.datumDo = datumDo;
		this.cena = cena;
		this.mozePojedinacno = mozePojedinacno;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Soba getSoba() {
        return soba;
    }

    public void setSoba(Soba soba) {
        this.soba = soba;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Long getCena() {
        return cena;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    public Boolean getMozePojedinacno() {
        return mozePojedinacno;
    }

    public void setMozePojedinacno(Boolean mozePojedinacno) {
        this.mozePojedinacno = mozePojedinacno;
    }

}
