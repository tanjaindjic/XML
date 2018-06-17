package XmlWeb.repository;

import XmlWeb.model.Smestaj;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
	
	/*@Query("select * from smestaj")
	List<Smestaj> findSimpleSearch();
	
	@Query("select * from smestaj")
	List<Smestaj> findAdvancedSearch();*/
}
