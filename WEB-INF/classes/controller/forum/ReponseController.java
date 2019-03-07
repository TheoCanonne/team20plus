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

import modele.dao.DS;
import modele.dao.ListeUtilisateurEquipeDAO;
import modele.dao.ReponseDAO;
import modele.dao.SujetDAO;
import modele.pojo.Reponse;
import modele.pojo.Sujet;
import modele.pojo.Utilisateur;

@WebServlet("/Reponse")
public class ReponseController extends HttpServlet {
	DS ds = DS.instance;
	ReponseDAO rep = new ReponseDAO(ds);	
	SujetDAO suj = new SujetDAO(ds);
	ListeUtilisateurEquipeDAO lu = new ListeUtilisateurEquipeDAO(ds);
	

	public void service( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String vue = "WEB-INF/jsp/reponse/reponse.jsp";

		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		Sujet sujet = null;
		ArrayList<Reponse> listReponses = new ArrayList<>();
		
		HashMap<String, String> resultat = new HashMap<>();
		
		
		if (req.getParameter("sujet") != null) {
			sujet = suj.find(Integer.parseInt(req.getParameter("sujet")));
			
			if (!suj.isAllowed(utilisateur, sujet) && !lu.utilisateurIsIn(utilisateur, "admin")) {
				res.sendRedirect("Sujet?forum="+sujet.getForum().getFno()+"&access=false");
				return;
			}
					
			listReponses = rep.findAllSujet(sujet.getSno());
			
		} else {
			res.sendError(1, "Erreur sujetController");
		}
		
		
		req.setAttribute("reponses", listReponses);
		req.setAttribute("sujet", sujet);
		req.setAttribute("resultat", resultat);
		req.getRequestDispatcher(vue).forward(req,res); 
	}
}
