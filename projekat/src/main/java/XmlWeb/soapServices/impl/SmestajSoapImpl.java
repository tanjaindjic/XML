package XmlWeb.soapServices.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import XmlWeb.model.Smestaj;
import XmlWeb.repository.SmestajRepository;
import XmlWeb.soapServices.SmestajSoap;

@WebService(
        portName = "SmestajPort",
        serviceName = "SmestajService",
        targetNamespace = "http://xmlWeb-smestaj/smestajSoap",
        endpointInterface = "XmlWeb.soapServices.SmestajSoap")
public class SmestajSoapImpl implements SmestajSoap {

	@Autowired
	private SmestajRepository smestajRepo;
	
	@Override
	public List<Smestaj> getSmestajs() {
		List<Smestaj> retVal = smestajRepo.findAll();
		return retVal;
	}

}
