package fr.eni.enchere.servlets;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class AccueilSession
 */
@WebServlet("/AccueilSession")
public class ServletAccueilSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Utilisateur> listeIdPseudo = new ArrayList<Utilisateur>();
		listeIdPseudo = utilisateurManager.getlisteIdPseudo();
		System.out.println("liste "+ listeIdPseudo);
		ServletContext servletContext;
		this.getServletContext().setAttribute("listeIdPseudo", listeIdPseudo);
		
    	
		
		
		//getServletContext().setAttribute("listeIdPseudo", "listeIdPseudo");
    	
    	
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = new Utilisateur();
		utilisateurSession = (Utilisateur) session.getAttribute("sessionUtilisateur");
		
		ArticleManager articleManager = new ArticleManager();
		List<Article> listeArticlesConnection = new ArrayList<Article>();
		listeArticlesConnection = articleManager.listeTousLesArticlesConnection(utilisateurSession);
		request.setAttribute("listeArticles", listeArticlesConnection);
		
		//Categories categorie = new Categories();
		//List<Categories> listeCategories = new ArrayList<>();
		
		//ArticleManager articleManagerCat = new ArticleManager();
		//listeCategories = articleManagerCat.selectLibelleCategorie();
		//request.setAttribute("listeCategories", listeCategories);
		
		System.out.println("**********SERVLET ACCUEIL SESSION****");
		System.out.println("Credit USERSESSION" + utilisateurSession.getCredit());
	
		
		RequestDispatcher rd = request.getRequestDispatcher("/restreint/accueilSession.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
