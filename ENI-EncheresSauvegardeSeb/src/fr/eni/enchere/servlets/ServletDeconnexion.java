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
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/Deconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/Deconnexion")) { 
			//recup libelle categorieBDD
			Categories categorie = new Categories();
			List<Categories> listeCategories = new ArrayList<>();
			
			ArticleManager articleManager = new ArticleManager();
			
			listeCategories = articleManager.selectLibelleCategorie();
			request.setAttribute("listeCategories", listeCategories);
			
			List<Article> listeArticles = new ArrayList<Article>();
			listeArticles = articleManager.listeTousLesArticles();
			System.out.println("ServletAccueil ListeArticle = "+ listeArticles.toString());
			request.setAttribute("listeArticles", listeArticles);
			System.out.println("***SERVLET_Accueil partie /");
			
			} 
			
		request.getSession().invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
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
