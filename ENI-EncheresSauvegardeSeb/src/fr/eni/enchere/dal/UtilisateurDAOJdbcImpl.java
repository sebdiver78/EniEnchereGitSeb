package fr.eni.enchere.dal;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static final String INSERT="INSERT INTO USERS (pseudo, nom, prenom, email, telephone,rue,code_postal, ville, mot_de_passe) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM USERS WHERE email = ?";
	private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE idUser = ?";
	private static final String GET_ALL_EMAIL = "SELECT email FROM USERS ";
	private static final String GET_ALL_PSEUDO = "SELECT pseudo FROM USERS";
	private static final String UPDATE = "UPDATE USERS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?,ville=?, mot_de_passe=? WHERE idUser=?";
	private static final String DELETE_USER = "DELETE FROM USERS WHERE idUser=?";
	private static final String UPDATE_CREDIT = "UPDATE USERS SET credit=? WHERE idUser = ?";
	private static final String GET_LIST_IDPSEUDO = "SELECT idUser, pseudo FROM USERS";
	
	@Override
	
	
	public void insertUtilisateur(Utilisateur utilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement rqt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			rqt.setString(1, utilisateur.getPseudo());
			rqt.setString(2, utilisateur.getNom());
			rqt.setString(3, utilisateur.getPrenom());
			rqt.setString(4, utilisateur.getEmail());
			rqt.setString(5, utilisateur.getTelephone());
			rqt.setString(6, utilisateur.getRue());
			rqt.setString(7, utilisateur.getCodePostal());
			rqt.setString(8, utilisateur.getVille());
			rqt.setString(9,  utilisateur.getMotDePasse());
			
			rqt.executeUpdate();
			ResultSet rs = rqt.getGeneratedKeys();
			if(rs.next()) {
				
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public Utilisateur selectByEmail(String email) {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_EMAIL);
			rqt.setString(1, email);
			ResultSet rs = rqt.executeQuery();
			
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("idUser"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}

	public Utilisateur selectById(int id) {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_ID);
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("idUser"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	

	@Override
	public void deleteUtilisateur(int id) {
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement rqt = cnx.prepareStatement(DELETE_USER);
			rqt.setInt(1, id);
			rqt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> getListeEmails() {
		List<String> listeEmails= new ArrayList<String>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			
			PreparedStatement rqt = cnx.prepareStatement(GET_ALL_EMAIL);
			ResultSet rs = rqt.executeQuery();
			while(rs.next()) {
				String email = rs.getString("email");
				listeEmails.add(email);
						
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listeEmails;
	}

	@Override
	public List<String> getListePseudo() {
		List<String> listePseudo = new ArrayList<String>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement rqt = cnx.prepareStatement(GET_ALL_PSEUDO);
			ResultSet rs = rqt.executeQuery();
			
			while (rs.next()) {
				String pseudo = rs.getString("pseudo");
				listePseudo.add(pseudo);
			
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listePseudo;
	}

	@Override
	public void updateProfil(Utilisateur utilisateur) {
		System.out.println("utilisateur updateJDBC" + utilisateur);
//""UPDATE USERS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?,ville=?, mot_de_passe=? WHERE id=?"		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(UPDATE);
			rqt.setString(1, utilisateur.getPseudo());
			rqt.setString(2, utilisateur.getNom());
			rqt.setString(3, utilisateur.getPrenom());
			rqt.setString(4, utilisateur.getEmail());
			rqt.setString(5, utilisateur.getTelephone());
			rqt.setString(6, utilisateur.getRue());
			rqt.setString(7,  utilisateur.getCodePostal());
			rqt.setString(8, utilisateur.getVille());
			rqt.setString(9, utilisateur.getMotDePasse());
			rqt.setInt(10, utilisateur.getNoUtilisateur());
			
			rqt.executeUpdate();
			System.out.println("*********JDBC**********");
			System.out.println("updateJDBC OK");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateCredit(int credit, int idUser) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_CREDIT);
			rqt.setInt(1, credit);
			rqt.setInt(2, idUser);
			
			rqt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Utilisateur> getListeIdPseudo() {
		List<Utilisateur> listeIdPseudo = new ArrayList<Utilisateur>();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement rqt = cnx.prepareStatement(GET_LIST_IDPSEUDO);
			ResultSet rs = rqt.executeQuery();
			
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur(rs.getInt("idUser"),rs.getString("pseudo"));
				
				
				listeIdPseudo.add(utilisateur);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return listeIdPseudo;
	} 

}
