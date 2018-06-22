package XmlWeb.soapServices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import XmlWeb.model.Smestaj;

@WebService(name="SmestajService", targetNamespace = "http://xmlWeb-smestaj/smestajSoap")
public interface SmestajSoap {
	
	 List<Smestaj> getSmestajs();

}
