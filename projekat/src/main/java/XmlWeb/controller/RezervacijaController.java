package XmlWeb.controller;


import XmlWeb.dto.RezStatusUpdateDTO;
import XmlWeb.dto.RezervacijaDTO;
import XmlWeb.model.Rezervacija;
import XmlWeb.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RezervacijaController {

    @Autowired
    RezervacijaService rs;

    @RequestMapping(method = RequestMethod.GET, value = "/reservation/getAll")
    public List<Rezervacija> getAll(){
        return rs.getAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/reservation/make")
    public boolean reserve(@RequestBody RezervacijaDTO r){
        return rs.rezervisi(r);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/reservation/update")
    public void update(@RequestBody RezStatusUpdateDTO rsud){
        rs.updateStatus(rsud);
    }


}
