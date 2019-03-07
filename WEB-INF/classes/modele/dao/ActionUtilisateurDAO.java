package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.ActionUtilisateur;

public class ActionUtilisateurDAO {
	DS ds;

	public ActionUtilisateurDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(ActionUtilisateur actionUtilisateur) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO ActionUtilisateur VALUES (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, actionUtilisateur.getAno());
			ps.setInt(2, actionUtilisateur.getUtilisateur().getUno());
			ps.setString(3, actionUtilisateur.getDescription());
			ps.setDate(4, actionUtilisateur.getDate());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(ActionUtilisateur actionUtilisateur) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE ActionUtilisateur SET nomActionUtilisateur=?, Utilisateurfk=?, Titre=?, Contenu=?, Jour=? Where ActionUtilisateurno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, actionUtilisateur.getAno());
			ps.setInt(2, actionUtilisateur.getUtilisateur().getUno());
			ps.setString(3, actionUtilisateur.getDescription());
			ps.setDate(4, actionUtilisateur.getDate());
			ps.setInt(5, actionUtilisateur.getAno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(ActionUtilisateur actionUtilisateur) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM ActionUtilisateur Where Actionno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, actionUtilisateur.getAno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public ActionUtilisateur find(int id) {
		ActionUtilisateur actionUtilisateur = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM ActionUtilisateur WHERE Actionno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				actionUtilisateur = new ActionUtilisateur(rs.getInt("Actionno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Description"),rs.getDate("Jour"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionUtilisateur;
	}

	public List<ActionUtilisateur> findAll(){
		List<ActionUtilisateur> actionUtilisateurList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM ActionUtilisateur";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				actionUtilisateurList.add(new ActionUtilisateur(rs.getInt("Actionno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Description"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionUtilisateurList;
	}
}
