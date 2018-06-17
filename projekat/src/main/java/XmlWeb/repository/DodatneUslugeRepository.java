package XmlWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import XmlWeb.model.Enums.DodatneUsluge;

public interface DodatneUslugeRepository extends JpaRepository<DodatneUsluge, Long>{

}
