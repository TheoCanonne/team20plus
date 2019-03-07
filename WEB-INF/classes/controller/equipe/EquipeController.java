package controller.equipe;

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

@WebServlet("/Equipe")
public class EquipeController extends HttpServlet {
	DS ds = DS.instance;
	ListeUtilisateurEquipeDAO lu = new ListeUtilisateurEquipeDAO(ds);


	public void service( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String vue = "WEB-INF/jsp/equipe/equipe.jsp";
		HashMap<String, String> resultat = new HashMap<>();
		ArrayList<Utilisateur> listUtilisateur = new ArrayList<>();
		
		
		listUtilisateur = lu.find("membre").getUtilisateur();
		System.out.println(listUtilisateur);
		
		req.setAttribute("listUtilisateur", listUtilisateur);
		req.setAttribute("resultat", resultat);
		req.getRequestDispatcher(vue).forward(req,res); 
	}
}
