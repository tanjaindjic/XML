package xml.agent.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import xml.agent.model.Enums.KategorijaSmestaja;

public interface KategorijaRepository extends JpaRepository<KategorijaSmestaja, Long>{

}
