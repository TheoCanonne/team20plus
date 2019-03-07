package controller.forum;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;

import modele.dao.DS;
import modele.dao.*;
import modele.pojo.*;


@WebServlet("/CreerSujet")
public class CreerSujet extends HttpServlet {

	private final String VUE = "WEB-INF/jsp/sujet/creerSujet.jsp";
	public static final String CHAMP_TITRE  = "titre";
	public static final String CHAMP_DESCRIPTION   = "description";
	public static final String FORUM    = "forum";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";
	public static final String EQUIPE_BASE = "utilisateur";

	DS ds = DS.instance;
	ForumDAO forDAO = new ForumDAO(ds);

	public void doGet( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		if (utilisateur == null ||req.getParameter("forum") == null) {
			res.sendRedirect("Accueil");
			return;
		}
		req.setAttribute("forum", forDAO.find(Integer.parseInt(req.getParameter("forum"))));
		req.getRequestDispatcher(VUE).forward(req,res); 
	}

	public void doPost( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HashMap<String,String> erreurs = new HashMap<>();
		String resultat = "";
		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");


		String titre = req.getParameter(CHAMP_TITRE);
		String description = req.getParameter(CHAMP_DESCRIPTION);
		Forum forum = forDAO.find(Integer.parseInt(req.getParameter("forum")));

		titre = Jsoup.parse(titre).text();

		if (utilisateur == null) {
			res.sendRedirect("Accueil");
			return;
		}
		try {
			validationTitre(titre);

		} catch (Exception e) {
			erreurs.put(CHAMP_TITRE,e.getMessage());
		}

		try {
			validationDescription(description);
		} catch (Exception e) {
			erreurs.put(CHAMP_DESCRIPTION,e.getMessage());
		}

		try {
			validationForum(forum);
		} catch (Exception e) {
			erreurs.put(FORUM,e.getMessage());
		}
		if (erreurs.isEmpty()) {
			resultat = "Votre sujet à bien été créé !";
			SujetDAO sujet = new SujetDAO(ds);
			sujet.insert(new Sujet(0,utilisateur,forum,titre,description,new Date(System.currentTimeMillis())));
		}else {
			resultat = "Echec lors de la création de votre Sujet !";
		}
		req.setAttribute("erreurs", erreurs);
		req.setAttribute("resultat", resultat);
		req.setAttribute("forum", forum);
		req.getRequestDispatcher(VUE).forward(req,res); 
	}

	private void validationTitre(String titre) throws Exception{
		if (titre.length() < 5 || titre.length() > 300) {
			throw new Exception("Le titre doit faire 5 caractères minimum et 300 maximum");
		}
	}

	private void validationDescription(String description) throws Exception{
		if (description.length() < 10) {
			throw new Exception("La description doit faire 10 caractères minimum");
		}
	}

	private void validationForum(Forum forum) throws Exception {
		if (forum == null)
			throw new Exception("Vous n'avez pas choisie de Forum veuillez retourner à la liste des sujets.");
	}
}



