package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {
	
	private int idArticle;
	private int noUtilisateur;
	private LocalDate dateEnchere;
	private int montantEnchere;
	Utilisateur utilisateur;
	Article article;
	
	
	public Enchere() {
		super();
	}
	
	
	
	


	public Enchere(int idArticle, int noUtilisateur, LocalDate dateEnchere, int montantEnchere, Utilisateur utilisateur,
			Article article) {
		super();
		this.idArticle = idArticle;
		this.noUtilisateur = noUtilisateur;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
		this.article = article;
	}






	public Enchere(int idArticle, int noUtilisateur, int montantEnchere) {
		super();
		this.idArticle = idArticle;
		this.noUtilisateur = noUtilisateur;
		this.montantEnchere = montantEnchere;
	}




	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}


	


	public Enchere(int idArticle, int noUtilisateur, LocalDate dateEnchere, int montantEnchere,
			Utilisateur utilisateur) {
		super();
		this.idArticle = idArticle;
		this.noUtilisateur = noUtilisateur;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
	}


	public int getIdArticle() {
		return idArticle;
	}


	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}


	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}


	public LocalDate getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public int getMontantEnchere() {
		return montantEnchere;
	}


	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}






	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}






	public Article getArticle() {
		return article;
	}






	public void setArticle(Article article) {
		this.article = article;
	}






	@Override
	public String toString() {
		return "Enchere [idArticle=" + idArticle + ", noUtilisateur=" + noUtilisateur + ", dateEnchere=" + dateEnchere
				+ ", montantEnchere=" + montantEnchere + ", utilisateur=" + utilisateur + ", article=" + article + "]";
	}






	
	
	
	
	
	

}
