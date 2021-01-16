package fr.eni.enchere.bll;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	public static final String CHAMP_PSEUDO ="pseudo";
	public static final String CHAMP_PRENOM = "prenom";
	public static final String CHAMP_TELEPHONE = "telephone";
	public static final String CHAMP_CPOSTAL = "codePostal";
	public static final String CHAMP_NOM = "nom";
	public static final String CHAMP_MOT_PASSE = "motDePasse";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_RUE = "rue";
	public static final String CHAMP_VILLE = "ville";
	public static final String CHAMP_CONF = "confirmation";	
	
	
	private List<String> listeEmails = null;
	private String resultat;
	//liste erreurs
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public String getResultat() {
		return resultat;
	}
	
	public Map<String, String> getErreur() {
		return erreurs;
	}
	
	
	
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}
	
	
	private UtilisateurDAO utilisateurDAO;//interface
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	
	
	
	
	public List<String> getListeEmail() {
		
		List<String> listeEmails = new ArrayList<String>();
		listeEmails=  this.utilisateurDAO.getListeEmails();
		
		return listeEmails;
		
		
	}
	
	
	public List<String> getListePseudo() {
		
		List<String> listePseudo = new ArrayList<String>();
		listePseudo = this.utilisateurDAO.getListePseudo();
		
		return listePseudo;
		
	}
	
	
	public List<Utilisateur> getlisteIdPseudo() {
		List<Utilisateur> listeIdPseudo = new ArrayList<Utilisateur>();
		listeIdPseudo = this.utilisateurDAO.getListeIdPseudo();
		return listeIdPseudo;
		
	}
	
	public Utilisateur selectUtilisateurId(int id) {
		
		Utilisateur utilisateurBDD = new Utilisateur();
		utilisateurBDD = this.utilisateurDAO.selectById(id);
		
		return utilisateurBDD;
	}
	
	
	public Utilisateur selectUtilisateurEmail(String email) {
			
		Utilisateur utilisateurBDD = new Utilisateur();
		utilisateurBDD = this.utilisateurDAO.selectByEmail(email);
		
		return utilisateurBDD;
	}
	
		public void deleteUtilisateur(int id) {
			
			utilisateurDAO.deleteUtilisateur(id);
			System.out.println("ARTICLE MANAGER DELETE OK");
		}
	
	
		public void UpdateCredit(int credit, int idUser) {
			
			utilisateurDAO.updateCredit(credit, idUser);
			
		}
		
		
		public Utilisateur UpdateUtilisateur(int noUtilisateur,String pseudo,String prenom,String telephone,String codePostal,String nom,String motDePasse,String email,String rue,String ville,String confirmation,String newMotDePasse) {
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(noUtilisateur);
			
		
			try {
				validationUpdatePseudo(pseudo);
			} catch (Exception e) {
				setErreur(CHAMP_PSEUDO, e.getMessage());
			}
			utilisateur.setPseudo(pseudo);
			
			try {
				validationPrenom(prenom);	
			} catch (Exception e) {
				setErreur(CHAMP_PRENOM, e.getMessage());
			}
			utilisateur.setPrenom(prenom);
			
			try {
				validationTelephone(telephone);	
			} catch (Exception e) {
				setErreur(CHAMP_TELEPHONE, e.getMessage());
			}
			utilisateur.setTelephone(telephone);
					
			try {
				validationNom(nom);	
			} catch (Exception e) {
				setErreur(CHAMP_NOM, e.getMessage());
			}
			utilisateur.setNom(nom);
			
			
			
			try {
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				utilisateur.setMotDePasse(utilisateurManager.validationUpdateMotsDePasse(motDePasse, confirmation, newMotDePasse));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}System.out.println("setMDP "+ motDePasse + confirmation + newMotDePasse);
			
			
			
			
			
			try {
				validationUpdateEmail(email);
			} catch (Exception e) {
				setErreur(CHAMP_EMAIL, e.getMessage());
			}
			utilisateur.setEmail(email);
			
			try {
				validationRue(rue);	
			} catch (Exception e) {
				setErreur(CHAMP_RUE, e.getMessage());
			}
			utilisateur.setRue(rue);
			
			try {
				validationVille(ville);	
			} catch (Exception e) {
				setErreur(CHAMP_VILLE, e.getMessage());
			}
			utilisateur.setVille(ville);
			
			
			
			try {
				validationCodePostal(codePostal);
			} catch (Exception e) {
				setErreur(CHAMP_CPOSTAL, e.getMessage());
			}
			utilisateur.setCodePostal(codePostal);
			
			
			
			if (erreurs.isEmpty()) {
				resultat = "Mise à jour OK";
				utilisateurDAO.updateProfil(utilisateur);
			} else {
				
				resultat = "Echec Mise à jour";
			}
			System.out.println("********utilisateurManager***********");
			System.out.println("resultat = " + resultat);
			System.out.println(erreurs.toString());
			
			
		
		
		
		
		return utilisateur;
		}
		
	
	
	
	public Utilisateur RecupUtilisateurRequete(HttpServletRequest request) {
		Utilisateur utilisateur = new Utilisateur();
		
		String pseudo = request.getParameter(CHAMP_PSEUDO);
		String nom = request.getParameter(CHAMP_NOM);
		String prenom = request.getParameter(CHAMP_PRENOM);
		String email = request.getParameter(CHAMP_EMAIL);
		String telephone = request.getParameter(CHAMP_TELEPHONE);
		String rue = request.getParameter(CHAMP_RUE);
		String codePostal = request.getParameter(CHAMP_CPOSTAL);
		String ville = request.getParameter(CHAMP_VILLE);
		String motDePasse = request.getParameter(CHAMP_MOT_PASSE);
		
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setMotDePasse(motDePasse);
		
		
		return utilisateur;
		
	}
	
	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		 
		//Map<String, String> erreurs = new HashMap<String, String>();
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		
		Utilisateur utilisateurBDD = new Utilisateur();
		UtilisateurManager userBDD = new UtilisateurManager();
		utilisateurBDD = userBDD.selectUtilisateurEmail(email);
		String emailBDD = utilisateurBDD.getEmail();
		String motDePasseBDD = utilisateurBDD.getMotDePasse();
		int idBDD = utilisateurBDD.getNoUtilisateur();
		String pseudo = utilisateurBDD.getPseudo();
		String nom = utilisateurBDD.getNom();
		String prenom = utilisateurBDD.getPrenom();
		String telephone = utilisateurBDD.getTelephone();
		String rue = utilisateurBDD.getRue();
		String codePostal = utilisateurBDD.getCodePostal();
		String ville = utilisateurBDD.getVille();
		int credit = utilisateurBDD.getCredit();
				
		Utilisateur utilisateur = new Utilisateur();
		System.out.println("*********UtilisateurManager 1***************");
		System.out.println("userBDD"+utilisateurBDD);
		System.out.println("***********************");
		
		try { 
			validationEmailConnexion(email);
			
		} catch (Exception e) {
			
			setErreur (CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email);
		
		
		try {
			validationMotDePasse(motDePasse, motDePasseBDD);
		} catch (Exception e) {
			
			setErreur (CHAMP_MOT_PASSE, e.getMessage());
		
	} 
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setCredit(credit);
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setNoUtilisateur(idBDD);
		
		
		if (erreurs.isEmpty()) {
			resultat="Succes connexion";
			
		} else {
			resultat= "echec connexion";
		}
		System.out.println("**********UtilisateurManager 2 ***************");
		System.out.println(resultat);
		System.out.println(erreurs.toString());
		System.out.println("utilisateur connexion"+ utilisateur.toString());
		System.out.println("***********************");
		return utilisateur;
		
	}
	
	
public Utilisateur validerInsererUtilisateur(String pseudo,String prenom,String telephone,String codePostal,String nom,String motDePasse,String email,String rue,String ville,String confirmation) {
		/*
		String pseudo = request.getParameter(CHAMP_PSEUDO);
		String prenom = request.getParameter(CHAMP_PRENOM);
		String telephone = request.getParameter(CHAMP_TELEPHONE);
		String codePostal = request.getParameter(CHAMP_CPOSTAL);
		String nom = request.getParameter(CHAMP_NOM);
		String motDePasse = request.getParameter(CHAMP_MOT_PASSE);
		String email = request.getParameter(CHAMP_EMAIL);
		String rue = request.getParameter(CHAMP_RUE);
		String ville = request.getParameter(CHAMP_VILLE);
		String confirmation = request.getParameter(CHAMP_CONF);
		*/
		Utilisateur utilisateur = new Utilisateur();
		
		
		try {
			validationPseudo(pseudo);	
		} catch (Exception e) {
			setErreur(CHAMP_PSEUDO, e.getMessage());
		}
		utilisateur.setPseudo(pseudo);
		
		try {
			validationPrenom(prenom);	
		} catch (Exception e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		utilisateur.setPrenom(prenom);
		
		try {
			validationTelephone(telephone);	
		} catch (Exception e) {
			setErreur(CHAMP_TELEPHONE, e.getMessage());
		}
		utilisateur.setTelephone(telephone);
				
		try {
			validationNom(nom);	
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		utilisateur.setNom(nom);
		
		try {
			validationMotsDePasse(motDePasse, confirmation);	
		} catch (Exception e) {
			setErreur(CHAMP_MOT_PASSE, e.getMessage());
			setErreur(CHAMP_CONF, e.getMessage());
		}
		utilisateur.setMotDePasse(motDePasse);
		
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email);
		
		try {
			validationRue(rue);	
		} catch (Exception e) {
			setErreur(CHAMP_RUE, e.getMessage());
		}
		utilisateur.setRue(rue);
		
		try {
			validationVille(ville);	
		} catch (Exception e) {
			setErreur(CHAMP_VILLE, e.getMessage());
		}
		utilisateur.setVille(ville);
		
		try {
			validationPseudo(pseudo);	
		} catch (Exception e) {
			setErreur(CHAMP_PSEUDO, e.getMessage());
		}
		utilisateur.setPseudo(pseudo);
		
		try {
			validationCodePostal(codePostal);
		} catch (Exception e) {
			setErreur(CHAMP_CPOSTAL, e.getMessage());
		}
		utilisateur.setCodePostal(codePostal);
		
		
		
		if (erreurs.isEmpty()) {
			resultat = "Inscription OK";
			utilisateurDAO.insertUtilisateur(utilisateur);
		} else {
			
			resultat = "Echec inscription";
		}
		
		
		
		return utilisateur;
	}
	
private void validationEmailConnexion(String email) throws Exception{
	
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	Utilisateur userBDD = utilisateurManager.selectUtilisateurEmail(email);
	String retourEmail = userBDD.getEmail();
	List<String> listeEmails = new ArrayList<String>();
	listeEmails = utilisateurManager.getListeEmail();
	System.out.println("********validationEmailConnexion**********");
	System.out.println(listeEmails.toString());
	System.out.println("***********************************");
	
	if (email !=null ) {
		if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse valide");
		} else {
			if (retourEmail == null) {
				
				throw new Exception("Pas de compte associé");
			}
			
			
	
	}
	}else {
		throw new Exception("Merci de saisir un email");
		
	}
	
	
}//fin validationConnexion
	
	
	private void validationEmail(String email) throws Exception{
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<String> listeEmails = new ArrayList<String>();
		listeEmails = utilisateurManager.getListeEmail();
		
		if (email !=null ) {
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse valide");
			} else {
				
			for (String emailBDD: listeEmails) {
				if (email.equals(emailBDD)) {
					throw new Exception("Email déjà utilisé");
				}
				
			}
		}
		}else {
			throw new Exception("Merci de saisir un email");
			
		}
		
		
	}//fin validation
	
	private void validationUpdateEmail(String email) throws Exception{
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<String> listeEmails = new ArrayList<String>();
		listeEmails = utilisateurManager.getListeEmail();
		
			listeEmails.remove(email);
			
				
		if (email !=null ) {
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse valide");
			} else {
				
			for (String emailBDD: listeEmails) {
				if (email.equals(emailBDD)) {
					throw new Exception("Email déjà utilisé");
				}
				
			}
		}
		}else {
			throw new Exception("Merci de saisir un email");
			
		}
		
		
	}//fin validation
	
	private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		 if (motDePasse !=null && confirmation != null) {
			 
			 if(!motDePasse.contentEquals(confirmation)) {
				 throw new Exception("Mots de passe différents");
			 } else if (motDePasse.length()<4) {
				 throw new Exception("Mot de passe supérieur à 4 car");
			 }
		 } else {
			 throw new Exception ("Merci de saisir un mot de passe");
		 }
		
	}//fin validationmdp
	
	private String validationUpdateMotsDePasse(String motDePasse, String confirmation, String newMotDePasse) throws Exception {
		System.out.println("Confimration"+confirmation+ " NEWMDP "+newMotDePasse);
		 if (!confirmation.equals("") && !newMotDePasse.contentEquals("")) {
			 
						 
			 if(!newMotDePasse.contentEquals(confirmation)) {
				 throw new Exception("Mots de passe différents");
			 } else if (newMotDePasse.length()<4) {
				 throw new Exception("Mot de passe supérieur à 4 car");
			 } motDePasse = newMotDePasse;
			 
		 } 
		 System.out.println("mdp = "+motDePasse+" "+ "newMDP= "+ newMotDePasse);
		 return motDePasse;
	}//fin validationmdp
	
	
	private void validationPseudo(String pseudo) throws Exception {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<String> listePseudo = new ArrayList<String>();
		listePseudo = utilisateurManager.getListePseudo();
		
		if (pseudo == null || pseudo =="" ) {
			throw new Exception("Saisir un pseudo");
		} else {
			for (String pseudoBDD : listePseudo) {
				if (pseudo.equals(pseudoBDD)) {
					
					throw new Exception("Pseudo déjà utilisé");
				}
				
			}
			
			
		}
	}
	
	private void validationUpdatePseudo(String pseudo) throws Exception {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<String> listePseudo = new ArrayList<String>();
		listePseudo = utilisateurManager.getListePseudo();
		
			listePseudo.remove(pseudo);
			
		
		
		
		if (pseudo == null || pseudo =="" ) {
			throw new Exception("Saisir un pseudo");
		} else {
			for (String pseudoBDD : listePseudo) {
				if (pseudo.equals(pseudoBDD)) {
					
					throw new Exception("Pseudo déjà utilisé");
				}
				
			}
			
			
		}
	}
	
private void validationPrenom(String prenom) throws Exception {
		
		if (prenom == null || prenom =="" ) {
			throw new Exception("Saisir un prénom");
		}
	}

private void validationTelephone(String telephone) throws Exception {
	
	if (telephone == null || telephone =="" ) {
		throw new Exception("Saisir un numero");
	}
}

private void validationNom(String nom) throws Exception {
	
	if (nom == null || nom =="" ) {
		throw new Exception("Saisir un nom");
	}
}
	
private void validationRue(String rue) throws Exception {
	
	if (rue == null || rue =="" ) {
		throw new Exception("Saisir une rue");
	}
}

private void validationVille(String ville) throws Exception {
	
	if (ville == null || ville =="" ) {
		throw new Exception("Saisir une ville");
	}
}


private void validationCodePostal(String codePostal) throws Exception {
	
	if (codePostal != null ) {
		if (codePostal.length()<5) {
		throw new Exception("Saisir 5 chiffres");
	}
} else {
	throw new Exception ("saisir un code postal");
}
}

private void validationMotDePasse (String motDePasse, String motDePasseBDD) throws Exception {
	
	if (motDePasse != null) {
		
		if (!motDePasse.equals(motDePasseBDD) ) {
			
			throw new Exception("Erreur mot de passe");
		}
	} else {
		throw new Exception (" saisir un mot de passe");
	}


}

}
	


