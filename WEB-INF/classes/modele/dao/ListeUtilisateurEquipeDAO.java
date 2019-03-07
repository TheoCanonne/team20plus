package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.ListeUtilisateurEquipe;
import modele.pojo.Utilisateur;

public class ListeUtilisateurEquipeDAO {
	DS ds;

	public ListeUtilisateurEquipeDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(String nomEquipe, Utilisateur u) {
		EquipeDAO equipe = new EquipeDAO(ds);
		if (equipe.find(nomEquipe) == null)
			return false;

		UtilisateurDAO util = new UtilisateurDAO(ds);
		if (util.find(u.getUno()) == null)
			return false;

		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO ListeUtilisateurEquipe VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, nomEquipe);
			ps.setInt(2, u.getUno());
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


	public boolean delete(ListeUtilisateurEquipe listeUtilisateurEquipe) {
		try(Connection con = ds.getConnection()){

			for(Utilisateur u: listeUtilisateurEquipe.getUtilisateur()) {
				String query = "DELETE FROM ListeUtilisateurEquipe Where nomEquipefk=? AND Utilisateurfk=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, listeUtilisateurEquipe.getEquipe().getNomEquipe());
				ps.setInt(2, u.getUno());
				ps.executeUpdate();	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

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
		if (u == null)
			return nomEquipe.equals("utilisateur");
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

	public String getNomEquipe(Utilisateur u) {
		if (u == null)
			return "utilisateur";
		try(Connection con = ds.getConnection()){

			String query = "SELECT Nomequipefk FROM ListeUtilisateurEquipe WHERE Utilisateurfk=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, u.getUno());
			ResultSet rs = ps.executeQuery();
			
			
			rs.next();
			
			return rs.getString("Nomequipefk");	

		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
	}

}
