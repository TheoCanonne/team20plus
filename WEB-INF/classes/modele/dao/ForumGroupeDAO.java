package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.Forum;
import modele.pojo.ForumGroupe;
import modele.pojo.ListeUtilisateurEquipe;
import modele.pojo.Utilisateur;

public class ForumGroupeDAO {
	DS ds;

	public ForumGroupeDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(String nomEquipe, Forum f) {
		EquipeDAO equipe = new EquipeDAO(ds);
		if (equipe.find(nomEquipe) == null)
			return false;

		ForumDAO util = new ForumDAO(ds);
		if (util.find(f.getFno()) == null)
			return false;

		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO ForumGroupe VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, nomEquipe);
			ps.setInt(2, f.getFno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// A FAIRE
	public boolean update(String nomEquipe, Utilisateur u) {
		EquipeDAO equipe = new EquipeDAO(ds);
		if (equipe.find(nomEquipe) == null)
			return false;

		try(Connection con = ds.getConnection()){

			String query = "UPDATE ListeUtilisateurEquipe SET nomEquipe=?, Utilisateurno=? WHERE nomEquipefk=? AND Utilisateurfk=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	public boolean delete(ForumGroupe fg) {
		try(Connection con = ds.getConnection()){

				String query = "DELETE FROM ForumGroupe Where Equipefk=? AND Forumfk=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, fg.getNomEquipe());
				ps.setInt(2, fg.getForumfk());
				ps.executeUpdate();	


		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	// A FAIRE
	public ListeUtilisateurEquipe find(String id) {
		ListeUtilisateurEquipe listeUtilisateurEquipe = null;
		EquipeDAO equipe = new EquipeDAO(ds);
		UtilisateurDAO utilisateur = new UtilisateurDAO(ds);
		
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM ListeUtilisateurEquipe WHERE NomEquipefk = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (listeUtilisateurEquipe == null)
					listeUtilisateurEquipe = new ListeUtilisateurEquipe(equipe.find(id));

				listeUtilisateurEquipe.setUtilisateur(utilisateur.find(rs.getInt("Utilisateurfk")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeUtilisateurEquipe;
	}

	// A FAIRE
	public List<ListeUtilisateurEquipe> findAll(){
		List<ListeUtilisateurEquipe> listeUtilisateurEquipeList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM ListeUtilisateurEquipe";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				SujetDAO s = new SujetDAO(ds);
				
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeUtilisateurEquipeList;
	}
	
	public boolean utilisateurIsIn(Utilisateur u, String nomEquipe) {
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM ListeUtilisateurEquipe WHERE Utilisateurfk=? AND nomEquipefk=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, u.getUno());
			ps.setString(2, nomEquipe);
			ResultSet rs = ps.executeQuery();

			return rs.next();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
