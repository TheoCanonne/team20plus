package modele.pojo;

import java.util.ArrayList;
import java.util.List;

public class ListeUtilisateurEquipe {
	private Equipe equipe;
	private ArrayList<Utilisateur> listUtilisateur;
	
	public ListeUtilisateurEquipe(Equipe nomEquipe) {
		this.equipe = nomEquipe;
		this.listUtilisateur = new ArrayList<Utilisateur>();
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public ArrayList<Utilisateur> getUtilisateur() {
		return listUtilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		listUtilisateur.add(utilisateur);
	}

	public String toString() {
		return "ListeUtilisateurEquipe [nomEquipe=" + equipe.getNomEquipe() + ", utilisateur=" + listUtilisateur + "]";
	}

	
}
