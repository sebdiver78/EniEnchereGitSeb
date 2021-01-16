package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (montant, idArticle, idUserEnchere) VALUES (?,?,?)";
	private static final String GET_ALL_ENCHERE = "SELECT * FROM ENCHERES ";
	private static final String DELETE_ENCHERE = "DELETE FROM ENCHERES WHERE idArticle = ?";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET idUserEnchere = ?, montant= ?, date = ? WHERE idArticle =?";
	private static final String GET_ALL_ENCHERES_TRI = "SELECT e.montant, e.idArticle, e.idUserEnchere, \r\n" + 
			"	a.idUserArticle, a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.idRetrait, a.prix_initial, a.prix_vente, \r\n" + 
			"	u.idUser, u.pseudo \r\n" + 
			"	FROM ENCHERES e INNER JOIN ARTICLES a ON e.idArticle = a.idArticle\r\n" + 
			"					INNER JOIN USERS u ON e.idUserEnchere = u.idUser\r\n";
	private static final String LISTE_VENTES_OUVERTES = "SELECT a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.prix_vente," +
			"a.idUserArticle, u.pseudo, u.telephone, c.libelle, e.idUserEnchere "+
			"FROM ARTICLES a LEFT JOIN USERS u ON a.idUserArticle = u.idUser "	+
			"INNER JOIN CATEGORIES c ON  a.idCategorie = c.idCategorie "+
			"FULL JOIN ENCHERES e ON a.idArticle = e.idArticle ";
			
			//"WHERE a.date_debut < GETDATE() AND a.date_fin > GETDATE()";
							
	
	
	
	@Override
	public void insertEnchere(Enchere enchere) {
		
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ENCHERE);
			rqt.setInt(1, enchere.getMontantEnchere());
			rqt.setInt(2, enchere.getIdArticle());
			rqt.setInt(3, enchere.getNoUtilisateur());
			rqt.executeUpdate();

			System.out.println("INSERT ENCHERE");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public Utilisateur getEnchereByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article getEnchereByArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> getListeEncheres() {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement rqt = cnx.prepareStatement(GET_ALL_ENCHERE);
			
			ResultSet rs = rqt.executeQuery();
			
			while (rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setIdArticle(rs.getInt("idArticle"));
				enchere.setNoUtilisateur(rs.getInt("idUserEnchere"));
				enchere.setMontantEnchere(rs.getInt("montant"));
				
				listeEncheres.add(enchere);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("**************ENCHERE JDBC LISTE**********");
		System.out.println("liste encheres = "+ listeEncheres.toString());
		return listeEncheres;
	}

	@Override
	public void deleteEnchere(int idArticle) {
		System.out.println("DELETE ENCHERE JDBC");
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(DELETE_ENCHERE);
			rqt.setInt(1, idArticle);
			rqt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void updateEnchere(int idArticle, int idUserSession, int montant, LocalDate date) {
		// TODO Auto-generated method stub
try(Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ENCHERE);
			
			//String dateConv = date.toString();
			rqt = cnx.prepareStatement(UPDATE_ENCHERE);
			rqt.setInt(1, idUserSession);
			rqt.setInt(2, montant);
			rqt.setDate(3, java.sql.Date.valueOf(date));
			rqt.setInt(4, idArticle);
			
			rqt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}



	@Override
	public List<Enchere> getListeEncheresPourTri() {
/*"SELECT e.montant, e.idArticle, e.idUser, \r\n" + 
		"	a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.idRetrait, a.prix_initial, a.prix_vente, \r\n" + 
		"	u.idUser, u.pseudo \r\n" + 
		"	FROM ENCHERES e INNER JOIN ARTICLES a ON e.idArticle = a.idArticle\r\n" + 
		"					INNER JOIN USERS u ON e.idUser = u.idUser\r\n";		
		*/
		List<Enchere> listeEncheresPourTri = new ArrayList<Enchere>();
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(GET_ALL_ENCHERES_TRI);
			ResultSet rs = rqt.executeQuery();
			
			while(rs.next()) {
				
				Article article = new Article();
				article.setIdUser(rs.getInt("idUserArticle"));
				article.setIdArticle(rs.getInt("idArticle"));
				article.setNomArticle(rs.getString("nom"));
				article.setDescriptionArticle(rs.getString("description"));
				article.setDateDebutEnchere((rs.getDate("date_debut").toLocalDate()));
				article.setDateFinEnchere((rs.getDate("date_fin").toLocalDate()));
				article.setIdRetrait(rs.getInt("idRetrait"));
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("idUSer"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				
				Enchere enchere = new Enchere();
				enchere.setNoUtilisateur(rs.getInt("idUserEnchere"));
				enchere.setIdArticle(rs.getInt("idArticle"));
				
				enchere.setUtilisateur(utilisateur);
				enchere.setArticle(article);
				
				listeEncheresPourTri.add(enchere);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeEncheresPourTri;
	}

	@Override
	public List<Article> getOuvert() {
	//  SELECT a.idArticle, a.nom, a.description, a.date_debut, a.date_fin, a.prix_vente,
		//a.idUserArticle, u.pseudo, u.telephone
		//FROM ARTICLES a LEFT JOIN USERS u ON a.idUserArticle = u.idUser 
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
					
					//System.out.println("listeEnchOuv "+listeVentesOuvertes.toString());
					listeVentesOuvertes.add(article);
					
					
					
					
					
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return listeVentesOuvertes;
		}


	
		
		
		
	


		
	

	

}
