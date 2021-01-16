package fr.eni.enchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletAffichage
 */
@WebServlet(
		urlPatterns= {
				"/Profil",
				"/Update",
				"/Delete",
				"/Accueil",
				"/ProfilUser"
				
		})


public class ServletGestionProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/Profil")) {
			System.out.println("*********/PROFIL************");
			Utilisateur utilisateur;
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			int idUser=0;
			if (request.getParameter("id") != null) {
				idUser = Integer.parseInt(request.getParameter("id"));
				
			}
			utilisateur = utilisateurManager.selectUtilisateurId(idUser);
			System.out.println("*************************");
			System.out.println("-------SERVLETAFFICHAGEPROFIL-----------");
			System.out.println("****utilisateur =" + utilisateur.toString());
			System.out.println("*******************");
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/restreint/MonProfil.jsp");
			rd.forward(request, response);
			
		} else if (request.getServletPath().contentEquals("/Delete")) {
			System.out.println("Delete");
			
			int idUser=0;
			if (request.getParameter("id") != null) {
				idUser = Integer.parseInt(request.getParameter("id"));
				}
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			utilisateurManager.deleteUtilisateur(idUser);
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			
		} else if (request.getServletPath().contains("/ProfilUser")) {
			
			System.out.println("*********/PROFIL USER************");
			Utilisateur utilisateur;
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			int idUser=0;
			if (request.getParameter("id") != null) {
				idUser = Integer.parseInt(request.getParameter("id"));
				
			}
			utilisateur = utilisateurManager.selectUtilisateurId(idUser);
			System.out.println("*************************");
			System.out.println("-------SERVLETAFFICHAGEPROFIL USER-----------");
			System.out.println("****utilisateur =" + utilisateur.toString());
			System.out.println("*******************");
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/restreint/profilUser.jsp");
			rd.forward(request, response);
		}
		
		
	
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/Update")) {
			int idUser;
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
			String newMotDePasse = request.getParameter("newMotDePasse");
			System.out.println("*********UPDATE***********");
			Utilisateur utilisateur = new Utilisateur();
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			idUser = Integer.parseInt(request.getParameter("id"));
			System.out.println("idUser" + idUser + "Confirmation =" + confirmation+".");
			
			utilisateur = utilisateurManager.RecupUtilisateurRequete(request);
			utilisateur.setNoUtilisateur(idUser);
			utilisateurManager.UpdateUtilisateur(idUser,pseudo,prenom,telephone,codePostal,nom,motDePasse,email, rue,ville, confirmation, newMotDePasse);
			System.out.println("utilisateurUPDATESERVLET " + utilisateur.toString() );
			request.setAttribute("utilisateur", utilisateur);
			
		
			

}
		RequestDispatcher rd = request.getRequestDispatcher("/AccueilSession");
		rd.forward(request, response);
	}
}
