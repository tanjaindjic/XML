package XmlWeb;

import XmlWeb.config.Read;
import XmlWeb.controller.RequestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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

	                registry.addMapping("/requests/**/**/**")
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

				}
	        };
	    }


}
