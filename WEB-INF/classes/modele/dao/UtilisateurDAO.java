package modele.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.*;

public class UtilisateurDAO {
	DS ds;

	public UtilisateurDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(Utilisateur u) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO Utilisateur VALUES (DEFAULT,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, u.getLogin());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getNom());
			ps.setString(5, u.getPrenom());
			ps.setString(6, u.getImage());
			
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Utilisateur u) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE Utilisateur SET Utilisateurno=?,Login=?,pwd=?,email=?, Nom=?, Prenom=?, Image=? Where Utilisateurno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, u.getUno());
			ps.setString(2, u.getLogin());
			ps.setString(3, u.getPwd());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getNom());
			ps.setString(6, u.getPrenom());
			ps.setString(7, u.getImage());
			ps.setInt(8, u.getUno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Utilisateur u) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM Utilisateur Where Utilisateurno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, u.getUno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Utilisateur find(int id) {
		Utilisateur u = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Utilisateur WHERE Utilisateurno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				u = new Utilisateur(id,rs.getString("Login"),rs.getString("Email"),rs.getString("Password"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Image"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	public List<Utilisateur> findAll(){
		List<Utilisateur> uList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Utilisateur";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				uList.add(new Utilisateur(rs.getInt("Utilisateurno"),rs.getString("Login"),rs.getString("Email"),rs.getString("Password"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Image")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uList;
	}

	public Utilisateur connexion(String login, String password) {
		Utilisateur u = null;

		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Utilisateur WHERE Login=? AND Password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				u = find(rs.getInt("Utilisateurno"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}



		return u;
	}

	public boolean loginExiste(String login) {
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Utilisateur WHERE Login=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean emailExiste(String email) {
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Utilisateur WHERE Email=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Utilisateur findByLogin(String login) {
		Utilisateur u = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Utilisateur WHERE Login = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				u = new Utilisateur(rs.getInt("Utilisateurno"),rs.getString("Login"),rs.getString("Email"),rs.getString("Password"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("Image"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
