package XmlWeb.dto;

public class RegisterDTO {
	
	private String username;
	private String password1;
	private String password2;
	private String email;
	private String firstname;
	private String lastname;
	private Boolean isAgent;
	private String pib;
	private String adresa;
	
	
	
	public RegisterDTO() {
		super();
	}
	public RegisterDTO(String username, String password1, String password2, String email, String firstname,
			String lastname, Boolean isAgent, String pib, String adresa) {
		super();
		this.username = username;
		this.password1 = password1;
		this.password2 = password2;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isAgent = isAgent;
		this.pib = pib;
		this.adresa=adresa;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Boolean isAgent() {
		return isAgent;
	}
	public void setIsAgent(Boolean isAgent) {
		this.isAgent = isAgent;
	}
	public String getPib() {
		return pib;
	}
	public void setPib(String pib) {
		this.pib = pib;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	

}
