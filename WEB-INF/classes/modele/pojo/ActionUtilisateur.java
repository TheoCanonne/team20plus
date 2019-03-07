package modele.pojo;

import java.sql.Date;

public class ActionUtilisateur {
	private int ano;
	private Utilisateur utilisateur;
	private String Description;
	private Date date;
	
	public ActionUtilisateur(int ano, Utilisateur utilisateur, String description, Date date) {
		super();
		this.ano = ano;
		this.utilisateur = utilisateur;
		Description = description;
		this.date = date;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		return "ActionUtilisateur [ano=" + ano + ", utilisateur=" + utilisateur + ", Description=" + Description
				+ ", date=" + date + "]";
	}

}
