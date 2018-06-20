package XmlWeb.soapServices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import XmlWeb.model.Smestaj;

@WebService(targetNamespace = "http://xmlWeb-smestaj/wsdl")
public interface SmestajSoap {
	
	@WebMethod
	public List<Smestaj> getSmestajs();

}
