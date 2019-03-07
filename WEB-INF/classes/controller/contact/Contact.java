package controller.contact;

import java.io.IOException;
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

@WebServlet("/Contact")
public class Contact extends HttpServlet {

	private final String VUE = "WEB-INF/jsp/contact/contact.jsp";
	public static final String CHAMP_CIVILITE  = "civilite";
	public static final String CHAMP_EMAIL  = "email";
	public static final String CHAMP_NOM  = "nom";
	public static final String CHAMP_PRENOM   = "prenom";
	public static final String CHAMP_QUESTION    = "question";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";

	DS ds = DS.instance;
	UtilisateurDAO util = new UtilisateurDAO(ds);
	
	public void doGet( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		req.getRequestDispatcher(VUE).forward(req,res); 
	}
	
	public void doPost( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String resultat;
		HashMap<String,String> erreurs = new HashMap<>();

		String nom = req.getParameter(CHAMP_NOM);
		String prenom = req.getParameter(CHAMP_PRENOM);
		String civilite = req.getParameter(CHAMP_CIVILITE);
		String email = req.getParameter(CHAMP_EMAIL);
		String question = req.getParameter(CHAMP_QUESTION);

		try {
			validationEmail( email );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_EMAIL, e.getMessage() );
		}


		try {
			validationNom( nom);
		} catch ( Exception e ) {
			erreurs.put( CHAMP_NOM, e.getMessage() );
		}


		try {
			validationPrenom( prenom );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_PRENOM, e.getMessage() );
		}
		
		try {
			validationQuestion( question );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_QUESTION, e.getMessage() );
		}
		
		try {
			validationCivilite( civilite );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_CIVILITE, e.getMessage() );
		}


		if ( erreurs.isEmpty() ) {
			this.sendEmail(nom,prenom,email,civilite,question);
			resultat = "Le message a bien été transmis à la team 20+, merci !";
		} else {
			resultat = "Échec de l'envoie du message.";
		}


		req.setAttribute( ATT_ERREURS, erreurs );
		req.setAttribute( ATT_RESULTAT, resultat );

		req.getRequestDispatcher(VUE).forward(req,res); 
	}
	
	private void validationEmail( String email ) throws Exception {
		if ( email != null && email.trim().length() != 0 ) {
			if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new Exception( "Merci de saisir une adresse mail valide." );
			}
		} else {
			throw new Exception( "Merci de saisir une adresse mail." );
		}
	}


	private void validationNom( String nom ) throws Exception{
		if (nom.equals("") ) {
			throw new Exception( "Le nom ne peut pas être vide." );
		}
	}

	
	private void validationPrenom( String prenom ) throws Exception {
		if (prenom.equals("")) {
			throw new Exception( "Le prénom ne peut pas être vide." );
		}
	}
	
	private void validationQuestion( String question ) throws Exception {
		if (question.length() < 10) {
			throw new Exception( "La question doit faire au minimum 10 caractères." );
		}
	}
	
	private void validationCivilite( String civilite ) throws Exception {
		if (civilite == null ) {
			throw new Exception( "Veuillez choisir votre civilité." );
		}
	}
	
	private void sendEmail(String nom,String prenom,String email,String civilite,String question) {
		String subject = "(CONTACT) Message de " + email;
		String body = "<h1>" + civilite + "." + nom + " " + prenom + "</h1>";
		body += "<p>" + question + "</p>" + "<p> Son email : " + email + "</p>";
		SendEmail.send("team20plus.contact@gmail.com", subject, body, "team20plus.contact@gmail.com", "Team20plus");
	}
}