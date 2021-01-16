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
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletTri
 */
@WebServlet("/triSession")
		
public class ServletTri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTri() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/restreint/accueilSession.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("**********bouton TRI SESSION RECHERCHER**********");
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = new Utilisateur();
		utilisateurSession = (Utilisateur) session.getAttribute("sessionUtilisateur");
		
		int idCat =Integer.parseInt(request.getParameter("idCat"));
		String filtre = request.getParameter("filtre");
		System.out.println("*********SERVLET TRI doPOST*****");
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
		List<Article> listeEncheresProv = new ArrayList<Article>();
		
		listeArticles = articleManager.listeTousLesArticles();
		
		
	
		//requete pour FILTRES
		//par défaut enchères ouvertes checked
		String valeurBouton = request.getParameter("achatEnchere");
		String valeurRadio = request.getParameter("liste");
		List<Article> listeArticlesTriees = new ArrayList<Article>();
		List<Article> listeEncheresTriees = new ArrayList<Article>();
		System.out.println("------retour BOUTON------------");
		System.out.println("bouton selectionné "+ valeurBouton.toString());
		//selection des encheres ou articles dans la liste par boutons
		//avant d'utiliser les filtres
		if (valeurRadio.equals("ventes")) {
		listeArticlesTriees = articleManager.listeArticlesPourTriBouton(valeurBouton, utilisateurSession);
		System.out.println("***********SERVLET TRI VENTES**********");
		System.out.println("liste Article triee " + listeArticlesTriees.toString());
		//si bouton encheres  on va ds enchereManager methode qui renvoie 
		//1 des 3 liste d'enchères
		//à filtre
		
		//si bouton ventes on va ds articleManager methode qui renvoie 
		//1 des 3 liste d'articles à filtrer
		
		//retour une liste qui portera le meme nom pour article et enchere
		
		listeAEnvoyer = articleManager.filtreArticles(idCat, filtre, listeArticlesTriees);
		System.out.println("listeAEnvoyer" + listeAEnvoyer.toString());
		request.setAttribute("listeArticles", listeAEnvoyer);
		} else if (valeurRadio.equals("encheres")) {
			System.out.println("---SERVLET TRI ACHAT----");
			System.out.println("radio = "+ valeurRadio + " checkbox ="+ valeurBouton);
			EnchereManager enchereManager = new EnchereManager();
			listeEncheresProv = enchereManager.getListeEncheresTri(valeurBouton, utilisateurSession, idCat, filtre);
			listeEncheresTriees = listeEncheresProv;
			//listeEncheresTriees = enchereManager.retourneListeConv(listeEncheresProv);
			System.out.println("***********SERVLET TRI ACHAT SUITE FILTRE**********");
		
			System.out.println("listeAEnvoyer" + listeEncheresTriees.toString());
			request.setAttribute("listeArticles", listeEncheresTriees);
		}
	
			
		
		RequestDispatcher rd =request.getRequestDispatcher("/restreint/accueilSession.jsp");
		rd.forward(request, response);
		
	}
			

	

}
