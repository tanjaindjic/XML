
package XmlWeb.config;

import XmlWeb.model.Korisnik;
import XmlWeb.model.security.Permission;
import XmlWeb.repository.KorisnikRepository;
import XmlWeb.security.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PermissionsInterceptor extends HandlerInterceptorAdapter {

	/*
	 * @Pointcut("execution(* *(..))") public void anyMethod() {
	 * System.out.println("POINTCUUUUUUUUUUUUUUUUUUT"); }
	 * 
	 * @Around("anyMethod() && @annotation(Read)") public Object invoke(final
	 * ProceedingJoinPoint pjp, final Read read) throws Throwable {
	 * System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaa");
	 * return pjp.proceed(); }
	 */

	@Autowired
	private KorisnikRepository korisnikRepository;

	private JwtTokenUtil jwtTokenUtil;
	static Logger logger = Logger.getLogger(PermissionsInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (handler.getClass() == org.springframework.web.method.HandlerMethod.class) {

			HandlerMethod method = (org.springframework.web.method.HandlerMethod) handler;
			if (method.hasMethodAnnotation(PermitAll.class)) {
				return true;
			} else if (method.hasMethodAnnotation(AdminRead.class)) {
				String requestHeader = request.getHeader("Authorization");
				String username = null;
				String authToken = null;
				if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
					authToken = requestHeader.substring(7);
					try {
						username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody()
								.get("sub", String.class);

						Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

						for (Permission p : k.getAuthorities().get(0).getPermissions()) {
							if (p.getName().equals("ADMINREAD")) {
								System.out.println("admin IMa permisiju Read");
								return true;
							}
						}
						System.out.println("admin nema permisiju Read");
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
			} else if (method.hasMethodAnnotation(AdminWrite.class)) {
				String requestHeader = request.getHeader("Authorization");
				String username = null;
				String authToken = null;
				if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
					authToken = requestHeader.substring(7);
					try {
						username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody()
								.get("sub", String.class);

						Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

						for (Permission p : k.getAuthorities().get(0).getPermissions()) {
							if (p.getName().equals("ADMINWRITE")) {
								System.out.println("admin IMa permisiju write");
								return true;
							}
						}
						System.out.println("admin nema permisiju write");
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

			else if (method.hasMethodAnnotation(AgentWrite.class)) {
				String requestHeader = request.getHeader("Authorization");
				String username = null;
				String authToken = null;
				if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
					authToken = requestHeader.substring(7);
					try {
						username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody()
								.get("sub", String.class);

						Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

						for (Permission p : k.getAuthorities().get(0).getPermissions()) {
							if (p.getName().equals("AGENTWRITE")) {
								System.out.println("agent IMa permisiju write");
								return true;
							}
						}
						System.out.println("agent nema permisiju write");
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

			else if (method.hasMethodAnnotation(AgentRead.class)) {
				String requestHeader = request.getHeader("Authorization");
				String username = null;
				String authToken = null;
				if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
					authToken = requestHeader.substring(7);
					try {
						username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody()
								.get("sub", String.class);

						Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

						for (Permission p : k.getAuthorities().get(0).getPermissions()) {
							if (p.getName().equals("AGENTREAD")) {
								System.out.println("agent IMa permisiju read");
								return true;
							}
						}
						System.out.println("agent nema permisiju read");
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
			} else if (method.hasMethodAnnotation(UserWrite.class)) {
				String requestHeader = request.getHeader("Authorization");
				String username = null;
				String authToken = null;
				if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
					authToken = requestHeader.substring(7);
					try {
						username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody()
								.get("sub", String.class);

						Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

						for (Permission p : k.getAuthorities().get(0).getPermissions()) {
							if (p.getName().equals("USERWRITE")) {
								System.out.println("user IMa permisiju write");
								return true;
							}
						}
						System.out.println("user nema permisiju write");
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

			else if (method.hasMethodAnnotation(UserRead.class)) {
				String requestHeader = request.getHeader("Authorization");
				String username = null;
				String authToken = null;
				if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
					authToken = requestHeader.substring(7);
					try {
						username = Jwts.parser().setSigningKey("mySecret").parseClaimsJws(authToken).getBody()
								.get("sub", String.class);

						Korisnik k = korisnikRepository.findByUsernameIgnoreCase(username);

						for (Permission p : k.getAuthorities().get(0).getPermissions()) {
							if (p.getName().equals("USERREAD")) {
								System.out.println("user IMa permisiju read");
								return true;
							}
						}
						System.out.println("user nema permisiju read");
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
		}

		return true;
	}
}
