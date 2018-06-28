package XmlWeb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import XmlWeb.config.AdminWrite;
import XmlWeb.config.AgentWrite;
import XmlWeb.config.PermitAll;
import XmlWeb.config.UserWrite;
import XmlWeb.service.LoginService;


@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;

    @PermitAll
	@RequestMapping(method = RequestMethod.GET, value = "/login")
    public void redirect(HttpServletResponse response) throws IOException{
		System.out.println("uso u kontroler");
        loginService.redirect(response);
    }

    @PermitAll
    @RequestMapping(method = RequestMethod.POST, value = "/login") 
    public void login(){
    	System.out.println(loginService.login());
    }

}
