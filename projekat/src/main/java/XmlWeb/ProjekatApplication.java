package XmlWeb;

import javax.xml.ws.Endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import XmlWeb.soapServices.impl.SmestajSoapImpl;

@SpringBootApplication
@EnableAsync
public class ProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatApplication.class, args);
		Endpoint.publish("http://localhost:4789/main/java/XmlWeb/soapServices/impl/smestajSoapImpl", new SmestajSoapImpl());
	/*	AdminService agentService = new AdminServiceLocator();
		try {
			AgentWs ad = agentService.getAdminPort();
			System.out.println("radiiiiiiiiiiiiiiiiiiiiiii");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	  @SuppressWarnings("deprecation")
	@Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	            	registry.addMapping("/**")
	                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");
	                registry.addMapping("/auth").allowedOrigins("https://localhost:8090");

	                registry.addMapping("/dtorequests")
	                .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
	                .allowedOrigins("https://localhost:8090");

	                registry.addMapping("/requests/**/**/**")
					.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
	                .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
	                .allowedOrigins("https://localhost:8090");

	                registry.addMapping("/register/admin")
	                .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
	                .allowedOrigins("https://localhost:8090");

	                registry.addMapping("/user")
	                .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
	                .allowedOrigins("https://localhost:8090");

	                registry.addMapping("/user/block/**")
	                .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
	                .allowedOrigins("https://localhost:8090");

					registry.addMapping("/comments/**")
							.allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
							.allowedOrigins("https://localhost:8090");

                    registry.addMapping("/api/tipService/**")
                            .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
                            .allowedOrigins("https://localhost:8090");

					registry.addMapping("/api/kategorija/**")
							.allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
							.allowedOrigins("https://localhost:8090");

					registry.addMapping("/api/dodatneUsluge/**")
							.allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
							.allowedOrigins("https://localhost:8090");

					registry.addMapping("/certificates/**/**")
							.allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
							.allowedOrigins("https://localhost:8090");
					registry.addMapping("/certificates")
							.allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
							.allowedOrigins("https://localhost:8090");


				}
	        };
	    }


}
