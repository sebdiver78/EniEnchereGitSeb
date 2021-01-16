package fr.eni.enchere.servlets;

import java.io.IOException; 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAjout
 */
@WebServlet("/Ajout")
public class ServletAjout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("**********doGETAJOUT************");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ajoutDOPOST");
		Utilisateur utilisateur = new Utilisateur();
		String pseudo = request.getParameter("pseudo");
		String prenom = request.getParameter("prenom");
		String telephone = request.getParameter("telephone");
		String codePostal = request.getParameter("codePostal");
		String nom = request.getParameter("nom");
		String motDePasse = request.getParameter("motDePasse");
		String email = request.getParameter("email");
		String rue = request.getParameter("rue");
		String ville = request.getParameter("ville");
		String confirmation = request.getParameter("confirmation");
		
		System.out.println("pseudoSaisi = " + pseudo );
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		utilisateur = utilisateurManager.validerInsererUtilisateur(pseudo, prenom, telephone, codePostal, nom, motDePasse, email, rue, ville, confirmation);
		
		System.out.println(utilisateurManager.getResultat());
		
		Map<String, String> listeErreur = new HashMap<String, String>();
		listeErreur = utilisateurManager.getErreur();
		System.out.println(listeErreur);
		HttpSession session = request.getSession();
		System.out.println("servlet AJOUT USER avant request =" +utilisateur);
	
		
		if (utilisateurManager.getErreur().isEmpty()) {
			session.setAttribute("sessionUtilisateur", utilisateur);
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/AccueilSession");
			rd.forward(request, response);
			
		} else {
			session.setAttribute("sessionUtilisateur", null);
			request.setAttribute("form", utilisateurManager);
			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("erreurs", listeErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
			rd.forward(request, response);
			
		}
		
		
	}

}
