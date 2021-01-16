package fr.eni.enchere.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleManager;
import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ServletVendreUnArticle
 */
@WebServlet(
		urlPatterns= {
				"/Annuler",
				"/VendreUnArticle"
				
		})
public class ServletVendreUnArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	
    
    }  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().contentEquals("/VendreUnArticle")) {
			
			//recup libelle categorieBDD
			//Categories categorie = new Categories();
			//List<Categories> listeCategories = new ArrayList<>();
			//ArticleManager articleManager = new ArticleManager();
			//listeCategories = articleManager.selectLibelleCategorie();
			//request.setAttribute("listeCategories", listeCategories);
					
		RequestDispatcher rd = request.getRequestDispatcher("/restreint/NouvelleVente.jsp");
		rd.forward(request, response);
		} else if (request.getServletPath().contentEquals("/Annuler")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/AccueilSession");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("************DEBUT doPOST ServletVENDRE**************");
		
		ArticleManager articleManager = new ArticleManager();
		Article article = new Article();
		Utilisateur userConnected = new Utilisateur();
		HttpSession session = request.getSession();
		userConnected = (Utilisateur) session.getAttribute("sessionUtilisateur");
		
		
		
		
		
		
		//Recup saisie retrait
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		//Recup saisie article
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		int idCategorie = Integer.parseInt(request.getParameter("categorie"));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		int prixVente = miseAPrix;
		LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebutEnchere"));
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("dateFinEnchere"));
		
		int idUser = userConnected.getNoUtilisateur();
		
		System.out.println( "Id User=" + idUser);
		System.out.println("date" + dateDebutEnchere);
		System.out.println("Rue =" +rue);
		
		
		articleManager.insertArticleRetrait(nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix, prixVente, idUser, idCategorie, rue, codePostal, ville);
		System.out.println("Article inséré dans BDD");
		System.out.println("************FIN doPOST*********");
		System.out.println();
			
		
	
		RequestDispatcher rd = request.getRequestDispatcher("/AccueilSession");
		rd.forward(request, response);
				
	}
	
	

}
