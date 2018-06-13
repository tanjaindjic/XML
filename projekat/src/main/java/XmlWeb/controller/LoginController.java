package XmlWeb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import XmlWeb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
    public void redirect(HttpServletResponse response) throws IOException{
		System.out.println("uso u kontroler");
        loginService.redirect(response);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/login") 
    public void login(){
    	System.out.println(loginService.login());
    }

}
