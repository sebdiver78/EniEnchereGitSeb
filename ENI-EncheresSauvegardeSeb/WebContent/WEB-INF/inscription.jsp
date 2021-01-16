<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>inscription</title>
</head>
<body>

	<div class="profil">
	<form method="post" action="Ajout">
		<div class="col1">
			<label for="Pseudo">Pseudo :</label>
			<input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo }"/>"/>
			<span>${erreurs['pseudo']}</span>
			<!--  -->
			<label for="Prenom">Prénom :</label>
			<input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateur.prenom }"/>"/>
			<span>${erreurs['prenom']}</span>
			
			<label for="telephone">Téléphone :</label>
			<input type="text" id="telephone" name="telephone" value="<c:out value="${utilisateur.telephone }"/>"/>
			<span>${erreurs['telephone']}</span>
			
			<label for="codePostal">Code Postal :</label>
			<input type="number" id="codePostal" name="codePostal" value="<c:out value="${utilisateur.codePostal }"/>"/>
			<span>${erreurs['codePostal']}</span>
			
			<label for="nom">Nom :</label>
			<input type="text" id="nom" name="nom" value="<c:out value="${utilisateur.nom }"/>"/>
			<span>${erreurs['nom']}</span>
			
			<label for="motDePasse">Mot de Passe :</label>
			<input type="password" id="motDePasse" name="motDePasse" value="<c:out value="${utilisateur.motDePasse }"/>">
			<span>${erreurs['motDePasse']}</span>
			
			<label for="email">Email :</label>
			<input type="email" id="email" name="email" value="<c:out value="${utilisateur.email }"/>"/>
			<span>${erreurs['email']}</span>
			
			<label for="rue">Rue :</label>
			<input type="text" id="rue" name="rue" value="<c:out value="${utilisateur.rue }"/>"/>
			<span>${erreurs['rue']}</span>
			
			<label for="ville">Ville :</label>
			<input type="text" id="ville" name="ville" value="<c:out value="${utilisateur.ville }"/>"/>
			<span>${erreurs['ville']}</span>
			
			<label for="confirmation">Confirmation :</label>
			<input type="password" id="confirmation" name="confirmation"/>
	</div>
	
		<input type="submit" value="Créer"/>
		
		<a href="<%=request.getContextPath() %>/">Annuler</a>
	
	<p>${form.resultat}</p> 
	<p>${erreurs['motDePasse']} </p>
	
		
	</form>
	
		
	
	
	
	
	
	
	
	
	</div>


</body>
</html>