package xml.agent.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.agent.dto.RezStatusUpdateDTO;
import xml.agent.dto.RezervacijaDTO;
import xml.agent.model.Rezervacija;
import xml.agent.services.RezervacijaService;

@RestController
public class RezervacijaController {

    @Autowired
    RezervacijaService rs;


    @RequestMapping(method = RequestMethod.GET, value = "/reservation/getAll")
    public List<Rezervacija> getAll(){
        return rs.getAll();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/pending")
    public List<Rezervacija> getPending(){
        return rs.getPending();
    }
 
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/{id}")
    public List<Rezervacija> getByUser(@PathVariable Long id){ return rs.getRezervacije(id); }
 
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/smestaj/{id}")
    public Long getSmestaj(@PathVariable Long id){ return rs.getSmestajId(id); }
   
    @RequestMapping(method = RequestMethod.POST, value = "/reservation/make")
    public boolean reserve(@RequestBody RezervacijaDTO r){ return rs.rezervisi(r); }
  
    @RequestMapping(method = RequestMethod.PUT, value = "/reservation/update")
    public void update(@RequestBody RezStatusUpdateDTO rsud){ rs.updateStatus(rsud); }
  
    @RequestMapping(method = RequestMethod.POST, value = "/reservation/comment/{id}/{ocena}")
    public void comment(@PathVariable Long id, @PathVariable int ocena  ){ rs.addOcena(id, ocena); }

    @RequestMapping(method = RequestMethod.GET, value = "/reservation/setBoolean/{id}")
    public void setBoolean(@PathVariable Long id){
        Rezervacija r = rs.getRez(id);
        r.setOcenio(false);
        rs.saveRez(r);
    }

}
