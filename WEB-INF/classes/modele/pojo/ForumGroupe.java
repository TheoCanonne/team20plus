package modele.pojo;

public class ForumGroupe {
	private String nomEquipe;
	private int forumfk;
	
	public ForumGroupe(String nomEquipe, int forumfk) {
		this.nomEquipe = nomEquipe;
		this.forumfk = forumfk;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public int getForumfk() {
		return forumfk;
	}

	public void setForumfk(int forumfk) {
		this.forumfk = forumfk;
	}
	
	
}
