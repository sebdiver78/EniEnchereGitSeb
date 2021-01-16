-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--
USE BDD_ENCHERES
GO

DROP TABLE IF EXISTS ENCHERES;
DROP TABLE IF EXISTS PHOTOS;
DROP TABLE IF EXISTS ARTICLES;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS RETRAITS;
DROP TABLE IF EXISTS CATEGORIES;

CREATE TABLE CATEGORIES (
    idCategorie   INTEGER NOT NULL CONSTRAINT Pk_categorie PRIMARY KEY ,
    libelle        VARCHAR(30) NOT NULL
)

CREATE TABLE RETRAITS (
	idRetrait       INTEGER NOT NULL CONSTRAINT Pk_retraits PRIMARY KEY IDENTITY (1,1),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

CREATE TABLE USERS (
    idUser   INTEGER NOT NULL CONSTRAINT Pk_utilisateurs PRIMARY KEY IDENTITY (1,1),
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
	dateInscription  DATETIME NOT NULL DEFAULT GETDATE(),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER DEFAULT 0,
    administrateur   bit DEFAULT 0,
	actif 			bit DEFAULT 1 /* permet de bloquer ou supprimer un utilisateurs */
)

CREATE TABLE ARTICLES (
    idArticle                    INTEGER NOT NULL CONSTRAINT Pk_articles PRIMARY KEY IDENTITY (1,1),
    nom                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut           	DATETIME NOT NULL,
    date_fin            	DATETIME NOT NULL,
    prix_initial                  INTEGER NOT NULL,
    prix_vente                    INTEGER NULL,
    idUserArticle              		INTEGER  NOT NULL CONSTRAINT Fk_articles_user FOREIGN KEY REFERENCES USERS(idUser),
    idCategorie                  INTEGER  NOT NULL CONSTRAINT Fk_articles_categorie FOREIGN KEY REFERENCES CATEGORIES(idCategorie),
	idRetrait					  INTEGER  NOT NULL CONSTRAINT Fk_articles_retrait FOREIGN KEY REFERENCES RETRAITS(idRetrait),
	actif BIT DEFAULT 1	 /* pour activer ou désactiver un article comprométant */
)

CREATE TABLE PHOTOS(

	idPhoto INTEGER NOT NULL CONSTRAINT Pk_photos PRIMARY KEY IDENTITY(1,1),
	idArticlePhoto INTEGER NOT NULL CONSTRAINT Fk_photos_article FOREIGN KEY REFERENCES ARTICLES(idArticle),
	url VARCHAR(100) NOT NULL
)

CREATE TABLE ENCHERES(	
	idArticleEnchere INTEGER NOT NULL CONSTRAINT Fk_ARTICLES_idArticle REFERENCES ARTICLES(idArticle),
	idUser INTEGER NOT NULL ,
	date DATETIME NOT NULL DEFAULT GETDATE(), 
	montant INTEGER NOT NULL,
	PRIMARY KEY (idArticle, idUser)
	)
	
 





 INSERT INTO CATEGORIES (idCategorie, libelle) VALUES (1, 'Informatique');
 INSERT INTO CATEGORIES (idCategorie, libelle) VALUES (2, 'Ameublement');
 INSERT INTO CATEGORIES (idCategorie, libelle) VALUES (3, 'Vêtement');
 INSERT INTO CATEGORIES (idCategorie, libelle) VALUES (4, 'Sport&Loisirs');
 
INSERT INTO USERS (pseudo, nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)
VALUES ('sebmas78','MASSERA','sebastien','sebastien.massera@hotmail.fr',0612345678,'10 rue de la paix','75000','MONOPOLY','123456',300,0);

