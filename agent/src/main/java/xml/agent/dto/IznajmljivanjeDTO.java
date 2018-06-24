package xml.agent.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class IznajmljivanjeDTO {
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date datumOd;
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date datumDo;

    private Long cena;

    private Boolean mozePojedinacno;

	public Date getDatumOd() {
		return datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public Long getCena() {
		return cena;
	}

	public Boolean getMozePojedinacno() {
		return mozePojedinacno;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}

	public void setCena(Long cena) {
		this.cena = cena;
	}

	public void setMozePojedinacno(Boolean mozePojedinacno) {
		this.mozePojedinacno = mozePojedinacno;
	}

	public IznajmljivanjeDTO() {
	}
    
    
}
