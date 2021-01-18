<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Pour récupérer le fichier css  -->
	<style><%@include file="../../styleCss/style.css"%></style> 
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/styleCss/style.css" /> --%> 

   					 <title>ENI-Encheres profil</title>
  </head>
<body>
    <div class="container" style="margin-top: 20px">
        <div class="row">
             <div class="col-sm-8">
                <a href="<%=request.getContextPath() %>/AccueilSession "><img id="logo-encheres" src="./images/logo-eni-encheres.png" alt="photo ici"></a>
            </div> 
            <div class="col-sm-12" style="text-align: right; margin-bottom: 20px"> 
                <a href="<%=request.getContextPath() %>#">Enchères</a>
				<a href="<%=request.getContextPath() %>/VendreUnArticle">Vendre un article</a>
											      <!-- /rien pour retour à l'accueil -->
				<a href="<%=request.getContextPath() %>/Connexion">Déconnexion</a> 
            </div>
        	<p>Crédit disponible : ${sessionScope.sessionUtilisateur.credit }</p>
        </div>
    </div>   
               <h4 style="text-align: center">Mon profil</h4>
                   <div class="container" style="margin-top: 20px">
               
          <div class="row" style="margin-top:20px">
              <div class="col-lg-12 col-lg-offset-1">
	<form method="post" action="Update?id=${sessionScope.sessionUtilisateur.noUtilisateur}">
   <div class="row">
                          <div class="col-md-6">
                              <label for="Pseudo">Pseudo : *</label>
                              <input type="text" id="pseudo" name="pseudo" class="form-control" value="<c:out value=" ${utilisateur.pseudo }" />"/>
                              <span>${erreurs['pseudo']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="nom">Nom : *</label>
							  <input type="text" id="nom" name="nom" class="form-control" value="<c:out value="${utilisateur.nom }"/>"/>
							  <span>${erreurs['nom']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="Prenom">Prénom : *</label>
                              <input type="text" id="prenom" name="prenom" class="form-control" value="<c:out value=" ${utilisateur.prenom }" />"/>
                              <span>${erreurs['prenom']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="email">Email : *</label>
							  <input type="email" id="email" name="email" class="form-control" value="<c:out value="${utilisateur.email }"/>"/>
							  <span>${erreurs['email']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="telephone">Téléphone : *</label>
							  <input type="text" id="telephone" name="telephone" class="form-control" value="<c:out value="${utilisateur.telephone }"/>"/>
							  <span>${erreurs['telephone']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="rue">Rue : *</label>
							  <input type="text" id="rue" name="rue" class="form-control" value="<c:out value="${utilisateur.rue }"/>"/>
							  <span>${erreurs['rue']}</span>
                              <p class="comments"></p>
                          </div><div class="col-md-6">
							  <label for="codePostal">Code Postal : *</label>
							  <input type="number" id="codePostal" name="codePostal" class="form-control" value="<c:out value="${utilisateur.codePostal }"/>"/>
							  <span>${erreurs['codePostal']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="ville">Ville :</label>
							  <input type="text" id="ville" name="ville" class="form-control" value="<c:out value="${utilisateur.ville }"/>"/>
							  <span>${erreurs['ville']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="motDePasse">Mot de passe actuel :</label>
							<input type="password" id="motDePasse" name="motDePasse" class="form-control" value="<c:out value="${utilisateur.motDePasse }"/>">
							<span>${erreurs['motDePasse']}</span>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-6">
                              <label for="motDePasse">Nouveau mot de passe :</label>
							<input type="password" id="newMotDePasse" name="newMotDePasse" class="form-control" >
					<span>${erreurs['motDePasse']}</span>
                              <p class="comments"></p>
                          </div>
                          
                          <div class="col-md-6">
                              <label for="confirmation">Confirmation nouveau mot de passe: *</label>
							  <input type="password" id="confirmation" name="confirmation" class="form-control"/>
                              <p class="comments"></p>
                          </div>
                          <div class="col-md-12">
                              <p><strong>* Ces informations sont requises</strong></p>
                          </div>
             </div>
             <p>Crédit : ${utilisateur.credit}</p>
	
		<input type="submit" class="btn btn-outline-primary btn-lg" style="margin-top: 10px" value="Enregistrer"/>
		
		<a href="Delete?id=${sessionScope.sessionUtilisateur.noUtilisateur}"><input type="button" class="btn btn-outline-primary btn-lg" style="margin-top: 10px; margin-left: 15px" value="Supprimer mon compte"/></a>
	
	<p>${form.resultat}</p> 
	<p>${erreurs['motDePasse']} </p>
	
	</form>
	</div>
	</div>
	</div>
	<footer>
      	<div class="barblanc"><img id="logo-eni" src="./images/logo-eni.png" alt="photo ici"></div>
      	<div class="barbleu"><p class="nous">Réalisé par Sebastien et Lorris</p></div>
      </footer>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
             
             
             
             
        