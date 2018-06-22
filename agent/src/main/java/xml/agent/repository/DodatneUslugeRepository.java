package xml.agent.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import xml.agent.model.Enums.DodatneUsluge;

public interface DodatneUslugeRepository extends JpaRepository<DodatneUsluge, Long>{

}
