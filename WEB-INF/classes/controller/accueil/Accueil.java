package controller.accueil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import modele.pojo.Utilisateur;

@WebServlet(urlPatterns = {"/Accueil",""})
public class Accueil extends HttpServlet {
	public void service( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String vue = "WEB-INF/jsp/index.jsp";
		
		req.getRequestDispatcher(vue).forward(req,res); 
	}

}