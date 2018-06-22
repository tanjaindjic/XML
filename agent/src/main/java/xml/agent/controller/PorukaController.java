package xml.agent.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xml.agent.config.UserRead;
import xml.agent.config.UserWrite;
import xml.agent.dto.PorukaDTO;
import xml.agent.model.Korisnik;
import xml.agent.model.Poruka;
import xml.agent.services.PorukaService;

@RestController
public class PorukaController {

    @Autowired
    private PorukaService ps;

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/messages/getAll")
    public List<Poruka> getAll(){
       return ps.getAllPoruke();
    }

    @UserWrite
    @RequestMapping(method = RequestMethod.POST, value = "/messages/send")
    public void sendMessage(@RequestBody PorukaDTO p){
        System.out.println("UAPO JE NA BACKEND :D");
        ps.sendPoruka(p);
    }

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/messages/inbox/{id}")
    public List<Korisnik> getInbox(@PathVariable Long id){ return ps.getInbox(id);}

    @UserRead
    @RequestMapping(method = RequestMethod.GET, value = "/messages/inbox/{id}/chat/{id2}")
    public List<Poruka> getChat(@PathVariable Long id, @PathVariable Long id2 ){ return ps.getChat(id,id2); }


}
