package modele.pojo;

import java.sql.Date;

public class Sujet {
	private int sno;
	private Utilisateur utilisateur;
	private Forum forum;
	private String titre;
	private String contenu;
	private Date date;
	
	public Sujet(int sno, Utilisateur utilisateur, Forum forum, String titre, String contenu, Date date) {
		this.sno = sno;
		this.utilisateur = utilisateur;
		this.forum = forum;
		this.titre = titre;
		this.contenu = contenu;
		this.date = date;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
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
		return "Sujet [sno=" + sno + ", utilisateur=" + utilisateur + ", forum=" + forum + ", titre=" + titre
				+ ", contenu=" + contenu + ", date=" + date + "]";
	}
	
	
}
