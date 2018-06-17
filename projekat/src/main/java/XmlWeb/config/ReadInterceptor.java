package XmlWeb.config;

import XmlWeb.controller.RequestController;
import XmlWeb.model.Korisnik;
import XmlWeb.model.security.Permission;
import XmlWeb.repository.KorisnikRepository;
import XmlWeb.security.JwtTokenUtil;
import XmlWeb.service.KorisnikService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.DecoderException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.misc.BASE64Decoder;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
@Component
public class ReadInterceptor extends HandlerInterceptorAdapter {
   /* @Pointcut("execution(* *(..))")
    public void anyMethod() {
        System.out.println("POINTCUUUUUUUUUUUUUUUUUUT");
    }

    @Around("anyMethod() && @annotation(Read)")
    public Object invoke(final ProceedingJoinPoint pjp, final Read read) throws Throwable {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaa");
        return  pjp.proceed();
    }*/

    @Autowired
    private KorisnikRepository korisnikRepository;

    private JwtTokenUtil jwtTokenUtil;
    static Logger logger = Logger.getLogger(ReadInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
       if(handler.getClass()==org.springframework.web.method.HandlerMethod.class){

            HandlerMethod method = (org.springframework.web.method.HandlerMethod) handler;
            if (method.hasMethodAnnotation(Read.class)) {
                String requestHeader = request.getHeader("Authorization");
                String username = null;
                String authToken = null;
                if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                    authToken = requestHeader.substring(7);
                    try {
                        username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody().get("sub", String.class);

                        Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

                        for (Permission p : k.getAuthorities().get(0).getPermissions()) {
                            if (p.getName().equals("READ")) {
                                System.out.println("IMa permisiju Read");
                                return true;
                            }
                        }
                        System.out.println("Nema permisiju Read");
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                        return false;

                    } catch (IOException e) {
                        logger.error("an error occured during response.sendError", e);
                    } catch (IllegalArgumentException e) {
                        logger.error("an error occured during getting username from token", e);
                    } catch (ExpiredJwtException e) {
                        logger.warn("the token is expired and not valid anymore", e);
                    }
                } else {
                    logger.warn("couldn't find bearer string, will ignore the header");
                }
            }
        }else {
                System.out.println("handler nije HandlerMehtod nego " + handler.getClass());
            }

        return true;
    }
}