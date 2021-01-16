<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Session</title>

<!-- Bootstrap CSS -->
     <link rel="stylesheet" href="css1/styleAcceuil.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link type ="text/css" rel="stylesheet" href="/css1/styleAcceuil.css" />
</head>
<body>
		  <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <h1>ENI-Enchères</h1>     
            </div>
            <div class="col-sm-4"> 
            	<a href="Encheres">Enchères</a>
            	<a href="${pageContext.request.contextPath}/Profil?id=${sessionScope.sessionUtilisateur.noUtilisateur}">Mon profil</a>
               	<a href="VendreUnArticle">Vendre un article</a>
               	<a href="Deconnexion">Se déconnecter</a>
            </div>
            <p>Bienvenue ${sessionScope.sessionUtilisateur.prenom} ${sessionScope.sessionUtilisateur.nom}</p>
            <p>Crédit disponible : ${sessionScope.sessionUtilisateur.credit }</p>
        </div>
    </div>
         

	
	



 <div class="col-sm-12">
        <h2>Liste des enchères</h2>    
           
    </div>
            
      <div class="container">
          <div class="row">
          <form action="triSession" method ="post">
            <h4 id="filtre">Filtres :</h4>  
            <div class="col-6">
                <div id="input" class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon1">?</span>
                  <input type="text" id="filtre" name = "filtre" class="form-control" placeholder="Le nom de l'article contient" aria-label="Username" aria-describedby="basic-addon1">
                </div>
              <br>
                <div class="container">
                    <div class="row">

                        <div class="col-sm-6">
                          <h4>Catégorie:</h4>
                        </div>

                        <div class="col-sm-6">
                          <select name="idCat" id="idCat" value="1" class="form-select" aria-label="Default select example">
                              <option value="0">Toutes</option>
                              <c:forEach var="cat" items="${listeCategories}">
		
		
				
							<c:set var="idCat" value="${cat.idCategorie }"/>
							<option value ="<c:out value="${cat.idCategorie}" />">${cat.libelle}</a> </option>
							</c:forEach>
                            </select>
                        </div>
                        <!--  par défaut, case encheres ouvertes selectionnée -->
                        <!-- j'ai mis des boutons radios et pas checkbox car pas le temps de gérer les différentes requêtes -->
                        <!--  pouvant être choises avec les choix mutilples des checkbox -->
                        
							<fieldset>
								<legend>
								<label for="achats">Achats</label>
								<input type="radio" id="achats" name="liste" value="encheres" checked />
								</legend>
							<label for="enchereOuverte">Encheres ouvertes</label>                        
                        	<input type="radio" id="enchereOuverte"  name="achatEnchere" value="ouverte" checked/>
                        	
                        	<label for="enchereEnCours">Encheres en cours</label>                        
                        	<input type="radio" id="enchereEnCours" name="achatEnchere" value="enchereEnCours"/>
                        	
                        	<label for="enchereRemporte">Encheres remportées</label>                        
                        	<input type="radio" id="enchereOuverte" name="achatEnchere" value="remportees"/>
                        								
							</fieldset>                 
							
							<fieldset>
								<legend>
								<label for="ventes">Ventes</label>
								<input type="radio" id="ventes" name="liste" value="ventes"/>
								
								</legend>
							<label for="venteEnCours">Ventes en cours</label>                        
                        	<input type="radio" id="ventesEnCours" name="achatEnchere" value="ventesEnCours"/>
                        	
                        	<label for="ventesNonDebutees">Ventes non débutées</label>                        
                        	<input type="radio" id="ventesNonDebutees" name="achatEnchere" value="nonDebutees"/>
                        	
                        	<label for="ventesTerminées">Ventes terminées</label>                        
                        	<input type="radio" id="ventesTerminees" name="achatEnchere" value="terminees"/>
                        
							</fieldset>       
							
                        	
                    </div>
                </div>
            
            </div>
              
            <div class="col-sm-6">
             <!--<button id="rechercher" type="button" class="btn btn-outline-primary btn-lg">Rechercher</button>  --> 
            <button id="rechercher"  type="button" class="btn btn-outline-primary btn-lg"><input type="submit" value="Rechercher" name="rechercher" id="rechercher" /></button> 
            </div>
            </form>
          </div>
        
		         
          
 
          
          <div>
 			

	<c:set var="i" value="${1}"/>
		<c:forEach var="art" items="${listeArticles}"  >
		
		
		<li>
		
		 <!-- JSTL qui permet dans le même affichage d'afficher plusieurs versions différentes des boutons et fonctionnalités -->   
		<%--quand bouton achats selectionné renvoie sur feuille enchere.jsp  --%>
			<c:if test="${valeurBouton eq 'encheres' }">
				<a href="ActionArticle?id=${art.idArticle}"><h2>${art.nomArticle}</h2> </a>
			</c:if>
		<%--quand bouton ventes selectionne renvoie sur feuille nouvelleVenteJsp --%>
			<c:if test="${valeurBouton eq 'ventes' }">
				<a href="Consultation?id=${art.idArticle}"><h2>${art.nomArticle}</h2> </a>
			</c:if>
			
		<%--<a href="ActionArticle?id=${art.idArticle}"><h2>${art.nomArticle}</h2> </a> --%>
		<p>Prix : ${art.prixVente} points</p>
		<p>Fin de l'enchère : ${art.dateFinEnchere}</p>
		<p>idUser Article : ${art.idUser }</p> 
		
		<c:set var="idUser" value="${art.idUser}"/>
		<!-- boucle qui permet de récupere le pseudo du vendeur de l'objet -->
			<c:forEach var="id" items="${listeIdPseudo}">
					<c:if test="${art.idUser == id.noUtilisateur}">
						<c:set var="pseudo" value="${id.pseudo }"/>
					</c:if>
			</c:forEach>
		
		<p> Vendeur : <a href="ProfilUser?id=${art.idUser}">${pseudo}</a></p>
		<br/>
		<c:set var="i" value="${i +1 }"/>
		</c:forEach>
          	</li>	
          	</ul>
          </div>
        </div>

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    -->
  </body>
</html>