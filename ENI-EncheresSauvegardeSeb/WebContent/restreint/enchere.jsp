<%@page import="fr.eni.enchere.bo.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Pour récupérer le fichier css  -->
	<style><%@include file="../styleCss/style.css"%></style> 
	<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/styleCss/style.css" /> --%> 
<title>Détail vente</title>
</head>
<body>
			<div class="col-sm-8">
             								        <!-- /rien pour retour à l'accueil -->
                <a href="<%=request.getContextPath() %> " ><img id="logo-encheres" src="./images/logo-eni-encheres.png" alt="photo ici"></a>
                
            </div> 
<!--  Pour transformer la valeur FLOAT en INT -->
<%
	Article articleEnCours = new Article();
	articleEnCours = (Article) request.getAttribute("articleEnchere");
	
	Article articleAffichage = new Article();
	articleAffichage = (Article) request.getAttribute("articleAffichage");

%>

<!-- Recuperation de la date du jour au format yyyy-MM-dd correspondant au format localDate, 
	je n'ai pas réussi à gérer les dates avec l'heure en plus?
 -->
	<jsp:useBean id="now" class="java.util.Date"/>
	<fmt:formatDate pattern ="yyyy-MM-dd" value = "${now }" var="now"/>
	
	<!-- JSTL qui change la valeur de PSEUDO suivnat la requete -->
	   
	   <c:choose>
	   
	   		<c:when test="${articleAffichage.enchere.noUtilisateur == 0 }">
	   			<c:set var="pseudoUser" value="${'Personne n'}"/>
	   		</c:when>
	   	 
	   		<c:when test="${articleAffichage.enchere.noUtilisateur != 0 }">
	   				
	 				
	 						<c:set var="pseudoUser" value="${articleAffichage.enchere.noUtilisateur}"/>
	 					
	 	
	 				
	   	
	   		</c:when>
	   	
	   </c:choose>
	
	   
	 
	
	 <!-- Affiche un H1 si un utilisateur a remporté l'enchère, sinon n'affiche rien -->
	 <c:if test="${not empty articleAffichage.idArticle }">
	 		<span><h1>${pseudoUser } a remporté l'enchère </h1></span>
	 </c:if>
	 
	 <!--  JSTL qui compare l'idSession avec l'IDUSER de l'enchere et si la date de fin d'enchère est plus petite qu'aujourd'hui
	Si c'est le cas, ça veut dire que l'enchère a été remportée et on affiche ENCHERE REMPORTEE, sinon, rien ne s'affiche
	 -->
	<c:if test="${(articleEnchere.enchere.noUtilisateur == sessionUtilisateur.noUtilisateur) && (articleEnchere.dateFinEnchere lt now)}">
			<span><h1>Vous avez remporté l'enchère</h1></span>
	
	</c:if>


<h1>Détail vente</h1>
<form method ="post" action="ActionArticle">
	<h1>${articleEnchere.nomArticle } ${articleAffichage.nomArticle}</h1>
	<h2>Description : ${articleEnchere.descriptionArticle } ${articleAffichage.descriptionArticle}</h2>
	<p>Catégorie : ${articleEnchere.categorie.libelle}</p>
	
	<label for="enchereActuelle">Meilleure offre</label>
	<!-- modifie affichage de la valeur de l'enchere suivant la provenance de la requete -->
		<c:choose>
			<c:when test="${empty articleAffichage.idArticle }">
				<input type="number" id="enchereActuelle" name="enchereActuelle"  readonly value="<%=Math.round(articleEnCours.getPrixVente())%>"/></p>
			</c:when>
			
			<c:when test="${empty articleEnCours.idArticle }">
			<p> prix vente terminée</p>
				<input type="number" id="enchereActuelle" name="enchereActuelle"  readonly value="<%=Math.round(articleAffichage.getPrixVente())%>"/></p>
		
			</c:when>
			
		</c:choose>
		
	<p>Fin de l'enchère : ${articleEnchere.dateFinEnchere } ${articleAffichage.dateFinEnchere}</p>
	<p>Retrait : ${articleEnchere.retrait.rue } ${articleEnchere.retrait.codePostal } ${articleEnchere.retrait.ville } ${articleAffichage.retrait.rue} ${articleAffichage.retrait.codePostal} ${articleAffichage.retrait.ville}</p>
	<p>Vendeur : ${articleEnchere.utilisateur.pseudo } ${articleAffichage.utilisateur.pseudo}</p>
	<input type="hidden" id="idArticle" name="idArticle" value="<c:out value="${articleEnchere.idArticle}"/>"/>
	<input type="hidden" id="idUSerSession" name="idUserSession" value="<c:out value="${sessionScope.sessionUtilisateur.noUtilisateur}"/>"/>
	<!-- JSTL qui affiche le TEL du vendeur si enchere remportée et rien en temps normal -->
	<c:if test="${(articleEnchere.enchere.noUtilisateur == sessionUtilisateur.noUtilisateur) && (articleEnchere.dateFinEnchere lt now)}">
			<p>Tel  : ${articleEnchere.utilisateur.telephone }</p>
	
	</c:if>

<!-- JSTL  qui modifie l'affichage et la fonction des boutons  du bas de page si enchere gagnée ou pas
si on est l'encherisseur et que enchere finie, affiche un bouton RETOUR sinon affiche encherir, valider et annuler
-->
<!-- si on consulte une enchere en cours, on ne peut pas réenchérir sur sa propore enchere donc accès bouton retour seulement -->
	<c:choose>
		<%--affiche des boutons et actions differentes suivant les propriétés de l'article  --%>
		<%-- REMPORTEE --%>
		<c:when test ="${(articleEnchere.enchere.noUtilisateur == sessionUtilisateur.noUtilisateur) &&  (articleEnchere.dateFinEnchere lt now)}">
				<a href="AccueilSession"><input type="button" value="Retour"/></a>
		</c:when>
		
		<%-- EN COURS --%>
		<c:when test="${(articleEnchere.enchere.noUtilisateur == sessionUtilisateur.noUtilisateur) && (articleEnchere.dateFinEnchere gt now)}">
		<p>EN COURS</p>
		<a href="AccueilSession"><input type="button" value="Retour"/></a>
		</c:when>

		<%-- OUVERTE --%>
		<c:when test="${ (articleEnchere.enchere.noUtilisateur != sessionUtilisateur.noUtilisateur) && (articleEnchere.dateFinEnchere gt now)}">
			<label for="proposition">Ma proposition</label>
			<input type="number" id="proposition" name="proposition"/>
			<input type="submit" value="Enchérir"/>	
	
	<a href="AccueilSession"><input type="button" value="annuler"/></a>
		</c:when>
		
		<c:when test ="${not empty articleAffichage.idArticle}">
		<a href="AccueilSession"><input type="button" value ="Retrait effectué"/></a>		
		</c:when>
		
		
		
	</c:choose>	
	
	</form>
	<footer>
	      	<div class="barblanc"><img id="logo-eni" src="./images/logo-eni.png" alt="photo ici"></div>
	      	<div class="barbleu"><p class="nous">Réalisé par Sebastien et Lorris</p></div>
	      </footer>
	      
	      
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	 	
	
</body>
</html>