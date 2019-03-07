package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modele.dao.DS;
import modele.dao.UtilisateurDAO;
import modele.pojo.Utilisateur;

@WebServlet("/Login")
public class Login extends HttpServlet {
	public void service( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String vue = "WEB-INF/jsp/login/login.jsp";

		HttpSession session = req.getSession(true);
		Utilisateur log = (Utilisateur) session.getAttribute("utilisateur");

		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String erreurLog = null;
		
		if (log == null) {
			vue = "WEB-INF/jsp/login/login.jsp";

			if (login != null && password != null) {
				
				Utilisateur u = login(login,password);
				if (u != null) {
					session.setAttribute("utilisateur", u);
					res.sendRedirect("Accueil");
					return;
				} else {
					erreurLog = "Nom de compte ou mot de passe invalide, veuillez réessayer.";
				}		
			}
		} else {
			
			if (req.getParameter("deco") == null) {
				vue = "WEB-INF/jsp/login/compte.jsp";
			}else {
				session.setAttribute("utilisateur", null);
				res.sendRedirect("Accueil");
				return;
			}
		}
		req.setAttribute("erreurLog", erreurLog);
		req.getRequestDispatcher(vue).forward(req,res); 
	}

	public Utilisateur login(String login, String password) {
		DS ds = DS.instance;
		UtilisateurDAO util = new UtilisateurDAO(ds);	
		return util.connexion(login, password);
	}
}
