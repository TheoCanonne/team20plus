package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.Sujet;
import modele.pojo.Utilisateur;

public class SujetDAO {
	DS ds;

	public SujetDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(Sujet sujet) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO Sujet VALUES (DEFAULT,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, sujet.getUtilisateur().getUno());
			ps.setInt(2, sujet.getForum().getFno());
			ps.setString(3, sujet.getTitre());
			ps.setString(4, sujet.getContenu());
			ps.setDate(5, sujet.getDate());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Sujet sujet) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE Sujet SET nomSujet=?, Utilisateurfk=?, Forumfk=?, Titre=?, Contenu=?, Jour=? Where Sujetno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, sujet.getSno());
			ps.setInt(2, sujet.getUtilisateur().getUno());
			ps.setInt(3, sujet.getForum().getFno());
			ps.setString(4, sujet.getTitre());
			ps.setString(5, sujet.getContenu());
			ps.setDate(6, sujet.getDate());
			ps.setInt(7, sujet.getSno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Sujet sujet) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM Sujet Where Sujetno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, sujet.getSno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Sujet find(int id) {
		Sujet sujet = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Sujet WHERE Sujetno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				ForumDAO f = new ForumDAO(ds);
				sujet = new Sujet(rs.getInt("Sujetno"),u.find(rs.getInt("Utilisateurfk")),f.find(rs.getInt("Forumfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sujet;
	}

	public ArrayList<Sujet> findAll(){
		ArrayList<Sujet> sujetList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Sujet";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				ForumDAO f = new ForumDAO(ds);
				sujetList.add(new Sujet(rs.getInt("Sujetno"),u.find(rs.getInt("Utilisateurfk")),f.find(rs.getInt("Forumfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sujetList;
	}

	public ArrayList<Sujet> findAllForum(int id){
		ArrayList<Sujet> sujetList = new ArrayList<>();
		UtilisateurDAO u = new UtilisateurDAO(ds);
		ForumDAO f = new ForumDAO(ds);
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Sujet Where Forumfk=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sujetList.add(new Sujet(rs.getInt("Sujetno"),u.find(rs.getInt("Utilisateurfk")),f.find(rs.getInt("Forumfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sujetList;
	}

	public boolean isAllowed(Utilisateur u, Sujet s) {

		try(Connection con = ds.getConnection()){
			ListeUtilisateurEquipeDAO lue = new ListeUtilisateurEquipeDAO(ds);
			String query = "Select Forumfk From ForumGroupe Where Forumfk=? AND Equipefk=? ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, s.getForum().getFno());
			ps.setString(2, (lue.getNomEquipe(u) == null ? "utilisateur ": lue.getNomEquipe(u)));
			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
