package fr.eni.enchere.bo;

public class Categories {
	
	int idCategorie;
	String libelle;
	
	
	
	public Categories() {
		super();
	}



	public Categories(int idCategorie, String libelle) {
		super();
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}



	public int getIdCategorie() {
		return idCategorie;
	}



	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	@Override
	public String toString() {
		return "Categories [idCategorie=" + idCategorie + ", libelle=" + libelle + "]";
	}
	
	
	
	

}
