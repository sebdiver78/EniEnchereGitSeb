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
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class Encherir
 */
@WebServlet(
		urlPatterns= {
				"/ActionArticle"
				
				
		})

public class ServletActionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActionArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("**********servlet ACTION ARTICLE************");
		Article article;
		ArticleManager articleManager = new ArticleManager();
		List<Article> listeArticles = new ArrayList<Article>();
		List<Article> listeVerif = new ArrayList<Article>();
	
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = new Utilisateur();
		utilisateurSession = (Utilisateur) session.getAttribute("sessionUtilisateur");
		//recup du parametre passé en 'id' dans la requete pour le passer à idArticle
		int idArticle = 0; 
		// on convertit en int le string de la requete
		if (request.getParameter("id") != null) {
			idArticle = Integer.parseInt(request.getParameter("id"));
			}
		
		listeArticles = articleManager.listeTousLesArticles();
		System.out.println(listeArticles.toString());
		listeVerif.clear();
		System.out.println("id Article "+idArticle);
		System.out.println("listeVerif = "+ listeVerif.toString());
		 for (Article a : listeArticles) {
			 	 if (a.getIdArticle() == idArticle) {
			 		 
			 		 listeVerif.add(a);
			 	 }
		 }
		 System.out.println("listeVerif2 = "+ listeVerif.toString());
			if (!listeVerif.isEmpty()) { 
				System.out.println("listeVerif pas vide id=" +idArticle);
				 article = articleManager.selectArticleById(idArticle);
				 System.out.println("articleEnchere" + article.toString());
					request.setAttribute("articleEnchere", article);
					System.out.println("********doGET ACTION ARTICLE retour selectArticleById enchere*****");
					System.out.println(article.toString());
					RequestDispatcher rd = request.getRequestDispatcher("/restreint/enchere.jsp");
					rd.forward(request, response);
				 
			 } else {
				 
					
				
					List<Article> listeArticlesConnection = new ArrayList<Article>();
					
					listeArticlesConnection = articleManager.listeTousLesArticlesConnection(utilisateurSession);
					request.setAttribute("listeArticles", listeArticlesConnection);
					
					System.out.println("**********SERVLET ACCUEIL SESSION****");
					System.out.println("Credit USERSESSION" + utilisateurSession.getCredit());
				
					
					RequestDispatcher rd = request.getRequestDispatcher("/restreint/accueilSession.jsp");
					rd.forward(request, response);
				 
				 
				 
				 
				 
				 
				 
			 }
			 
		 }
		
		

		
		
		
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("********SERVLET ENCHERIR doPOST***********");
		Utilisateur utilisateurSession = new Utilisateur();
		HttpSession session = request.getSession();
		utilisateurSession = (Utilisateur) session.getAttribute("sessionUtilisateur");
		int enchereActuelle = (int) Integer.parseInt(request.getParameter("enchereActuelle"));
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		System.out.println("idArticle" + idArticle);
		
		int idUserSession = utilisateurSession.getNoUtilisateur();
		int credit = utilisateurSession.getCredit();
		
		System.out.println("CREDIT USER SESSION = "+ credit);
		System.out.println("**********SERVLET encherir************");
		System.out.println(idArticle +" "+idUserSession+" "+enchereActuelle);
		
		int montant = Integer.parseInt(request.getParameter("proposition"));
		
		
		Enchere enchere = new Enchere();
		enchere.setMontantEnchere(montant);
		enchere.setIdArticle(idArticle);
		enchere.setNoUtilisateur(idUserSession);
		
		Utilisateur utilisateur = new Utilisateur();
		EnchereManager enchereManager = new EnchereManager();
		utilisateur = enchereManager.valideInsertEnchere(idArticle, idUserSession, montant, enchereActuelle, credit);
		utilisateurSession.setCredit(utilisateur.getCredit());
		if (enchereManager.getErreur().isEmpty()) {
			
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/AccueilSession");
		rd.forward(request, response);
		
		
	}

}
