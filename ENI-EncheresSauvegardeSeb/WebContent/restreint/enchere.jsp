<%@page import="fr.eni.enchere.bo.Article"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>D�tail vente</title>
</head>
<body>

<!--  Pour transformer la valeur FLOAT en INT -->
<%
	Article articleEnCours = new Article();
	articleEnCours = (Article) request.getAttribute("articleEnchere");
	
	Article articleAffichage = new Article();
	articleAffichage = (Article) request.getAttribute("articleAffichage");

%>

<!-- Recuperation de la date du jour au format yyyy-MM-dd correspondant au format localDate, 
	je n'ai pas r�ussi � g�rer les dates avec l'heure en plus?
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
	
	   
	 
	
	 <!-- Affiche un H1 si un utilisateur a remport� l'ench�re, sinon n'affiche rien -->
	 <c:if test="${not empty articleAffichage.idArticle }">
	 		<span><h1>${pseudoUser } a remport� l'ench�re </h1></span>
	 </c:if>
	 
	 <!--  JSTL qui compare l'idSession avec l'IDUSER de l'enchere et si la date de fin d'ench�re est plus petite qu'aujourd'hui
	Si c'est le cas, �a veut dire que l'ench�re a �t� remport�e et on affiche ENCHERE REMPORTEE, sinon, rien ne s'affiche
	 -->
	<c:if test="${(articleEnchere.enchere.noUtilisateur == sessionUtilisateur.noUtilisateur) && (articleEnchere.dateFinEnchere lt now)}">
			<span><h1>Vous avez remport� l'ench�re</h1></span>
	
	</c:if>


<h1>D�tail vente</h1>
<form method ="post" action="ActionArticle">
	<h1>${articleEnchere.nomArticle } ${articleAffichage.nomArticle}</h1>
	<h2>Description : ${articleEnchere.descriptionArticle } ${articleAffichage.descriptionArticle}</h2>
	<p>Cat�gorie : ${articleEnchere.categorie.libelle}</p>
	
	<label for="enchereActuelle">Meilleure offre</label>
	<!-- modifie affichage de la valeur de l'enchere suivant la provenance de la requete -->
		<c:choose>
			<c:when test="${empty articleAffichage.idArticle }">
				<input type="number" id="enchereActuelle" name="enchereActuelle"  readonly value="<%=Math.round(articleEnCours.getPrixVente())%>"/></p>
			</c:when>
			
			<c:when test="${empty articleEnCours.idArticle }">
			<p> prix vente termin�e</p>
				<input type="number" id="enchereActuelle" name="enchereActuelle"  readonly value="<%=Math.round(articleAffichage.getPrixVente())%>"/></p>
		
			</c:when>
			
		</c:choose>
		
	<p>Fin de l'ench�re : ${articleEnchere.dateFinEnchere } ${articleAffichage.dateFinEnchere}</p>
	<p>Retrait : ${articleEnchere.retrait.rue } ${articleEnchere.retrait.codePostal } ${articleEnchere.retrait.ville } ${articleAffichage.retrait.rue} ${articleAffichage.retrait.codePostal} ${articleAffichage.retrait.ville}</p>
	<p>Vendeur : ${articleEnchere.utilisateur.pseudo } ${articleAffichage.utilisateur.pseudo}</p>
	<input type="hidden" id="idArticle" name="idArticle" value="<c:out value="${articleEnchere.idArticle}"/>"/>
	<input type="hidden" id="idUSerSession" name="idUserSession" value="<c:out value="${sessionScope.sessionUtilisateur.noUtilisateur}"/>"/>
	<!-- JSTL qui affiche le TEL du vendeur si enchere remport�e et rien en temps normal -->
	<c:if test="${(articleEnchere.enchere.noUtilisateur == sessionUtilisateur.noUtilisateur) && (articleEnchere.dateFinEnchere lt now)}">
			<p>Tel  : ${articleEnchere.utilisateur.telephone }</p>
	
	</c:if>

<!-- JSTL  qui modifie l'affichage et la fonction des boutons  du bas de page si enchere gagn�e ou pas
si on est l'encherisseur et que enchere finie, affiche un bouton RETOUR sinon affiche encherir, valider et annuler
-->
<!-- si on consulte une enchere en cours, on ne peut pas r�ench�rir sur sa propore enchere donc acc�s bouton retour seulement -->
	<c:choose>
		<%--affiche des boutons et actions differentes suivant les propri�t�s de l'article  --%>
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
			<input type="submit" value="Ench�rir"/>	
	
	<a href="AccueilSession"><input type="button" value="annuler"/></a>
		</c:when>
		
		<c:when test ="${not empty articleAffichage.idArticle}">
		<a href="AccueilSession"><input type="button" value ="Retrait effectu�"/></a>		
		</c:when>
		
		
		
	</c:choose>	
	
	</form>
		
	
</body>
</html>