package modele.pojo;

import java.sql.Date;

public class Article {
	private int ano;
	private Utilisateur utilisateur;
	private String titre; // 300 caractère;
	private String contenu;
	private Date date;
	
	public Article(int ano, Utilisateur utilisateur, String titre, String contenu, Date date) {
		super();
		this.ano = ano;
		this.utilisateur = utilisateur;
		this.titre = titre;
		this.contenu = contenu;
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
		return "Article [ano=" + ano + ", utilisateur=" + utilisateur + ", titre=" + titre + ", contenu=" + contenu
				+ ", date=" + date + "]";
	}
	 
}
