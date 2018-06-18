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
		return smestajRepository.findAll();
		/*ArrayList<Smestaj> temp = (ArrayList<Smestaj>) smestajRepository.findByNameAndSobaNumberSeats(ser.getDestination(), ser.getHowManyPeople());
		ArrayList<Smestaj> temp1 = new ArrayList<Smestaj>();
		
		boolean pom = true;
		for(Smestaj s: temp) {
			pom = true;
			for(Soba soba:s.getSobe()) {
				if(soba.validateDates(ser.getFrom(), ser.getTo())) {
					
				}else {
					pom = false;
				}
			}
			if(!pom) {
				temp1.add(s);
			}
		}
		
		return temp1;*/
	}
	
	public Collection<Smestaj> getAllSmestajAdv(SearchDTO ser){
		System.out.println(ser);
		return smestajRepository.findAll();
		/*ArrayList<Smestaj> temp = (ArrayList<Smestaj>) smestajRepository.findByNameAndSobaNumberSeatsAndCategory(ser.getDestination(), ser.getHowManyPeople(), 0);
		ArrayList<Smestaj> temp1 = new ArrayList<Smestaj>();
		
		boolean pom = true;
		for(Smestaj s: temp) {
			pom = true;
			for(Soba soba:s.getSobe()) {
				if(soba.validateDates(ser.getFrom(), ser.getTo())) {
					
				}else {
					pom = false;
				}
			}
			if(!pom&&s.validateCategories(ser.getServices())&&s.validateTypes(ser.getTypes())) {
				temp1.add(s);
			}
		}
		
		return temp1;*/
	}

}
