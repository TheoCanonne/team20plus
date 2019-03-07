package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.Forum;
import modele.pojo.Sujet;
import modele.pojo.Utilisateur;

public class ForumDAO {
	DS ds;

	public ForumDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(Forum forum) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO Forum VALUES (DEFAULT,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, forum.getUtilisateur().getUno());
			ps.setString(2, forum.getTitre());
			ps.setString(3, forum.getContenu());
			ps.setDate(4, forum.getDate());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Forum forum) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE Forum SET nomForum=?, Utilisateurfk=?, Titre=?, Contenu=?, Jour=? Where Forumno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, forum.getFno());
			ps.setInt(2, forum.getUtilisateur().getUno());
			ps.setString(3, forum.getTitre());
			ps.setString(4, forum.getContenu());
			ps.setDate(5, forum.getDate());
			ps.setInt(6, forum.getFno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Forum forum) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM Forum Where nomForum=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, forum.getFno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Forum find(int id) {
		Forum forum = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Forum WHERE Forumno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				forum = new Forum(rs.getInt("Forumno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forum;
	}

	public ArrayList<Forum> findAll(){
		ArrayList<Forum> forumList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Forum";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				if (rs.getString("Titre") != null)
					forumList.add(new Forum(rs.getInt("Forumno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forumList;
	}
	
	// A simplifié ?
	public ArrayList<Forum> listForumPrive(Utilisateur u) {
		ArrayList<Forum> res = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT Forumfk FROM ForumGroupe Where Equipefk IN "
					+ "(SELECT Nomequipefk FROM ListeUtilisateurEquipe WHERE Utilisateurfk=?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,u.getUno());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				res.add(this.find(rs.getInt("Forumfk")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<Forum> listForumGeneral() {
		ArrayList<Forum> res = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT Forumfk FROM ForumGroupe Where Equipefk ='utilisateur'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				res.add(this.find(rs.getInt("Forumfk")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
	public Forum findByText(String title, String description) {
		Forum f = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Forum WHERE Titre=? AND Contenu=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, description);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				f = new Forum(rs.getInt("Forumno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean isAllowed(Utilisateur u, Forum f) {

		try(Connection con = ds.getConnection()){
			ListeUtilisateurEquipeDAO lue = new ListeUtilisateurEquipeDAO(ds);
			String query = "Select * From ForumGroupe Where Forumfk=? AND Equipefk=? ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, f.getFno());
			ps.setString(2, (lue.getNomEquipe(u) == null ? "utilisateur ": lue.getNomEquipe(u)));
			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
