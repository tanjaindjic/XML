package XmlWeb.dto;

import java.util.Date;
import java.util.List;

import XmlWeb.model.Enums.StatusKorisnika;
import XmlWeb.model.security.Authority;


public class AgentRequestDTO {
	

	private Long korisnikId;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private Date lastPasswordResetDate;
	private boolean enabled;
	private String csr;
	private Long csrId;
	private String PIB;
	private String adresa;
	private StatusKorisnika statusNaloga;
	private List<Authority> authorities;
	public Long getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Long id) {
		this.korisnikId = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public StatusKorisnika getStatusNaloga() {
		return statusNaloga;
	}
	public void setStatusNaloga(StatusKorisnika statusNaloga) {
		this.statusNaloga = statusNaloga;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public String getCsr() {
		return csr;
	}
	public void setCsr(String csr) {
		this.csr = csr;
	}
	
	public AgentRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getPIB() {
		return PIB;
	}
	public void setPIB(String pIB) {
		PIB = pIB;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Long getCsrId() {
		return csrId;
	}
	public void setCsrId(Long csrId) {
		this.csrId = csrId;
	}

}
