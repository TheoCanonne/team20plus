package modele.pojo;

public class Equipe {
	private String nomEquipe;
	
	public Equipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public String toString() {
		return "Equipe [nomEquipe=" + nomEquipe + "]";
	}
}
