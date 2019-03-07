package controller.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import modele.dao.DS;
import modele.dao.*;
import modele.pojo.*;

@WebServlet("/Enregistrement")
@MultipartConfig
public class Enregistrement extends HttpServlet {

	private final String VUE = "WEB-INF/jsp/login/enregistrement.jsp";
	public static final String CHAMP_EMAIL  = "email";
	public static final String CHAMP_PWD    = "pwd";
	public static final String CHAMP_CONF   = "confirm";
	public static final String CHAMP_LOG    = "login";
	public static final String CHAMP_NOM    = "nom";
	public static final String CHAMP_PRENOM = "prenom";
	public static final String CHAMP_IMAGE    = "image";
	public static final String ATT_ERREURS  = "erreurs";
	public static final String ATT_RESULTAT = "resultat";
	public static final String EQUIPE_BASE = "utilisateur";
	DS ds = DS.instance;
	UtilisateurDAO util = new UtilisateurDAO(ds);

	public void doGet( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		Utilisateur log = (Utilisateur) session.getAttribute("utilisateur");


		if(log != null)
			res.sendRedirect("Accueil");


		req.getRequestDispatcher(VUE).forward(req,res); 
	}
	public void doPost( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String resultat;
		HashMap<String,String> erreurs = new HashMap<>();

		HttpSession session = req.getSession(true);
		Utilisateur log = (Utilisateur) session.getAttribute("utilisateur");

		String login = req.getParameter(CHAMP_LOG);
		String pwd = req.getParameter(CHAMP_PWD);
		String confirmation = req.getParameter(CHAMP_CONF);
		String email = req.getParameter(CHAMP_EMAIL);
		String nom = req.getParameter(CHAMP_NOM);
		String prenom = req.getParameter(CHAMP_PRENOM);
		Part image = req.getPart(CHAMP_IMAGE);

		if(log != null)
			res.sendRedirect("Accueil");

		
		try {
			validationEmail( email );
			emailExiste( email );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_EMAIL, e.getMessage() );
		}


		try {
			validationMotsDePasse( pwd, confirmation );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_PWD, e.getMessage() );
		}


		try {
			validationLogin( login );
			loginExiste( login );
		} catch ( Exception e ) {
			erreurs.put( CHAMP_LOG, e.getMessage() );
		}

		try {
			validationImage(image.getName());
		} catch ( Exception e ) {
			erreurs.put( CHAMP_IMAGE, e.getMessage() );
		}


		if ( erreurs.isEmpty() ) {
			resultat = "Succès de l'inscription.";
			String path = saveImage(image, login, image.getHeader("content-type").split("/")[1]);
			Utilisateur u = ajoutUtilisateur(login, pwd, email, nom, prenom, path);
			u = util.findByLogin(login);
			session.setAttribute("utilisateur",u );
			this.ajoutGroupe(u);
			res.sendRedirect("Accueil");
			return;
		} else {
			resultat = "Échec de l'inscription.";
		}


		req.setAttribute( ATT_ERREURS, erreurs );
		req.setAttribute( ATT_RESULTAT, resultat );

		req.getRequestDispatcher(VUE).forward(req,res); 
	}

	private void ajoutGroupe(Utilisateur u) {
		DS ds = DS.instance;
		ListeUtilisateurEquipeDAO dao = new ListeUtilisateurEquipeDAO(ds);
		dao.insert(EQUIPE_BASE, u);
	}
	private void loginExiste(String login) throws Exception {
		if(util.loginExiste(login))
			throw new Exception("Ce nom d'utilisateur existe déjà, merci d'en choisir un autre.");
	}

	private void emailExiste(String email) throws Exception{
		if(util.emailExiste(email))
			throw new Exception("Cette adresse email est déjà utilisé, merci d'en utlisé une autre.");
	}
	private Utilisateur ajoutUtilisateur(String login, String pwd, String email, String nom, String prenom, String image) {
		Utilisateur u = new Utilisateur(0,login,email,pwd, nom, prenom, image);
		util.insert(u);
		return u;
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


	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
		if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motDePasse.trim().length() < 8) {
				throw new Exception("Les mots de passe doivent contenir au moins 8 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}


	private void validationLogin( String nom ) throws Exception {
		if ( nom == null || nom.trim().length() < 5 ) {
			throw new Exception( "Le nom d'utilisateur doit contenir au moins 5 caractères." );
		}
	}

	private void validationImage(String name) throws Exception {
		if( !name.contains("image") && name.equals("")) {
			throw new Exception("Veuillez choisir une image.");
		}
	}

	private String saveImage(Part image, String login,String type) {
		String path = "../webapps/Sitedepeche/data/image/utilisateur/" +login+"."+type;
		String res = "data/image/utilisateur/"+login+"."+type;
		OutputStream out;
		InputStream file;	
		
		try {
			File f = new File(path);
			f.createNewFile();
			 out = new FileOutputStream(f);
			 file = image.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = file.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			out.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}