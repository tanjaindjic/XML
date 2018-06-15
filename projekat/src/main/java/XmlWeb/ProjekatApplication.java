package XmlWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAsync
public class ProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatApplication.class, args);
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
	                registry.addMapping("/user").allowedOrigins("https://localhost:8090");
	                registry.addMapping("/requests/**/**/**")
	                .allowedHeaders("Content-Type, Access-Control-Allow-Origin, Access-Control-Allow-Headers")
	                .allowedOrigins("https://localhost:8090");
	                
	            }
	        };
	    }
}
