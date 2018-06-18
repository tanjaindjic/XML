package XmlWeb.repository;

import XmlWeb.model.Smestaj;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
	
	@Query(value = "SELECT *\n" + 
			"FROM SMESTAJ, SMESTAJ_SOBE, SOBA, SOBA_IZNAJMLJIVANJA, IZNAJMLJIVANJE\n" + 
			"WHERE s.ID=SMESTAJ_SOBE.SMESTAJ_ID AND SOBA.ID=SMESTAJ_SOBE.SOBE_ID AND SOBA_IZNAJMLJIVANJA.SOBA_ID=SOBA.ID AND SOBA_IZNAJMLJIVANJA.IZNAJMLJIVANJA_ID=IZNAJMLJIVANJE.ID AND"	+ 
			"UPPER(SMESTAJ.NAZIV)=UPPER(?3) AND SOBA.BROJ_LEZAJA>=?4 AND IZNAJMLJIVANJE.DATUM_DO>=?2 AND IZNAJMLJIVANJE.DATUM_OD <=?3", nativeQuery = true)
	List<Smestaj> findSimpleSearch(Date from, Date to, String name, int size);
	
	@Query(value = "select * from smestaj", nativeQuery = true)
	List<Smestaj> findAdvancedSearch();
}
