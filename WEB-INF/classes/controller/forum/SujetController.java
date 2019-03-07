package controller.forum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.dao.*;
import modele.pojo.*;

@WebServlet("/Sujet")
public class SujetController extends HttpServlet {
	DS ds = DS.instance;
	ListeUtilisateurEquipeDAO util = new ListeUtilisateurEquipeDAO(ds);	
	SujetDAO suj = new SujetDAO(ds);
	ForumDAO forDAO = new ForumDAO(ds);
	RechercheDAO search = new RechercheDAO(ds);
	ListeUtilisateurEquipeDAO lu = new ListeUtilisateurEquipeDAO(ds);
	HashMap<String, String> resultat = new HashMap<>();

	public void service( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String vue = "WEB-INF/jsp/sujet/sujet.jsp";

		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		ArrayList<Sujet> listSujet = new ArrayList<>();
		Forum forum = forDAO.find(Integer.parseInt(req.getParameter("forum")));
		
		String recherche = req.getParameter("recherche");
		
		if (forum != null) {
			listSujet = (recherche != null ? search.rechercheSujet(recherche) : suj.findAllForum(Integer.parseInt(req.getParameter("forum"))));
			if (!forDAO.isAllowed(utilisateur, forum) && !util.utilisateurIsIn(utilisateur, "admin")) {
				res.sendRedirect("Forum?access=false");
				return;
			}
				
		} else {
			res.sendError(1, "Erreur sujetController");
		}
		
		if (req.getParameter("acces") != null) {
			resultat.put("accesNonAutorise", "<span class=\"text-danger\">Désolé mais vous n'avez pas acces à ce Sujet</span>");
		}
		
		req.setAttribute("forum", forum);
		req.setAttribute("listSujet", listSujet);
		req.setAttribute("resultat", resultat);
		req.getRequestDispatcher(vue).forward(req,res); 
	}
}
