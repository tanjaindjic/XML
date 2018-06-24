package xml.agent.xmlWeb_smestaj.smestajSoap.proba;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.xmlWeb_smestaj.smestajSoap.model.Smestaj;


@Service
public class ServiceSoapLocalImpl implements ServiceSoapLocal{
	/*@Autowired
	private SmestajService smestajService;*/
	@Override
	public List<Smestaj> getSmestajs() {
		List<Smestaj> retVal = new ArrayList<Smestaj>();//smestajService.getSmestajs();
		return retVal;
	}
	
}
