<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>

	<header>
		<div>ENI-Enchères</div>
			
	</header>	

	<main>
		<form method="post" action="Connexion">
		<div class="formulaire">
			<label for="identifiant">Identifiant :</label>
			<input type="text" id="email" name="email"/>
			<br/>
			
			<label for="motdepasse">Mot de passe :</label>
			<input type="password" id="motDePasse" name="motDePasse"/>
			<br/>
		</div>	
		
			
			<div class="connexion">
			<input type="checkbox" id="souvenir" name="souvenir" value="oui"/>
				<input type="submit" value="Connexion"/>
				
				<a href="" >Mot de passe oublié</a>
			</div>
			
		<div class="creerCompte">
			<a href="<%=request.getContextPath() %>/Ajout"><input type="button" value="Créer un compte"/></a>
	
		
		</div>
			</form>				
		
	</main>



</body>
</html>