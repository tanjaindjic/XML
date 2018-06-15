package XmlWeb.repository;

import XmlWeb.model.Smestaj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SmestajRepository extends JpaRepository<Smestaj, Long> {
}
