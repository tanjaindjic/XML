package xml.agent.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml.agent.model.Smestaj;
import xml.agent.model.Soba;
import xml.agent.repository.RezervacijaRepository;
import xml.agent.repository.SmestajRepository;
import XmlWeb.dto.SearchDTO;



@Service
public class SmestajService {
	
	@Autowired
	private SmestajRepository smestajRepository;
	
	@Autowired
	private RezervacijaRepository reservationRepository;
	
	
	
	public Collection<Smestaj> getAllSmestaj(){
		return smestajRepository.findAll();
	}
	
	public Collection<Smestaj> getAllSmestajSimple(SearchDTO ser){
		//System.out.println(ser);
		ArrayList<Smestaj> temp = (ArrayList<Smestaj>) smestajRepository.findByNameAndSobaNumberSeats(ser.getDestination(), ser.getHowManyPeople());
		ArrayList<Smestaj> temp1 = new ArrayList<Smestaj>();
		
		boolean pom = false;
		
		for(Smestaj s: temp) {
			ArrayList<Soba> tempSobe = new ArrayList<>();
			s.setMinCena(Long.MAX_VALUE);
			s.setMaxCena(0L);
			pom = false;
			//System.out.println("Usao sam u smestaje "+s.getNaziv()+" i bollean je: "+pom);
			for(Soba soba:s.getSobe()) {
				
				if(soba.validateDates(ser.getFrom(), ser.getTo(), reservationRepository)&&soba.getBrojLezaja()>=ser.getHowManyPeople()) {
					pom = true;
					tempSobe.add(soba);
					if(soba.getCena()>s.getMaxCena())s.setMaxCena(soba.getCena());
					if(soba.getCena()<s.getMinCena())s.setMinCena(soba.getCena());
				}
			}
			//System.out.println("Zavrsavam smestaj "+s.getNaziv()+" i bollean je: "+pom+"kolko sam ubacio: "+tempSobe.size());
			if(pom==true) {
				temp1.add(s);
				s.setSobe(tempSobe);
			}
		}
		return temp1;
	}
	
	public Collection<Smestaj> getAllSmestajAdv(SearchDTO ser){
		System.out.println(ser);
		ArrayList<Integer> tipovi = (ArrayList<Integer>) ser.getCatss();
		System.out.println(tipovi);
		ArrayList<Smestaj> temp = (ArrayList<Smestaj>) smestajRepository.findByNameAndSobaNumberSeatsAndCategory(ser.getDestination(), ser.getHowManyPeople(), tipovi.get(0), tipovi.get(1), tipovi.get(2), tipovi.get(3), tipovi.get(4), tipovi.get(5));
		ArrayList<Smestaj> temp1 = new ArrayList<Smestaj>();
		
		boolean pom = false;
		for(Smestaj s: temp) {
			s.setMinCena(Long.MAX_VALUE);
			s.setMaxCena(0L);
			ArrayList<Soba> tempSobe = new ArrayList<>();
			pom = false;
			System.out.println("Usao sam u smestaje "+s.getNaziv()+" i bollean je: "+pom);
			for(Soba soba:s.getSobe()) {
				System.out.println("Usao sam u sobe u smestaju "+s.getNaziv()+" i bollean je: "+pom);
				if(soba.validateDates(ser.getFrom(), ser.getTo(), reservationRepository) &&soba.getBrojLezaja()>=ser.getHowManyPeople()) {
					pom = true;
					tempSobe.add(soba);
					if(soba.getCena()>s.getMaxCena())s.setMaxCena(soba.getCena());
					if(soba.getCena()<s.getMinCena())s.setMinCena(soba.getCena());
				}
			}
			System.out.println("Zavrsavam smestaj "+s.getNaziv()+" i bollean je: "+pom);
			if(pom==true&&s.validateCategories(ser.getServices())&&s.validateTypes(ser.getTypes())&&s.validateCategory(ser.getCats())) {
				temp1.add(s);
				s.setSobe(tempSobe);
			}
		}
		return temp1;
	}

	public Smestaj dodajSmestaj(Smestaj smestaj) {
		return smestajRepository.save(smestaj);
	}

	public List<Smestaj> getAgentSmestaj(String username) {
		return smestajRepository.findByVlasnikUsername(username);
	}

	public Smestaj getSmestajByID(Long id) {
		Optional<Smestaj> retVal = null;
		retVal = smestajRepository.findById(id);
		return retVal.get();
	}

}
