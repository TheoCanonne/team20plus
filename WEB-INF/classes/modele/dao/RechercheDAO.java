package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modele.pojo.*;

public class RechercheDAO {
	DS ds;

	public RechercheDAO(DS ds) {
		this.ds = ds;
	}

	public ArrayList<Forum> rechercheForumPrive(Utilisateur u, String text){

		ArrayList<Forum> f = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT DISTINCT * FROM Forum WHERE (Titre LIKE ? OR Contenu LIKE ?) AND Forumno IN"
					+"(SELECT Forumfk FROM ForumGroupe Where Equipefk IN "
					+ "(SELECT DISTINCT Nomequipefk FROM ListeUtilisateurEquipe WHERE Utilisateurfk=?))";


			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,"%"+ text +"%");
			ps.setString(2, "%"+ text +"%");
			ps.setInt(3, u.getUno());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO util = new UtilisateurDAO(ds);
				f.add(new Forum(rs.getInt("Forumno"),util.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	
			System.out.println(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public ArrayList<Forum> rechercheForumGeneral(String text){

		ArrayList<Forum> f = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Forum WHERE (Titre LIKE ? OR Contenu LIKE ?) AND Forumno IN"
					+"(SELECT Forumfk FROM ForumGroupe Where Equipefk='utilisateur')";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,"%"+ text +"%");
			ps.setString(2, "%"+ text +"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO util = new UtilisateurDAO(ds);
				f.add(new Forum(rs.getInt("Forumno"),util.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}


	public ArrayList<Sujet> rechercheSujet(String text){

		ArrayList<Sujet> f = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Sujet WHERE Titre LIKE ? OR Contenu LIKE ?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,"%"+ text +"%");
			ps.setString(2, "%"+ text +"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO util = new UtilisateurDAO(ds);
				ForumDAO forDAO = new ForumDAO(ds);
				f.add(new Sujet(rs.getInt("Sujetno"),util.find(rs.getInt("Utilisateurfk")),forDAO.find(rs.getInt("Forumfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}
}
