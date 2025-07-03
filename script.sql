INSERT INTO tag (tagName) VALUES ('fantasy');
INSERT INTO tag (tagName) VALUES ('science-fiction');
INSERT INTO tag (tagName) VALUES ('romance');
INSERT INTO tag (tagName) VALUES ('thriller');
INSERT INTO tag (tagName) VALUES ('biographie');
INSERT INTO tag (tagName) VALUES ('jeunesse');
INSERT INTO tag (tagName) VALUES ('historique');
INSERT INTO tag (tagName) VALUES ('développement personnel');
INSERT INTO tag (tagName) VALUES ('drame');
INSERT INTO tag (tagName) VALUES ('aventure');


INSERT INTO typeAccount (accountType) VALUES ('Utilisateur normal');
INSERT INTO typeAccount (accountType) VALUES ('Bibliothecaire');
INSERT INTO typeAccount (accountType) VALUES ('Admin');

INSERT INTO profil (profilType, quotaPret) VALUES 
('Professionnel',10),
('Professeur',8),
('Etudiant',5),
('Anonyme',2);

INSERT INTO statutAdherent(statut) VALUES
('Actif'),
-- ('Penalite'),
('Innactif');

INSERT INTO categorieAge (ageMin, ageMax) VALUES
(0, 10),    -- 1: Jeunesse
(11, 17),   -- 2: Adolescent
(18, NULL);

SET client_encoding TO 'UTF8';
INSERT INTO livre (isbn, editionDate, auteur, idCategorieAge) VALUES
('978-2070360424', '1972-01-01', 'Georges Perec', 3),
('978-2253010693', '1947-06-08', 'Albert Camus', 3),
('978-2070360028', '1954-01-01', 'J.R.R. Tolkien', 2),
('978-2211230828', '2016-10-12', 'J.K. Rowling', 1),
('978-2080700146', '1862-01-01', 'Victor Hugo', 3),
('978-2070409314', '1943-01-01', 'Antoine de Saint-Exupery', 1),
('978-2253170984', '1813-01-28', 'Jane Austen', 3),
('978-2070360530', '1925-01-01', 'Franz Kafka', 3),
('978-2253010211', '1897-01-01', 'Bram Stoker', 3),
('978-2070368222', '1969-01-01', 'Maurice Sendak', 1);


UPDATE livre SET titre = 'La Disparition' WHERE idlivre = 1;
UPDATE livre SET titre = 'L''Étranger' WHERE idlivre = 2;
UPDATE livre SET titre = 'Le Seigneur des Anneaux' WHERE idlivre = 3;
UPDATE livre SET titre = 'Harry Potter à l''école des sorciers' WHERE idlivre = 4;
UPDATE livre SET titre = 'Les Misérables' WHERE idlivre = 5;
UPDATE livre SET titre = 'Le Petit Prince' WHERE idlivre = 6;
UPDATE livre SET titre = 'Orgueil et Préjugés' WHERE idlivre = 7;
UPDATE livre SET titre = 'Le Procès' WHERE idlivre = 8;
UPDATE livre SET titre = 'Dracula' WHERE idlivre = 9;
UPDATE livre SET titre = 'Max et les Maximonstres' WHERE idlivre = 10;

INSERT INTO tagLivre (idLivre, idTag) VALUES
(1, 9),  
(2, 9),  
(2, 5),  
(3, 1),   
(3, 10), 
(4, 1),  
(4, 6),   
(4, 10), 
(5, 7),   
(5, 9),   
(6, 6),   
(6, 10),  
(7, 3),   
(7, 7),  
(8, 9),   
(9, 4),  
(9, 7),   
(10, 6),  
(10, 10); 

INSERT INTO exemplaire (idLivre, nbExemplaire) VALUES
(1, 5),   
(2, 8),   
(3, 12),  
(4, 15), 
(5, 7),   
(6, 20),  
(7, 6),   
(8, 4),   
(9, 3),   
(10, 10); 

