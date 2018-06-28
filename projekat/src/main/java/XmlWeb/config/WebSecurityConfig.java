package XmlWeb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import XmlWeb.security.JwtAuthenticationEntryPoint;
import XmlWeb.security.JwtAuthorizationTokenFilter;
import XmlWeb.security.JwtTokenUtil;
import XmlWeb.service.JwtUserDetailsService;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


//KONFIGURISANO NA NIVOU ROLA, PERMSIJE ODRADJENE PREKO INTERCEPTORA I CUSTOM ANOTACIJA



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("/auth")
    private String authenticationPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtUserDetailsService)
            .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // we don't need CSRF because our token is invulnerable
            .csrf().disable()
       
            
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).anonymous()
            .antMatchers("/dtorequests").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE,"/requests/**/**/**").hasRole("ADMIN")
            .antMatchers("/user").hasAnyRole("ADMIN", "USER", "AGENT")
            .antMatchers("/user/**").hasAnyRole("ADMIN", "USER", "AGENT")
            .antMatchers("/user/block/**").hasRole("ADMIN")
            .antMatchers("/comments/**").hasRole("ADMIN")
            .antMatchers("/resources/**").permitAll()
            .antMatchers("**/**.js").permitAll()
            .antMatchers("/css/**", "/assets/**", "/images/**").permitAll()
            .antMatchers("/reservation/setBoolean/**").hasRole("ADMIN")
            .antMatchers("/certificates").hasRole("ADMIN")
            .antMatchers("/certificates/**/**").hasRole("ADMIN")
            .antMatchers("/h2-console/**/**").permitAll()
            .antMatchers("/uploadCert").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/services/**").permitAll()
        	.antMatchers("/user/**/resetPassword").permitAll()
        	.antMatchers("/user/resetToken/**").permitAll();
           

        // Custom JWT based security filter
        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
        httpSecurity
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity
            .headers()
            .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
            .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        web
            .ignoring()
            .antMatchers(
                HttpMethod.POST,
                authenticationPath, 
                "/register",
                "/api/smestaj/simplesearch",
                "/api/smestaj/advancedsearch",
                "/api/postFile",
                "/api/getallfiles",
                    "/requests/**/**/**",
                    "/certificates/**/**",
                    "/services/**",
                    "/user/resetToken/**"
            )

           .antMatchers(
                HttpMethod.DELETE // BUDZI GRBA

           )

            // allow anonymous resource requests
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.png",
                "/register", 
                "/success", 
                "/confirm/**",
                "/success/**",
                "/api/smestaj",
                "/api/iznajmljivanje",
                "/api/sobe",
                "/api/dodatneUsluge",
                "/api/tipService",
                "/api/kategorija",
                "/api/files/**", "/uploadCert",
                "/services/**",
                "/resources/**",
                "/user/**/resetPassword"
        
                
            )

            // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
            .and()
            .ignoring()
            .antMatchers("/h2-console/**/**", "/**/*.js" )
        
        .and()
        .ignoring()
        .antMatchers(HttpMethod.OPTIONS);
    }

    

}
