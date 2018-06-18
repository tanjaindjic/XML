package xml.agent.model.Enums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipSmestaja {

    @Id
    @GeneratedValue
    private Long id;

    private String tip;

	public TipSmestaja() {
		super();
	}

	public TipSmestaja(String tip) {
		super();
		this.tip = tip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
    
    

}
