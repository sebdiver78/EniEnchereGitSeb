package fr.eni.enchere.bo;

public class Retrait {
	
	int idRetrait;
	String rue;
	String codePostal;
	String Ville;
	Utilisateur utilisateur;
	
	
	public Retrait() {
		super();
	}


	public Retrait(int idRetrait, String rue, String codePostal, String ville) {
		super();
		this.idRetrait = idRetrait;
		this.rue = rue;
		this.codePostal = codePostal;
		Ville = ville;
	}


	public int getIdRetrait() {
		return idRetrait;
	}


	public void setIdRetrait(int idRetrait) {
		this.idRetrait = idRetrait;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return Ville;
	}


	public void setVille(String ville) {
		Ville = ville;
	}


	@Override
	public String toString() {
		return "Retrait [idRetrait=" + idRetrait + ", rue=" + rue + ", codePostal=" + codePostal + ", Ville=" + Ville
				+ "]";
	}
	
	
	
	

}
