package XmlWeb.repository;

import XmlWeb.model.Smestaj;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
	
	@Query(value = "SELECT DISTINCT SMESTAJ.ID, SMESTAJ.ADRESA\n, SMESTAJ.BROJ_OCENA, SMESTAJ.DRZAVA, SMESTAJ.GMAP_URL, SMESTAJ.GRAD, SMESTAJ.NAZIV, SMESTAJ.OPIS, SMESTAJ.REJTING, SMESTAJ.ZVEZDICE, SMESTAJ.TIP_ID, SMESTAJ.VLASNIK_ID " + 
			"FROM SMESTAJ, SMESTAJ_SOBE, SOBA, SOBA_IZNAJMLJIVANJA, IZNAJMLJIVANJE\n" + 
			"WHERE SMESTAJ.ID=SMESTAJ_SOBE.SMESTAJ_ID AND SOBA.ID=SMESTAJ_SOBE.SOBE_ID AND SOBA_IZNAJMLJIVANJA.SOBA_ID=SOBA.ID AND SOBA_IZNAJMLJIVANJA.IZNAJMLJIVANJA_ID=IZNAJMLJIVANJE.ID AND "	+ 
			"UPPER(SMESTAJ.NAZIV)=UPPER(?3) AND SOBA.BROJ_LEZAJA>=?4 AND IZNAJMLJIVANJE.DATUM_DO>=?1 AND IZNAJMLJIVANJE.DATUM_OD <=?2", nativeQuery = true)
	List<Smestaj> findSimpleSearch(Date from, Date to, String name, int size);
	
	@Query(value = "select * from smestaj", nativeQuery = true)
	List<Smestaj> findAdvancedSearch();
}
