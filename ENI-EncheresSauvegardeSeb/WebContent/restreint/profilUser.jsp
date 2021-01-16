<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil utilisateur</title>
</head>
<body>

<h1>ENI-Enchères</h1>

<p>Pseudo : ${utilisateur.pseudo }</p>
<br/>
<p>Nom : ${utilisateur.nom }</p>
<br/>
<p>Prénom : ${utilisateur.prenom }</p>
</br>
<p>Email : ${utilisateur.telephone }</p>
</br>
<p>Rue : ${utilisateur.rue }</p>
</br>
<p>Code Postal ${utilisateur.codePostal }</p>
</br>
<p>Ville ${utilisateur.ville }</p>

<a href="AccueilSession">Retour</a>


</body>
</html>