package XmlWeb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test {

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String test() {
        return "TEST RADI !";
    }

}
