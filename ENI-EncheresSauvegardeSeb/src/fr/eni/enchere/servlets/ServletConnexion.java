package fr.eni.enchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		Utilisateur utilisateur = utilisateurManager.connecterUtilisateur(request); 
		System.out.println("userConnex "+utilisateur.toString());
		
		HttpSession session = request.getSession();
		
		
			
			//verifie si case cookie cochée
		//on recupere la valeur du parametre de checkbox avec getPArameter
			String souvenir = request.getParameter("souvenir");
			System.out.println("souvenir " + souvenir);
					
			if (utilisateurManager.getErreur().isEmpty()) {
			
			
			if ( souvenir != null) {// si oui créer un objet Cookie, recuperer ds utilisateurSession idUSER -> getCoookie + parametre duree de vie 5min
				//ensuite se connecte
					System.out.println("condition souvenir OUI");							
				Cookie cookieUser = new Cookie("cookieUser",Integer.toString(utilisateur.getNoUtilisateur()));
				cookieUser.setMaxAge(60*60);
				response.addCookie(cookieUser);
				
				System.out.println("CookieUSer name =" + cookieUser.getName().toString());
				System.out.println("CookieUSer value =" + cookieUser.getValue().toString());
				
								
				
					
					ArticleManager articleManager = new ArticleManager();
					
					List<Article> listeArticlesConnection = new ArrayList<Article>();
					listeArticlesConnection = articleManager.listeTousLesArticlesConnection(utilisateur);
					System.out.println("ServletAccueil ListeArticle = "+ listeArticlesConnection.toString());
					request.setAttribute("listeArticles", listeArticlesConnection);
					
					//Categories categorie = new Categories();
					//List<Categories> listeCategories = new ArrayList<>();
					
					ArticleManager articleManagerCat = new ArticleManager();
					
					//listeCategories = articleManagerCat.selectLibelleCategorie();
					//request.setAttribute("listeCategories", listeCategories);
					
					session.setAttribute("sessionUtilisateur", utilisateur);
					System.out.println("SERVELT  CONNEXION valeur utilisateurSession"  );
					System.out.println("UserSession "+ utilisateur.toString());
					request.setAttribute("form", utilisateurManager);
					request.setAttribute("utilisateur", utilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/restreint/accueilSession.jsp");
					rd.forward(request, response);
				
				} else  {		
			
				//si case pas cochée
				//on connecte la session sans recuperer de COOKIE
						System.out.println("connection pas cochée :");
					
					ArticleManager articleManager = new ArticleManager();
					
					List<Article> listeArticlesConnection = new ArrayList<Article>();
					listeArticlesConnection = articleManager.listeTousLesArticlesConnection(utilisateur);
					System.out.println("ServletAccueil ListeArticle = "+ listeArticlesConnection.toString());
					request.setAttribute("listeArticles", listeArticlesConnection);
					
					//Categories categorie = new Categories();
					//List<Categories> listeCategories = new ArrayList<>();
					
					ArticleManager articleManagerCat = new ArticleManager();
					
					//listeCategories = articleManagerCat.selectLibelleCategorie();
					//request.setAttribute("listeCategories", listeCategories);
					
					session.setAttribute("sessionUtilisateur", utilisateur);
					System.out.println("SERVELT  CONNEXION valeur utilisateurSession"  );
					System.out.println("UserSession "+ utilisateur.toString());
					request.setAttribute("form", utilisateurManager);
					request.setAttribute("utilisateur", utilisateur);
					RequestDispatcher rd = request.getRequestDispatcher("/restreint/accueilSession.jsp");
					rd.forward(request, response);
					
				
				
					
			} // fin case pas cochée 
	
	
	//FIN test condition sur case cochée
	}else {
				
			//session pas connectée
					
			System.out.println("seesion non connectée");
			session.setAttribute("sessionUtilisateur", null);
			request.setAttribute("form", utilisateurManager);
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
			rd.forward(request, response);
		
		}
	}	
		
	
}
			
			


	//fin doPost
		
//fin SERVLET connexion
