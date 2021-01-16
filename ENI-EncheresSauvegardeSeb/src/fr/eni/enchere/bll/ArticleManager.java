package fr.eni.enchere.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleDAO;
import fr.eni.enchere.dal.DAOFactory;

public class ArticleManager {
	//on instancie la'interface pour le lien avec articleDAOJdbcImpl
	private ArticleDAO articleDAO;
	private String resultat;
	
		//CONSTRUCTEUR d' articleManager qui permet de récuperer les methodes de articleDAOJdbcImpl
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	//liste d'erreurs
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	
	//ajoute à la liste ERREURS une variable champ avec un message
	private void setErreur(String champ, String message) {
		 erreurs.put(champ, message);
	}
	
	//retourne la liste d'erreur
	public Map<String, String> getErreur() {
		return erreurs;
	}
	
	public String getResultat() {
		return resultat;
	}




//*************************************************************************
//METHODE ArticleManager
//***************************************************************************

public void insertArticleRetrait(String nomArticle, String descriptionArticle, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente, int idUser, int idCategorie,  String rue, String codePostal, String ville ) {
	
	Article article = new Article();
	Retrait retrait = new Retrait();
	
	article.setIdUser(idUser);
	
	try {
		
		validationArticle(nomArticle);
	} catch (Exception e) {
		setErreur("nom", e.getMessage());
		
	}
	article.setNomArticle(nomArticle);
	
try {
		
		validationDescription(descriptionArticle);
	} catch (Exception e) {
		setErreur("", e.getMessage());
		
	}
	article.setDescriptionArticle(descriptionArticle);
	
try {
		
		validationDateDebutEnchere(dateDebutEnchere);
	} catch (Exception e) {
		setErreur("dateDebut", e.getMessage());
		
	}
	article.setDateDebutEnchere(dateDebutEnchere);
	
try {
		
		validationDateFinEnchere(dateFinEnchere);
	} catch (Exception e) {
		setErreur("dateFin", e.getMessage());
		
	}
	article.setDateFinEnchere(dateFinEnchere);
	
try {
		
		validationMiseAPrix(miseAPrix);
	} catch (Exception e) {
		setErreur("miseAPrix", e.getMessage());
		
	}
	article.setMiseAPrix(miseAPrix);
	
	
	
try {
		
		validationCategorie(idCategorie);
	} catch (Exception e) {
		setErreur("Idcategorie", e.getMessage());
		
	}
	article.setIdCategorie(idCategorie);
	article.setPrixVente(prixVente);
	
	retrait.setRue(rue);
	retrait.setCodePostal(codePostal);
	retrait.setVille(ville);
	
	
	//System.out.println("*********ARTICLE MANAGER INSERTION************");
	//System.out.println(" article = "+ article.toString()+ " retrait "+retrait.toString());
	//System.out.println("*******************************");
	if (erreurs.isEmpty()) {
		
		articleDAO.insertArticle(article, retrait);
		resultat="Enregistrement article OK";
	} else {
		resultat = "Echec enregistrement article";
		
	}
	
System.out.println("ArticleManager REsultat: "+ resultat);
	
	
	
}


//SELECT ARTICLE BY ID
public Article selectArticleById(int idArticle) {
	Article article = new Article();
	article = articleDAO.getArticleById(idArticle);
	System.out.println("*********Article MANAGER ARTICLE BY ID");
	System.out.println("Article "+ idArticle +" "+ article.toString());
	
	return article;
}


//GESTION LISTE CATEGORIES

public List<Categories> selectLibelleCategorie() {
	List<Categories> listeCategories = new ArrayList<>();
	listeCategories = articleDAO.getAllCategories();
	//System.out.println("******ArticleManager CATEGORIE LISTE*********");
	//System.out.println("ListeCat = "+ listeCategories.toString());
	
	return listeCategories;
}

//RECUP TOUTES LISTES ARTICLES



public List<Article> listeTousLesArticles() {
	List<Article> listeArticles = new ArrayList<Article>();
	
listeArticles = articleDAO.getAllArticles();
//System.out.println("******ARTICLE MANAGER**************");
//System.out.println("ListeArtilces "+ listeArticles);
	
	
	return listeArticles;
}


//*****************************************************
//Methode qui trie la liste d'articles de l'utilisateur suivant la valeur renvoyée par le bouton
//****************************************************************
public List<Article> listeArticlesPourTriBouton(String bouton, Utilisateur utilisateurSession) {
	List<Article> listeArticlesTriee = new ArrayList<Article>();
	List<Article> listeArticlesATrier = new ArrayList<Article>();
		listeArticlesATrier = this.articleDAO.getListeArticlesPourTri();
	System.out.println("***********ARTICLE MANAGER listeArticlePourTri****************");
	System.out.println("listeArticles pour tri boutons "+ listeArticlesATrier.toString());
	//ventes en cours = listeArticle-article.idUSER de utilisateur session
	//ventes non débutées = listeArticle avec articles qui correspondent à article.idUSer et date_debut>NOW
	//ventes terminées = listeArticle avec idUSer userSession dateFinEnchere<now
	System.out.println("Bouton = " + bouton);
		if (bouton.equals("ventesEnCours")) {
			System.out.println("vente en cours");
				for (Article a : listeArticlesATrier) {
					if ((utilisateurSession.getNoUtilisateur() == a.getIdUser()) && (a.getDateDebutEnchere().compareTo(LocalDate.now())<1) && (a.getDateFinEnchere().compareTo(LocalDate.now())>1)) {
						listeArticlesTriee.add(a);
					}
				}
					
		} else if (bouton.equals("nonDebutees")) {
				for (Article a : listeArticlesATrier) {
					if(utilisateurSession.getNoUtilisateur() == a.getIdUser() && (a.getDateDebutEnchere().compareTo(LocalDate.now())>1) ) {
						
						listeArticlesTriee.add(a);
							
						}
					}
				} else if (bouton.equals("terminees")) {
			for (Article a : listeArticlesATrier) {
				if(utilisateurSession.getNoUtilisateur() == a.getIdUser()) {
					if((a.getDateFinEnchere().compareTo(LocalDate.now()))<1) {
						listeArticlesTriee.add(a);
						
								}
							}
						}
					}
		System.out.println("ArticleManager ListeTriée bouton "+ listeArticlesTriee.toString());
		return listeArticlesTriee;
		}
	


//--------------------------------------------------------------------------------
public List<Article> filtreArticles(int idCat, String filtre, List<Article> listeArticlesTries) {
		List<Article> listeAEnvoyer = new ArrayList<Article>();
		System.out.println("article MANAGER filtreArticles");
		System.out.println("idCAT "+ idCat + " filtre " + filtre);
		System.out.println("liste ArticlesTries" + listeArticlesTries.toString());
		if (idCat != 0 && filtre != null || filtre != "") {
			System.out.println("1ere boucle id cat !0 filtre !null" );
			if (idCat!=0 && filtre == null || filtre =="") {
				System.out.println("1ere boucle id cat set filtre null PAR CATEGORIE" );
				
				for (Article articleListe : listeArticlesTries) {
					if (idCat == articleListe.getIdCategorie()) {
						listeAEnvoyer.add(articleListe);
						
					}
		
				}
				System.out.println("listeArticleTries "+ listeArticlesTries);
				System.out.println("listeAEnvoyer" + listeAEnvoyer.toString());
				System.out.println("*************FIN articleMANAGER************************");
			} else if (idCat==0 && (filtre != null || filtre != "")) {
				for (Article article : listeArticlesTries) {
					if (article.getNomArticle().contains(filtre)) {
						
						listeAEnvoyer.add(article);	
						}
										
				} 
				
				
			} else {
				
				for (Article article : listeArticlesTries) {
			
				
									
				if (article.getNomArticle().contains(filtre) && idCat == article.getIdCategorie()) {
					
						
						listeAEnvoyer.add(article);
				}
				}
			}
					
		} else {
			
			listeAEnvoyer = listeArticlesTries;
			System.out.println("1ere boucle id cat 0 filtre null donc tout" );
		System.out.println("listeArticleTries "+ listeArticlesTries);
		System.out.println("listeAEnvoyer" + listeAEnvoyer.toString());
		System.out.println("*************FIN articleMANAGER************************");
		
			
		}
	return listeAEnvoyer;
}

	
	
	
	




// appelle par articleDAO methode de articleDAOJdbcImpl getListeArticlesPourTri
//renvoie liste ARTICLES triée














//liste en mode connectée, on retire les articles de l'utilisateur en session
//car il ne peut pas encherir sur ses propres objets
public List<Article> listeTousLesArticlesConnection(Utilisateur utilisateur) {
	List<Article> listeArticlesConnection = new ArrayList<Article>();
	List<Article> listeArticles = new ArrayList<Article>();
	//System.out.println("ARTICLE MAANGER listearticlesCONNECTION");
	//System.out.println("IdUser "+ utilisateur.getNoUtilisateur());
	
listeArticles = articleDAO.getAllArticles();
//System.out.println((listeArticles.toString()));
	for (Article a :listeArticles) {
		System.out.println("a.getUser " + a.getIdUser()+ " No User "+ utilisateur.getNoUtilisateur());
	if (a.getIdUser() != utilisateur.getNoUtilisateur()) {
		listeArticlesConnection.add(a);
	}
	}
	

//System.out.println("******ARTICLE MANAGER**************");
//System.out.println("ListeArtilces "+ listeArticlesConnection);
	
	
	return listeArticlesConnection;
}


//UPDATE PRIXVENTE d'ARTICLE

public void updatePrixArticle(int idArticle, int prixVente) {
	
	articleDAO.updateEnchereArticle(idArticle, prixVente);
	
}



public List<Article> getListeVentesOuvertes() {
	List<Article> listeVentesOuvertes = new ArrayList<Article>();
	listeVentesOuvertes = articleDAO.getListeVentesOuvertes();
	
	return listeVentesOuvertes;
	
	
}







//********************************************************
//fonctions utilisées par articleManager pout verif et validation
//***********************************************************


private void validationArticle(String article) throws Exception {
	
	
	if(article == null || article =="") { //si var article est null ou vide renvoie une exception
		throw new Exception("Saisir un nom d'article");//avec un message qui sera recupéré dans un try
														//catch dans la methode utilisant pour etre ajouté à la liste des erreurs
		
	}
	
}





private void validationDescription (String description) throws Exception {
	
	if(description == null || description == "" ) {
		
		throw new Exception ("Saisir une description");
	}


}

private void validationCategorie(int categorie) throws Exception {
	
	if(categorie < 0) {
		
		throw new Exception ("Choisir une categorie");
	}

}

private void validationMiseAPrix(int miseAPrix) throws Exception {
	
	if(miseAPrix <0) {
		
		throw new Exception ("Choisir un prix différent de 0");
		}
}

private void validationDateDebutEnchere (LocalDate dateDebutEnchere) throws Exception {
	
	if (dateDebutEnchere == null) {
		
		throw new Exception ("Choisir une date de début");
	}
}

private void validationDateFinEnchere (LocalDate dateFinEnchere) throws Exception {
	
	if (dateFinEnchere == null) {
		
		throw new Exception ("Choisir une date de fin");
	}
}

private void validationRue (String rue) throws Exception {
	
	if (rue == null || rue == "") {
		
		throw new Exception ("Indiquer une rue");
	}
	
	
}

private void validationCodePostal (String codePostal) throws Exception {
	
	if (codePostal == null || codePostal == "") {
		
		throw new Exception ("Indiquer un codePostal");
	}
	
	
}

private void validationVille (String ville) throws Exception {
	
	if (ville == null || ville == "") {
		
		throw new Exception ("Indiquer une ville");
	}
	
	
}
}