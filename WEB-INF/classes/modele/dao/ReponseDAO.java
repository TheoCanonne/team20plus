package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.Reponse;

public class ReponseDAO {
	DS ds;

	public ReponseDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(Reponse reponse) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO Reponse VALUES (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, reponse.getRno());
			ps.setInt(2, reponse.getUtilisateur().getUno());
			ps.setInt(3, reponse.getSujet().getSno());
			ps.setString(4, reponse.getContenu());
			ps.setDate(5, reponse.getDate());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Reponse reponse) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE Reponse SET nomReponse=?, Utilisateurfk=?, Titre=?, Contenu=?, Jour=? Where Reponseno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, reponse.getRno());
			ps.setInt(2, reponse.getUtilisateur().getUno());
			ps.setInt(3, reponse.getSujet().getSno());
			ps.setString(4, reponse.getContenu());
			ps.setDate(5, reponse.getDate());
			ps.setInt(6, reponse.getRno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Reponse reponse) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM Reponse Where Reponseno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, reponse.getRno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Reponse find(int id) {
		Reponse reponse = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Reponse WHERE Reponseno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				SujetDAO s = new SujetDAO(ds);
				reponse = new Reponse(rs.getInt("Reponseno"),u.find(rs.getInt("Utilisateurfk")),s.find(rs.getInt("Sujetfk")),rs.getString("Contenu"),rs.getDate("Jour"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse;
	}

	public List<Reponse> findAll(){
		List<Reponse> reponseList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Reponse";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				SujetDAO s = new SujetDAO(ds);
				reponseList.add(new Reponse(rs.getInt("Reponseno"),u.find(rs.getInt("Utilisateurfk")),s.find(rs.getInt("Sujetfk")),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponseList;
	}

	public ArrayList<Reponse> findAllSujet(int id){
		ArrayList<Reponse> reponse = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Reponse WHERE Sujetfk = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			UtilisateurDAO u = new UtilisateurDAO(ds);
			SujetDAO s = new SujetDAO(ds);
			while(rs.next()) {
				
				reponse.add(new Reponse(rs.getInt("Reponseno"),u.find(rs.getInt("Utilisateurfk")),s.find(rs.getInt("Sujetfk")),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reponse;
	}

	public boolean reponseExist(Reponse rep) {
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Reponse Where Sujetfk=? AND Contenu=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, rep.getSujet().getSno());
			ps.setString(2, rep.getContenu());
			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
