<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!doctype html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
     <link rel="stylesheet" href="css1/styleAcceuil.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link type ="text/css" rel="stylesheet" href="/css1/styleAcceuil.css" />

    <title>ENI-Encheres</title>
  </head>
  <body>
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <h1>ENI-Enchères</h1>     
            </div>
            <div class="col-sm-4"> 
                <a href="<%=request.getContextPath() %>/Ajout">Créer un compte -</a>
				<a href="<%=request.getContextPath() %>/Connexion">Se connecter</a> 
            </div>
        </div>
    </div>
         
    <div class="col-sm-12">
        <h2>Liste des enchères</h2>          
    </div>
            
      <div class="container">
          <div class="row">
          <form action="tri" method ="post">
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
                        
                    </div>
                </div>
            
            </div>
              
            <div class="col-sm-6">
             <!--<button id="rechercher" type="button" class="btn btn-outline-primary btn-lg">Rechercher</button>  --> 
            <button id="rechercher" type="button" class="btn btn-outline-primary btn-lg"><input type="submit" name="rechercher" id="rechercher" /></button> 
            </div>
            
          </div>
          </form>
          <div>
          	<ul>
          	





			<p> Il y a ${listeArticles.size()} objets en vente</p>

	<c:set var="i" value="${1}"/>
		<c:forEach var="art" items="${listeArticles}"  >
		<li>
		<h2> Article ${i} ${art.nomArticle}</h2> 
		<p>${art.descriptionArticle}</p>
		<p>Categorie :${art.categorie.libelle}</p>
		<P>${art.utilisateur.pseudo}</P>
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

