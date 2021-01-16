package fr.eni.enchere.dal;

import java.util.List; 

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insertUtilisateur(Utilisateur utilisateur);
	public Utilisateur selectByEmail(String email);
	public Utilisateur selectById(int id);
	public List<String> getListeEmails();
	public List<String> getListePseudo();
	public List<Utilisateur> getListeIdPseudo();
	
	public void deleteUtilisateur(int id);
	public void updateProfil(Utilisateur utilisateur);
	public void updateCredit(int credit, int idUser);

}
