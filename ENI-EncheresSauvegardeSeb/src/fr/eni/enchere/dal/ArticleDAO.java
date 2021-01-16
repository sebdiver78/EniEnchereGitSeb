package fr.eni.enchere.dal;

import java.util.List; 

import fr.eni.enchere.bo.Article;
import fr.eni.enchere.bo.Categories;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;

public interface ArticleDAO {
	
	public void insertArticle(Article article, Retrait retrait);
	public List<Article> getAllArticles();
	public Article getArticleById(int id);
	public List<Categories> getAllCategories();
	public Enchere getEnchereByArticle(int idArticle);
	public void updateEnchereArticle(int idArticle, int prixVente);
	public List<Article> getListeArticlesPourTri();
	public List<Article> getListeVentesOuvertes();

}
