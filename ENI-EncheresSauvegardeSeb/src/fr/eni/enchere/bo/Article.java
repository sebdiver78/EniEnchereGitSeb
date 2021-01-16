package fr.eni.enchere.bo;

import java.time.LocalDate; 
import java.util.Date;

public class Article {
	
	int idArticle;
	int idUser;
	int idRetrait;
	String nomArticle;
	String descriptionArticle;
	int idCategorie;
	LocalDate dateDebutEnchere;
	LocalDate dateFinEnchere;
	int miseAPrix;
	int prixVente;
	Utilisateur utilisateur;
	Categories categorie;
	Retrait retrait;
	Enchere enchere;
	
	
	
	
	public Article() {
		super();
	}

	
	



	public Article(int idArticle, int idUser, String nomArticle, LocalDate dateDebutEnchere, LocalDate dateFinEnchere,
			int miseAPrix, int prixVente, Utilisateur utilisateur) {
		super();
		this.idArticle = idArticle;
		this.idUser = idUser;
		this.nomArticle = nomArticle;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
	}






	public Article(int idArticle, int idUser, int idRetrait, String nomArticle, String descriptionArticle,
			int idCategorie, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente,
			Utilisateur utilisateur, Retrait retrait, Categories categories) {
		super();
		this.idArticle = idArticle;
		this.idUser = idUser;
		this.idRetrait = idRetrait;
		this.nomArticle = nomArticle;
		this.descriptionArticle = descriptionArticle;
		this.idCategorie = idCategorie;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.retrait = retrait;
		this.categorie = categories;
	}






	public Article(int idArticle, int idUser, int idRetrait, String nomArticle, String descriptionArticle,
			int idCategorie, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente) {
		super();
		this.idArticle = idArticle;
		this.idUser = idUser;
		this.idRetrait = idRetrait;
		this.nomArticle = nomArticle;
		this.descriptionArticle = descriptionArticle;
		this.idCategorie = idCategorie;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}


	




	public Article(int idArticle, int idUser, int idRetrait, String nomArticle, String descriptionArticle,
			int idCategorie, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, int prixVente,
			Utilisateur utilisateur, Categories categorie, Retrait retrait, Enchere enchere) {
		super();
		this.idArticle = idArticle;
		this.idUser = idUser;
		this.idRetrait = idRetrait;
		this.nomArticle = nomArticle;
		this.descriptionArticle = descriptionArticle;
		this.idCategorie = idCategorie;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
		this.enchere = enchere;
	}






	public Utilisateur getUtilisateur() {
		return utilisateur;
	}






	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}






	public Retrait getRetrait() {
		return retrait;
	}






	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}






	public int getIdArticle() {
		return idArticle;
	}






	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}






	public int getIdUser() {
		return idUser;
	}






	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}






	public int getIdRetrait() {
		return idRetrait;
	}






	public void setIdRetrait(int idRetrait) {
		this.idRetrait = idRetrait;
	}






	public String getNomArticle() {
		return nomArticle;
	}






	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}






	public String getDescriptionArticle() {
		return descriptionArticle;
	}






	public void setDescriptionArticle(String descriptionArticle) {
		this.descriptionArticle = descriptionArticle;
	}






	public int getIdCategorie() {
		return idCategorie;
	}






	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}






	public LocalDate getDateDebutEnchere() {
		return dateDebutEnchere;
	}






	public void setDateDebutEnchere(LocalDate dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}






	public LocalDate getDateFinEnchere() {
		return dateFinEnchere;
	}






	public void setDateFinEnchere(LocalDate dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}






	public double getMiseAPrix() {
		return miseAPrix;
	}






	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}






	public double getPrixVente() {
		return prixVente;
	}






	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}






	public Categories getCategorie() {
		return categorie;
	}






	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}






	public Enchere getEnchere() {
		return enchere;
	}






	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}






	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", idUser=" + idUser + ", idRetrait=" + idRetrait + ", nomArticle="
				+ nomArticle + ", descriptionArticle=" + descriptionArticle + ", idCategorie=" + idCategorie
				+ ", dateDebutEnchere=" + dateDebutEnchere + ", dateFinEnchere=" + dateFinEnchere + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", utilisateur=" + utilisateur + ", categorie=" + categorie
				+ ", retrait=" + retrait + ", enchere=" + enchere + "]";
	}






	











	
	
	
	
	
}