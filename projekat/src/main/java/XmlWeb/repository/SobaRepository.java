package XmlWeb.repository;

import XmlWeb.model.Soba;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SobaRepository extends JpaRepository<Soba, Long> {
}
