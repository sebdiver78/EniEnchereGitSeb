package fr.eni.enchere.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet(
		urlPatterns= {
				"",
				"/rechercher"
				
		})
		
	
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	public void init() throws ServletException {
		//chargement liste cat�gorie dans le contexte d'application
		Categories categorie = new Categories();
		List<Categories> listeCategories = new ArrayList<>();
		ArticleManager articleManagerCat = new ArticleManager();
		listeCategories = articleManagerCat.selectLibelleCategorie();
		this.getServletContext().setAttribute("listeCategories", listeCategories);
		
		
		//chargement liste id/pseudo contexte application
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Utilisateur> listeIdPseudo = new ArrayList<Utilisateur>();
		listeIdPseudo = utilisateurManager.getlisteIdPseudo();
		System.out.println("liste "+ listeIdPseudo);
		ServletContext servletContext;
		this.getServletContext().setAttribute("listeIdPseudo", listeIdPseudo);
		
    	
		
		
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// page accueil sans cookie accueil.jsp
		
		//si cookie accueilSession
		
		
		
		
		
		
		
		//verifie si il y a cookie USER* CookieUSer
		HttpSession session = request.getSession();
		Utilisateur userSession = new Utilisateur();
		
		//SI OUI recupere COOKIE
		
		//1ere connection sans cookie
			
		if (request.getServletPath().equals("")) { 
			
			
			//1er verifie cookie
			Cookie [ ] cookies = request.getCookies();
			int idCookie =0;		
		
			//ACCUEIL
			// si (tableau cookie vide car dossier cookie vidés par user) {
			if (cookies == null) {
					System.out.println("******condition cookie null*****");
				//affichage accueil normal
				//Categories categorie = new Categories();
				 //List<Categories> listeCategories = new ArrayList<>();
				
				ArticleManager articleManager = new ArticleManager();
				
				//listeCategories = articleManager.selectLibelleCategorie();
				//request.setAttribute("listeCategories", listeCategories);
				
			List<Article> listeArticles = new ArrayList<Article>();
				listeArticles = articleManager.listeTousLesArticles();
				System.out.println("ServletAccueil ListeArticle = "+ listeArticles.toString());
				request.setAttribute("listeArticles", listeArticles);
				
				System.out.println("/ accueil normal");
				System.out.println(" listeArticle accueil normal "+ listeArticles.toString());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
				rd.forward(request, response);
				
			} else if (cookies != null) {
				
			// Creation liste listeCookies
				List<Cookie> listeCookies = new ArrayList<Cookie>();
				Cookie cookieUser=null;
						for (Cookie c : cookies) {
					
					System.out.println("tableau cookies : "+ c.getName().toString());
								if (c.getName().equals("cookieUser")) {
									//on met dans la liste le cookie nomm�e cookieUser
									//il n'y en a qu'un
									listeCookies.add(c);
									
								}
						
						
							}//fin boucle FOR
						
							//si la liste n'est pas vide, on récupére le seul et 1er cookie	
							if (!listeCookies.isEmpty()) {
							System.out.println("Cookie user trouvé");
							
							
							cookieUser = listeCookies.get(0);
							idCookie = Integer.parseInt(cookieUser.getValue());
							
								System.out.println("IdCookie ="+ idCookie);	
								UtilisateurManager utilisateurManager = new UtilisateurManager();
								//on recupere en BDD l'utilisateur qui a l'id enregistr� dans le cookie
								userSession = utilisateurManager.selectUtilisateurId(idCookie);
								//on passe en attribut de session l'objet utilisateur r�cup�r�
								session.setAttribute("sessionUtilisateur", userSession);
								request.setAttribute("sessionUtilisateur", userSession);
							
								//Categories categorie = new Categories();
								//ist<Categories> listeCategories = new ArrayList<>();
								
								ArticleManager articleManager = new ArticleManager();
								
								//listeCategories = articleManager.selectLibelleCategorie();
								//request.setAttribute("listeCategories", listeCategories);
								
								List<Article> listeArticles = new ArrayList<Article>();
								listeArticles = articleManager.listeTousLesArticles();
								System.out.println("ServletAccueil ListeArticle = "+ listeArticles.toString());
								request.setAttribute("listeArticles", listeArticles);
								System.out.println("***FIN condition cookieUSer trouv�");
								System.out.println("VERS AccueilSession.jsp");
							
								
								RequestDispatcher rd = request.getRequestDispatcher("/AccueilSession");
								rd.forward(request, response);
						
						
						} else {//si la liste est vide on execute ce code
							
							System.out.println("servletAccueil");
							//Categories categorie = new Categories();
							//List<Categories> listeCategories = new ArrayList<>();
							
							ArticleManager articleManager = new ArticleManager();
							
							//listeCategories = articleManager.selectLibelleCategorie();
							//request.setAttribute("listeCategories", listeCategories);
							
							List<Article> listeArticles = new ArrayList<Article>();
							listeArticles = articleManager.listeTousLesArticles();
							
							request.setAttribute("listeArticles", listeArticles);
							System.out.println("pas de cookieUSer -> /Accueil.jsp");
							
						
							
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
							rd.forward(request, response);
							
						}
						
						}
					
		}
				
			}
			
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idCat =Integer.parseInt(request.getParameter("idCat"));
		String filtre = request.getParameter("filtre");
		System.out.println("*********SERVLET ACCUEIL doPOST*****");
		System.out.println("Idcat= "+idCat + " filtre ="+filtre);
		
		//Categories categorie = new Categories();
		//List<Categories> listeCategories = new ArrayList<>();
		
		ArticleManager articleManager = new ArticleManager();
		// requete pour remplir liste CATEGORIE
		//listeCategories = articleManager.selectLibelleCategorie();
		//request.setAttribute("listeCategories", listeCategories);
		
		//REQUETE pour remplir listeArticle
		List<Article> listeArticles = new ArrayList<Article>();
		List<Article> listeAEnvoyer = new ArrayList<Article>();
		
		listeArticles = articleManager.listeTousLesArticles();
		
		
	
		//requete pour FILTRES
		if (idCat != 0 && filtre != null || filtre != "") {
			if (idCat!=0 && filtre == null || filtre =="") {
				for (Article articleListe : listeArticles) {
					if (idCat == articleListe.getIdCategorie()) {
						listeAEnvoyer.add(articleListe);
						
					}
					
					
										
				} 
				
				request.setAttribute("listeArticles", listeAEnvoyer);
				
			} else if (idCat==0 && (filtre != null || filtre != "")) {
				
				
				for (Article article : listeArticles) {
					if (article.getNomArticle().contains(filtre)) {
						
						listeAEnvoyer.add(article);	
						}
										
				} 
				request.setAttribute("listeArticles", listeAEnvoyer);
				
			} else { 
					System.out.println("****filtre+cat***");
					for (Article article : listeArticles) {
						
											
						if (article.getNomArticle().contains(filtre) && idCat == article.getIdCategorie()) {
							
								
								listeAEnvoyer.add(article);
							
										
	
					}request.setAttribute("listeArticles", listeAEnvoyer);
			}	
			}
			
		} else {
			
			listeAEnvoyer = listeArticles;
			request.setAttribute("listeArticles", listeAEnvoyer);
					
					
				}
			
		
			
			
		
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
		
	}
			
}
