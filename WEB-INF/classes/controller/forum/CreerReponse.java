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

@WebServlet("/CreerReponse")
public class CreerReponse extends HttpServlet {

	private final String VUE = "Reponse";
	public static final String CHAMP_REPONSE  = "textReponse";
	public static final String CHAMP_SUJET   = "sujet";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";

	DS ds = DS.instance;
	ReponseDAO repDAO = new ReponseDAO(ds);
	SujetDAO suj = new SujetDAO(ds);

	public void doPost( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HashMap<String,String> erreurs = new HashMap<>();
		String resultat = "";
		HttpSession session = req.getSession(true);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		if (utilisateur == null) {
			res.sendRedirect("Accueil");
			return;

		}
		String reponse = req.getParameter(CHAMP_REPONSE);
		int sujet = Integer.parseInt(req.getParameter(CHAMP_SUJET));

		reponse = Jsoup.parse(reponse).text();

		try {
			validationReponse(reponse);

		} catch (Exception e) {
			erreurs.put(CHAMP_REPONSE,e.getMessage());
		}

		Reponse rep = new Reponse(0,utilisateur,suj.find(sujet),reponse,new Date(System.currentTimeMillis()));
		try {
			reponseExist(rep);
		}catch (Exception e) {
			erreurs.put(CHAMP_REPONSE,e.getMessage());
		}
		
		
		if (erreurs.isEmpty()) {
			resultat = "Votre réponse à bien été créé !";
			repDAO.insert(rep);
		}else {
			resultat = "Echec lors de la création de votre réponse!";
		}

		req.setAttribute("erreurs", erreurs);
		req.setAttribute("resultat", resultat);
		req.setAttribute("sujet", req.getParameter(CHAMP_SUJET));
		req.getRequestDispatcher(VUE).forward(req,res); 
	}

	private void validationReponse(String titre) throws Exception{
		if (titre.length() < 10) {
			throw new Exception("Le titre doit faire 10 caractères minimum");
		}
	}

	private void reponseExist(Reponse rep) throws Exception {
		if (repDAO.reponseExist(rep)) {
			throw new Exception("La réponse existe déjà !");
		}
	}
}