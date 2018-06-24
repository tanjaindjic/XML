package xml.agent.xmlWeb_smestaj.smestajSoap.proba;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import xml.agent.xmlWeb_smestaj.smestajSoap.model.Smestaj;

@WebService(name = "SmestajService", targetNamespace = "http://agent.xml.soap/smestaj")
public interface SmestajService {
	@WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSmestajs", targetNamespace = "http://agent.xml.soap/smestaj", className = "xml.agent.xmlWeb_smestaj.smestajSoap.ostoja.GetSmestajs") //ovo ti fali
    @ResponseWrapper(localName = "getSmestajsResponse", targetNamespace = "http://agent.xml.soap/smestaj", className = "xml.agent.xmlWeb_smestaj.smestajSoap.ostoja.getSmestajsResponse")//ovo je okish, lose si nazvala u GetSmestajsRespnse
    public List<Smestaj> getSmestajs();
}
