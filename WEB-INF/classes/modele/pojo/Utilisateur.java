package modele.pojo;

public class Utilisateur {
	private int uno;
	private String login;
	private String email;
	private String pwd;
	private String nom;
	private String prenom;
	private String image;
	
	public Utilisateur(int uno, String login, String email, String pwd, String nom, String prenom, String image) {
		this.uno = uno;
		this.login = login;
		this.email = email;
		this.pwd = pwd;
		this.nom = nom;
		this.prenom = prenom;
		this.image = image;
	}
	

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email; 
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "Utilisateur [uno=" + uno + ", login=" + login + ", email=" + email + ", pwd=" + pwd + "]";
	}
	
}
