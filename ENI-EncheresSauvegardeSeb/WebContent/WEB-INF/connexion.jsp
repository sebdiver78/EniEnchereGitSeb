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

    						 <title>ENI-Encheres connexion</title>    
  </head>
  
  <body> 	
    <div class="container">
        <div class="row">
             <div class="col-sm-12">
             								      <!-- /rien pour retour à l'accueil -->
                <a href="<%=request.getContextPath() %> "><img id="logo-encheres" src="./images/logo-eni-encheres.png" alt="photo ici"></a>
           	 </div> 
       	</div>
    </div>
    <div class="container">
    	<form id="contact-form" role="form" method="post" action="Connexion"> 
	    	<div class="row" style="justify-content:center;">
	       		<div class="col-md-6" >
	       		<label for="identifiant">Identifiant :</label>
				<input type="text" id="email" name="email" class="form-control"/>
	       		</div>
	       	</div>
	       	<div class="row" style="justify-content:center;">
	   			<div class="col-md-6">
		   			<label for="motdepasse">Mot de passe :</label>
					<input type="password" id="motDePasse" name="motDePasse"class="form-control"/>
	     		</div>
	        </div>
	        <div class="row" style="justify-content:center;">
		        <div class="connexion">
					<div align="center">
						<a href="<%=request.getContextPath() %>/AccueilSession"><input type="submit" value="Connexion" class="btn btn-outline-primary btn-lg" style="margin-top: 20px; margin-right: 35%"/></a>
					</div>
					<div align="center" style="margin-top: -45px;">
						<input style=" margin-left: 30px" type="checkbox" id="souvenir" name="souvenir" value="oui"/>
						<p style="font-size: 15px; margin-left: 180px; margin-top: -24px; margin-bottom: -1px">Se souvenir de moi</p>
						<a href="" style="font-size: 15px; margin-left: 150px;">Mot de passe oublié</a>
					</div>
		        </div>
       		 	<div align="center">
	        		<a href="<%=request.getContextPath() %>/Ajout"><input type="button" class="btn btn-outline-primary btn-lg" style="margin-top: 100px;" value="Créer un compte"/></a>
	        	</div>
			</div>
		</form>
		
		<footer>
	   		<div class="barblanc"><img id="logo-eni" src="./images/logo-eni.png" alt="photo ici"></div>
	      	<div class="barbleu"><p class="nous">Réalisé par Sebastien et Lorris</p></div>
    	</footer>
	</div>
   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
 
 </body>
</html>