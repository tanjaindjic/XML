package xml.agent.xmlWeb_smestaj.smestajSoap.proba;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import xml.agent.xmlWeb_smestaj.smestajSoap.model.Smestaj;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSmestajsResponse", propOrder = {
    "_return"
})
public class GetSmestajsResponse {
	@XmlElement(name = "return")
    protected List<Smestaj> _return;

  
    public List<Smestaj> getReturn() {
        return _return;
    }

    
    public void setReturn(List<Smestaj> value) {
        this._return = value;
    }
}
