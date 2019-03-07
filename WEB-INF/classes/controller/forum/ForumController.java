package controller.forum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.dao.DS;
import modele.dao.*;
import modele.pojo.*;

@WebServlet("/Forum")
public class ForumController extends HttpServlet {
	DS ds = DS.instance;
	UtilisateurDAO util = new UtilisateurDAO(ds);	
	ForumDAO forum = new ForumDAO(ds);
	ListeUtilisateurEquipeDAO lu = new ListeUtilisateurEquipeDAO(ds);
	RechercheDAO search = new RechercheDAO(ds);


	public void service( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		String vue = "WEB-INF/jsp/forum/forum.jsp";

		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		HashMap<String, String> resultat = new HashMap<>();
		
		String recherche = req.getParameter("recherche");
		String acccesAutorise = req.getParameter("access");
		
		ArrayList<Forum> listG = null;
		ArrayList<Forum> listP  = null;
		
		listG = (recherche != null ? search.rechercheForumGeneral(recherche):forum.listForumGeneral());
		
		if (utilisateur != null && !lu.utilisateurIsIn(utilisateur, "utilisateur")) {
			listP = (recherche != null ? search.rechercheForumPrive(utilisateur, recherche) :forum.listForumPrive(utilisateur));

			if (lu.utilisateurIsIn(utilisateur, "admin")) {
				resultat.put("creerForum", "<a href=\"CreerForum\" class=\"btn btn-action\">Creer un forum<a>");
			}
		}
		
		if (acccesAutorise != null) {
			resultat.put("accesNonAutorise","<span class=\"text-danger\">Désolé mais vous n'avez pas acces à ce forum</span>");
		}

		req.setAttribute("listForumGeneral", listG);
		req.setAttribute("listForumPrive", listP);
		req.setAttribute("resultat", resultat);
		req.getRequestDispatcher(vue).forward(req,res); 
	}
}
