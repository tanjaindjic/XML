package xml.agent.xmlWeb_smestaj.smestajSoap.ostoja;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import xml.agent.xmlWeb_smestaj.smestajSoap.model.Smestaj;

@Service
public class ServiceSoapLocalImpl implements ServiceSoapLocal{
	/*@Autowired
	private SmestajService smestajService;*/
	@Override
	public List<Smestaj> getSmestajs() {
		List<Smestaj> retVal = new ArrayList<Smestaj>();
		return retVal;
	}
	
}
