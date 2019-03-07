package modele.pojo;

import java.sql.Date;

public class Forum {
	private int fno;
	private Utilisateur utilisateur;
	private String titre;
	private String contenu;
	private Date date;
	
	public Forum(int fno, Utilisateur utilisateur, String titre, String contenu, Date date) {
		super();
		this.fno = fno;
		this.utilisateur = utilisateur;
		this.titre = titre;
		this.contenu = contenu;
		this.date = date;
	}

	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
		return "Forum [fno=" + fno + ", utilisateur=" + utilisateur + ", titre=" + titre + ", contenu=" + contenu
				+ ", date=" + date + "]";
	}
	
}
