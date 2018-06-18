package XmlWeb.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XmlWeb.dto.SearchDTO;
import XmlWeb.model.Smestaj;
import XmlWeb.model.Soba;
import XmlWeb.repository.SmestajRepository;

@Service
public class SmestajService {
	
	@Autowired
	private SmestajRepository smestajRepository;
	
	public Collection<Smestaj> getAllSmestaj(){
		return smestajRepository.findAll();
	}
	
	public Collection<Smestaj> getAllSmestajSimple(SearchDTO ser){
		System.out.println(ser);
		ArrayList<Smestaj> temp = (ArrayList<Smestaj>) smestajRepository.findByNameAndSobaNumberSeats(ser.getDestination(), ser.getHowManyPeople());
		ArrayList<Smestaj> temp1 = new ArrayList<Smestaj>();
		
		for(Smestaj s: temp) {
			for(Soba soba:s.getSobe()) {
				if(soba.validateDates(ser.getFrom(), ser.getTo())) {
					temp1.add(s);
				}
			}
		}
		
		return temp1;
	}
	
	public Collection<Smestaj> getAllSmestajAdv(SearchDTO ser){
		return smestajRepository.findAll();//findAdvancedSearch();
	}

}
