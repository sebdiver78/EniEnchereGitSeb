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
	<style><%@include file="../styleCss/style.css"%></style> 
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/styleCss/style.css" /> --%>

    						 <title>ENI-Encheres profil vendeur</title>    
  </head>
  
  <body> 	
    <div class="container">
        <div class="row">
             <div class="col-sm-6">
                <a href="<%=request.getContextPath() %>/AccueilSession"><img id="logo-encheres" src="./images/logo-eni-encheres.png" alt="photo ici"></a>
           	 </div> 
	     	 <div class="col-sm-6" style="margin-top: 20px; margin-bottom: 20px"> 
	         	<a href="<%=request.getContextPath() %>#">Enchères</a>
			 	<a href="<%=request.getContextPath() %>/VendreUnArticle">Vendre un article</a>
	       	 	<a href="${pageContext.request.contextPath}/Profil?id=${sessionScope.sessionUtilisateur.noUtilisateur}">Mon profil</a>
			 	<a href="<%=request.getContextPath() %>/Connexion">Déconnexion</a> 
	         </div>
        </div>
    	<h4 style="text-align: center">Profil du vendeur</h4>
    	<br>
   		<div class="col-md-12" style="justify-content:center;">
   			<div class="row" >
		       	<p style="text-align: center;">Pseudo : ${utilisateur.pseudo }</p>
				<p style="text-align: center;">Nom : ${utilisateur.nom }</p>			
				<p style="text-align: center;">Prénom : ${utilisateur.prenom }</p>
				<p style="text-align: center;">Email : ${utilisateur.telephone }</p>
				<p style="text-align: center;">Rue : ${utilisateur.rue }</p>			
				<p style="text-align: center;">Code Postal ${utilisateur.codePostal }</p>			
				<p style="text-align: center;">Ville ${utilisateur.ville }</p>	
	   		</div>
	   		<br>
	   		<div class="d-grid gap-2 col-6 mx-auto" style="justify-content:center;">		
				<a href="<%=request.getContextPath() %>/AccueilSession">
				<button id="retour" type="button" class="btn btn-outline-primary btn-lg">Retour</button>
				</a>  
			</div>
 	   </div>
	   <footer style="margin-top: -60px">
		  <div class="barblanc"><img id="logo-eni" src="./images/logo-eni.png" alt="photo ici"></div>
		  <div class="barbleu"><p class="nous">Réalisé par Sebastien et Lorris</p></div>
	   </footer>
   </div>
	   
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>