package modele.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.*;

public class EquipeDAO {
	DS ds;

	public EquipeDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(Equipe equipe) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO Equipe VALUES (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, equipe.getNomEquipe());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Equipe equipe,String newNom) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE Equipe SET nomEquipe=? Where Equipeno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, newNom);
			ps.setString(2, equipe.getNomEquipe());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Equipe equipe) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM Equipe Where nomEquipe=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, equipe.getNomEquipe());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Equipe find(String id) {
		Equipe equipe = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Equipe WHERE Nomequipe=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				equipe = new Equipe(rs.getString("nomEquipe"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipe;
	}

	public List<Equipe> findAll(){
		List<Equipe> equipeList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Equipe";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				equipeList.add(new Equipe(rs.getString("nomEquipe")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipeList;
	}


}

