package fr.eni.enchere.dal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public interface EnchereDAO {
	
	public void insertEnchere(Enchere enchere);
	public Utilisateur getEnchereByUser();
	public Article getEnchereByArticle();
	public List<Enchere> getListeEncheres();
	public void deleteEnchere( int idArticle);
	public void updateEnchere(int idArticle, int idUserSession, int montant, LocalDate date);
	public List<Enchere> getListeEncheresPourTri();
	public List<Article> getOuvert();
	
}
