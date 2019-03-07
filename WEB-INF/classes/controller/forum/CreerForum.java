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

import modele.dao.DS;
import modele.dao.*;
import modele.pojo.*;

@WebServlet("/CreerForum")
public class CreerForum extends HttpServlet {

	private final String VUE = "WEB-INF/jsp/forum/creerForum.jsp";
	public static final String CHAMP_TITRE  = "titre";
	public static final String CHAMP_DESCRIPTION   = "description";
	public static final String CHAMP_PRIVATE    = "isPrivate";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";
	public static final String EQUIPE_BASE = "utilisateur";

	DS ds = DS.instance;
	ForumDAO forDAO = new ForumDAO(ds);

	public void doGet( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		if (utilisateur == null) {
			res.sendRedirect("Forum");
			return;
		}
		
		// req.setAttribute("errorLog", "<h3>Désole tu n'es pas connecté !</h3>");
		req.getRequestDispatcher(VUE).forward(req,res); 
	}

	public void doPost( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HashMap<String,String> erreurs = new HashMap<>();
		String resultat = "";
		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");


		String titre = req.getParameter(CHAMP_TITRE);
		String description = req.getParameter(CHAMP_DESCRIPTION);
		String isPrivate = req.getParameter(CHAMP_PRIVATE);

		System.out.println(titre);

		if (utilisateur == null) {
			res.sendRedirect("Acceuil");
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

		Forum tmp = new Forum(0,utilisateur,titre,description,new Date(System.currentTimeMillis()));
		if (forDAO.findByText(tmp.getTitre(), tmp.getContenu()) != null) {
			erreurs.put("alreadyExisting","Désolé mais ce Forum existe déjà.");
		}
		
		if (erreurs.isEmpty()) {
			resultat = "Votre forum à bien été créé !";
			forDAO.insert(tmp);
			ForumGroupeDAO fgDAO = new ForumGroupeDAO(ds);
			tmp = forDAO.findByText(tmp.getTitre(), tmp.getContenu());
			if (isPrivate != null) {
				
				fgDAO.insert("admin", tmp);
				fgDAO.insert("membre", tmp);
			} else {
				fgDAO.insert("utilisateur", tmp);
			}
			res.sendRedirect("Sujet?forum="+ tmp.getFno()+"&succesCreerForum=true");
			return;
		}else {
			resultat = "Echec lors de la création de votre forum !";
		}
		
		req.setAttribute("erreurs", erreurs);
		req.setAttribute("resultatCreerForum", resultat);
		req.getRequestDispatcher(VUE).forward(req,res); 
	}

	private void validationTitre(String titre) throws Exception{
		if (titre.length() < 5 || titre.length() > 300) {
			throw new Exception("Le titre doit faire 5 caractères minimum et 300 maximum");
		}
	}

	private void validationDescription(String description) throws Exception{
		if (description.length() < 10) {
			throw new Exception("Le titre doit faire 10 caractères minimum");
		}
	}

}


