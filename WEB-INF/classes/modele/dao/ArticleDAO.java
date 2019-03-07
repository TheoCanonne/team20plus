package modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modele.pojo.Article;

public class ArticleDAO {
	DS ds;

	public ArticleDAO(DS ds) {
		this.ds = ds;
	}

	public boolean insert(Article article) {
		try(Connection con = ds.getConnection()){

			String query = "INSERT INTO Article VALUES (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, article.getAno());
			ps.setInt(2, article.getUtilisateur().getUno());
			ps.setString(3, article.getTitre());
			ps.setString(4, article.getContenu());
			ps.setDate(5, article.getDate());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean update(Article article) {
		try(Connection con = ds.getConnection()){

			String query = "UPDATE Article SET nomArticle=?, Utilisateurfk=?, Titre=?, Contenu=?, Jour=? Where Articleno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, article.getAno());
			ps.setInt(2, article.getUtilisateur().getUno());
			ps.setString(3, article.getTitre());
			ps.setString(4, article.getContenu());
			ps.setDate(5, article.getDate());
			ps.setInt(6, article.getAno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete(Article article) {
		try(Connection con = ds.getConnection()){

			String query = "DELETE FROM Article Where Articleno=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, article.getAno());
			ps.executeUpdate();	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public Article find(int id) {
		Article article = null;
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Article WHERE Articleno = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				article = new Article(rs.getInt("Articleno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour"));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	public List<Article> findAll(){
		List<Article> articleList = new ArrayList<>();
		try(Connection con = ds.getConnection()){

			String query = "SELECT * FROM Article";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				UtilisateurDAO u = new UtilisateurDAO(ds);
				articleList.add(new Article(rs.getInt("Articleno"),u.find(rs.getInt("Utilisateurfk")),rs.getString("Titre"),rs.getString("Contenu"),rs.getDate("Jour")));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
}
