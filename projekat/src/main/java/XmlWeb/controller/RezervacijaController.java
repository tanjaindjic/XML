package XmlWeb.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.config.AdminWrite;
import XmlWeb.config.AgentWrite;
import XmlWeb.config.UserRead;
import XmlWeb.config.UserWrite;
import XmlWeb.dto.RezStatusUpdateDTO;
import XmlWeb.dto.RezervacijaDTO;
import XmlWeb.model.Rezervacija;
import XmlWeb.service.RezervacijaService;

@RestController
public class RezervacijaController {

    @Autowired
    RezervacijaService rs;

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/getAll")
    public List<Rezervacija> getAll(){
        return rs.getAll();
    }
    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/{id}")
    public List<Rezervacija> getByUser(@PathVariable Long id){ return rs.getRezervacije(id); }
    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/smestaj/{id}")
    public Long getSmestaj(@PathVariable Long id){ return rs.getSmestajId(id); }
    @UserWrite
    @RequestMapping(method = RequestMethod.POST, value = "/reservation/make")
    public boolean reserve(@RequestBody RezervacijaDTO r){ return rs.rezervisi(r); }
    @UserWrite
    @AgentWrite
    @RequestMapping(method = RequestMethod.PUT, value = "/reservation/update")
    public void update(@RequestBody RezStatusUpdateDTO rsud){ rs.updateStatus(rsud); }
    @UserWrite
    @RequestMapping(method = RequestMethod.POST, value = "/reservation/comment/{id}/{ocena}")
    public void comment(@PathVariable Long id, @PathVariable int ocena  ){ rs.addOcena(id, ocena); }

    @UserWrite
    @AdminWrite
    @RequestMapping(method = RequestMethod.GET, value = "/reservation/setBoolean/{id}")
    public void setBoolean(@PathVariable Long id){
        Rezervacija r = rs.getRez(id);
        r.setOcenio(false);
        rs.saveRez(r);
    }

}
