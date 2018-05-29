package XmlWeb.repository;

import XmlWeb.model.Korisnik;
import XmlWeb.model.Poruka;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PorukaRepository extends CrudRepository<Poruka, Long> {
    List<Poruka> findByPosiljalacId(Long id);
    List<Poruka> findByPrimalacId(Long id);
    List<Poruka> findByPosiljalacIdAndPrimalacId(Long posiljalac, Long primalac);
    List<Poruka> findByPrimalacIdAndPosiljalacId(Long primalac, Long posiljalac);
   // List<Poruka> findDistinctByPosiljalacIdAndPrimalacIdOrPrimalacIdAndPosiljalacId(Long id, Long id2, Long id3, Long id4);
    List<Poruka>  findDistinctByPosiljalacIdAndPrimalacIdOrPrimalacIdAndPosiljalacIdOrderByVremeKreiranjaAsc(Long id, Long id2, Long id3, Long id4); //OrderByVremeKreiranjaAsc
    List<SamoPosiljaoci> findDistinctByPrimalacId(Long id);// Ovde saljem id osobe a dobijam id sve korisnike koji su mu pisali :D
    List<SamoPrimaoci> findDistinctByPosiljalacId (Long id);

}
