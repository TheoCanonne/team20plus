package modele.pojo;

import java.sql.Date;

public class Reponse {
	private int rno;
	private Utilisateur utilisateur;
	private Sujet sujet;
	private String contenu;
	private Date date;
	
	public Reponse(int rno, Utilisateur utilisateur, Sujet sujet, String contenu, Date date) {
		super();
		this.rno = rno;
		this.utilisateur = utilisateur;
		this.sujet = sujet;
		this.contenu = contenu;
		this.date = date;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		return "Reponse [rno=" + rno + ", utilisateur=" + utilisateur + ", sujet=" + sujet + ", contenu=" + contenu
				+ ", date=" + date + "]";
	}

	
}
