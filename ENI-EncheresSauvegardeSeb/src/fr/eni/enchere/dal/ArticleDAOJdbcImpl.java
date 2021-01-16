package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO{
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES (nom, description, date_debut, date_fin, prix_initial, prix_vente, idUserArticle, idCategorie, idRetrait) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (rue, code_postal, ville) VALUES (?,?,?)";
	private static final String GET_CATEGORIE = "SELECT * FROM CATEGORIES";
	private static final String SELECT_ARTICLES_EN_VENTE = "SELECT u.idUser, u.pseudo, u.nom nomUser, u.prenom, u.email, u.telephone, u.rue rueUser, u.code_postal CPUser, u.ville villeUser," +
									" a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.prix_vente, a.idCategorie, r.rue, r.code_postal, r.ville, c.idCategorie, c.libelle" +
									" FROM ARTICLES a INNER JOIN  RETRAITS r ON a.idRetrait=r.idRetrait INNER JOIN USERS u ON u.idUser=a.idUserArticle INNER JOIN CATEGORIES c ON a.idCategorie = c.idCategorie";
	private static final String SELECT_ENCHERE_BY_ARTICLE = "SELECT e.idUserEnchere,e.date, e.MAX(montant), u.pseudo" +
															"FROM ENCHERES e  INNER JOIN ON USERS u WHERE u.idUser = e.idUserEnchere"+
															"AND idArticle = ? ";
	private static final String GET_ARTICLE_BY_ID = "SELECT a.idArticle, a.nom, a.description, c.libelle, a.prix_vente, a.prix_initial, a.date_debut, a.date_fin,"+
															"r.rue, r.code_postal, r.ville, a.idUserArticle, u.pseudo, u.telephone, "+
															"e.idUserEnchere "+	
															"FROM ARTICLES a INNER JOIN  RETRAITS r ON r.idRetrait = a.idRetrait "+
															"INNER JOIN  CATEGORIES c ON c.idCategorie = a.idCategorie "+
															"INNER JOIN USERS u ON u.idUser = a.idUserArticle "+
															"FULL JOIN ENCHERES e ON e.idArticle = a.idArticle WHERE a.idArticle = ?";
	private static final String UPDATE_ENCHERE_ARTICLE = "UPDATE ARTICLES SET prix_vente = ? WHERE idArticle = ?";
	private static final String GET_LISTEARTICLES_POUR_TRI="SELECT a.idArticle, a.nom, a.prix_vente, a.date_debut, a.date_fin, a.idUserArticle, a.idCategorie, u.idUser, u.pseudo" + 
			"		FROM ARTICLES a INNER JOIN USERS u ON a.idUSerArticle = u.idUser";
	private static final String LISTE_VENTES_OUVERTES = "SELECT a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.prix_vente," +
															"a.idUserArticle, u.pseudo, u.telephone, c.libelle, e.idUserEnchere "+
															"FROM ARTICLES a LEFT JOIN USERS u ON a.idUserArticle = u.idUser "	+
															"INNER JOIN CATEGORIES c ON  a.idCategorie = c.idCategorie "+
															"FULL JOIN ENCHERES e ON a.idArticle = e.idArticle ";
															
															
															
														//	"WHERE a.date_debut < GETDATE() AND a.date_fin > GETDATE()";
			
			//\r\n
		@Override
	
	
	
	
	public void insertArticle(Article article, Retrait retrait) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			
			PreparedStatement rqt = cnx.prepareStatement(INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
			rqt.setString(1, retrait.getRue());
			rqt.setString(2,  retrait.getCodePostal());
			rqt.setString(3, retrait.getVille());
			
			rqt.executeUpdate();
			ResultSet rs = rqt.getGeneratedKeys();
			
			if (rs.next()) {
				
				retrait.setIdRetrait(rs.getInt(1));
				
				System.out.println("DAOJDBC = "+ retrait.toString());
			}
			rs.close();
			rqt.close();
			
			
										
			rqt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescriptionArticle());
			rqt.setDate(3, Date.valueOf(article.getDateDebutEnchere()));
			rqt.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			rqt.setInt(5, (int) article.getMiseAPrix());
			rqt.setInt(6, (int) article.getPrixVente());
			rqt.setInt(7, article.getIdUser());
			rqt.setInt(8,  article.getIdCategorie());
			rqt.setInt(9,  retrait.getIdRetrait());
			
			rqt.executeUpdate();
			
			rs = rqt.getGeneratedKeys();//on recupere l'ID generé par l'insertion de l'article
			
			if (rs.next()) {
				
				article.setIdArticle(rs.getInt(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

	@Override
	
	public List<Article> getAllArticles() {
		List<Article> listeArticles = new ArrayList<>();
		Utilisateur utilisateur;
		Article article;
		Retrait retrait;
		Categories categorie;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLES_EN_VENTE);
			ResultSet rs = rqt.executeQuery();
			
			while (rs.next()) {
				
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("idUser"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nomUser"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rueUser"));
				utilisateur.setCodePostal(rs.getString("CPUser"));
				utilisateur.setVille(rs.getString("villeUser"));
				utilisateur.setPrenom(rs.getString("prenom"));
				
				//LocalDate dateFinEnchere = LocalDate.parse((CharSequence) rs.getDate("date_fin"));
				//LocalDate dateDebutEnchere = LocalDate.parse((CharSequence) rs.getDate("date_debut"));
				article = new Article();
				article.setIdUser(utilisateur.getNoUtilisateur());
				article.setIdArticle(rs.getInt("idArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDescriptionArticle(rs.getString("description"));
				article.setDateDebutEnchere(rs.getDate("date_debut").toLocalDate());
				article.setDateFinEnchere(rs.getDate("date_fin").toLocalDate());
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setIdCategorie(rs.getInt("idCategorie"));
				
				retrait = new Retrait();
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				
				categorie = new Categories();
				categorie.setIdCategorie(rs.getInt("idCategorie"));
				categorie.setLibelle(rs.getString("libelle"));
				
				
				article.setUtilisateur(utilisateur);
				article.setRetrait(retrait);
				article.setCategorie(categorie);
				
				listeArticles.add(article);
				
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return listeArticles;
	}

	
	//*********************ARTICLE BY ID**********************
	@Override
	/*
	"SELECT a.idArticle, a.nom, a.description, c.libelle, a.prix_vente, a.prix_initial, a.date_debut, a.date_fin,"+
	"r.rue, r.code_postal, r.ville, a.idUserArticle, u.pseudo, u.telephone, "+
	"e.idUserEnchere"+	
	"FROM ARTICLES a INNER JOIN  RETRAITS r ON r.idRetrait = a.idRetrait"+
	"INNER JOIN  CATEGORIES c ON c.idCategorie = a.idCategorie"+
	"INNER JOIN USERS u ON u.idUser = a.idUserArticle"+
	"INNER JOIN ENCHERES e ON e.idArticle = a.idArticle WHERE idArticle = ?";
	*/

	public Article getArticleById(int id) {
		Article article = new Article();
		Retrait retrait = new Retrait();
		Categories categorie = new Categories();
		Utilisateur utilisateur = new Utilisateur();
		Enchere enchere = new Enchere();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(GET_ARTICLE_BY_ID);
			rqt.setInt(1, id);
			
			ResultSet rs = rqt.executeQuery();
			System.out.println("DAOJDBCARTICLE id = "+ id);
			if (rs.next()) {
				
				article = new Article();
				utilisateur= new Utilisateur();
				retrait=new Retrait();
				categorie = new Categories();
				enchere = new Enchere();
				
				enchere.setNoUtilisateur(rs.getInt("idUserEnchere"));
				
				article = new Article();
				article.setIdArticle(rs.getInt("idArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDescriptionArticle(rs.getString("description"));
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setDateDebutEnchere(rs.getDate("date_debut").toLocalDate());
				article.setDateFinEnchere(rs.getDate("date_fin").toLocalDate());
				article.setMiseAPrix(rs.getInt("prix_initial"));
				
				retrait = new Retrait();
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setTelephone(rs.getString("telephone"));
				categorie.setLibelle(rs.getString("libelle"));
				
				
				article.setUtilisateur(utilisateur);
				article.setRetrait(retrait);
				article.setCategorie(categorie);
				article.setEnchere(enchere);
			
				
				
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("article "+id+ " " +article.toString());
		return article;
	}

	@Override
	public List<Categories> getAllCategories() {
		List<Categories> listeCategories = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(GET_CATEGORIE);
			ResultSet rs = rqt.executeQuery();
			
			while(rs.next()) {
				Categories categorie = new Categories();
				categorie.setIdCategorie(rs.getInt("idCategorie"));
				categorie.setLibelle(rs.getString("libelle"));
				
				listeCategories.add(categorie);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeCategories;
	}
	
	//--------------ENCHERES-----------------
	
		//"SELECT idUser,date, MAX(montant)  FROM ENCHERES WHERE idArticle = ? AND MONTANT ";

	@Override
	public Enchere getEnchereByArticle(int idArticle) {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void updateEnchereArticle(int idArticle, int prixVente) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ENCHERE_ARTICLE);
			rqt.setInt(1, prixVente);
			rqt.setInt(2, idArticle);
			rqt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
//*******************************************
	//LISTE ARTICLES POUR TRI
	
	//"SELECT a.idArticle, a.nom, a.prix_vente, a.date_debut, a.date_fin, a.idUser, a.Idcategorie, u.idUser, u.pseudo\r\n" + 
	//"		FROM ARTICLES a INNER JOIN USERS u ON a.idUSer = u.idUser";
	public List<Article> getListeArticlesPourTri() {
		List<Article> listeArticlesPourTri = new ArrayList<Article>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(GET_LISTEARTICLES_POUR_TRI);
			
			ResultSet rs = rqt.executeQuery();
			
			while(rs.next()) {
				
				Article article = new Article();
				article.setIdCategorie(rs.getInt("idCategorie"));
				article.setIdArticle(rs.getInt("idArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDateDebutEnchere(rs.getDate("date_debut").toLocalDate());
				article.setDateFinEnchere(rs.getDate("date_fin").toLocalDate());
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setIdUser(rs.getInt("idUserArticle"));
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("idUser"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				
				article.setUtilisateur(utilisateur);
				
				listeArticlesPourTri.add(article);
				
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return listeArticlesPourTri;
	}

	//  SELECT a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.prix_vente,
	//a.idUserArticle, u.pseudo, u.telephone
	//FROM ARTICLES a LEFT JOIN USERS u ON a.idUserArticle = u.idUser 
	@Override
	public List<Article> getListeVentesOuvertes() {
		System.out.println("*****************ARTICLE JDBC*************");
		List<Article> listeVentesOuvertes = new ArrayList<Article>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(LISTE_VENTES_OUVERTES);
			ResultSet rs = rqt.executeQuery();
			
			while (rs.next()) {
				Article article = new Article();
				article.setIdArticle(rs.getInt("idArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDescriptionArticle(rs.getString("description"));
				article.setDateDebutEnchere(rs.getDate("date_debut").toLocalDate());
				article.setDateFinEnchere(rs.getDate("date_fin").toLocalDate());
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setIdUser(rs.getInt("idUserArticle"));
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setTelephone(rs.getString("telephone"));
				
				Categories categorie = new Categories();
				categorie.setLibelle(rs.getString("libelle"));
				
				Enchere enchere = new Enchere();
				
				Integer idUserEnchere = rs.getInt("idUserEnchere");
				if (idUserEnchere != null) {
					enchere.setNoUtilisateur(rs.getInt("idUSerEnchere"));
					
										
				} else {
					
					enchere.setNoUtilisateur(0);
				}
				
				//enchere.setNoUtilisateur(rs.getInt("idUserEnchere"));

				article.setUtilisateur(utilisateur);
				article.setCategorie(categorie);
				
				article.setEnchere(enchere);
				
				listeVentesOuvertes.add(article);
				
				
				
				
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listeVentesOuvertes;
	}
	
	
	
	
	

}
