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


-- Insertion des livres
INSERT INTO livre (isbn, editionDate, auteur, idCategorieAge) VALUES
('978-2070360424', '1972-01-01', 'Georges Perec', 3),          -- La Disparition
('978-2253010693', '1947-06-08', 'Albert Camus', 3),           -- L'Étranger
('978-2070360028', '1954-01-01', 'J.R.R. Tolkien', 2),        -- Le Seigneur des Anneaux
('978-2211230828', '2016-10-12', 'J.K. Rowling', 1),          -- Harry Potter
('978-2080700146', '1862-01-01', 'Victor Hugo', 3),           -- Les Misérables
('978-2070409314', '1943-01-01', 'Antoine de Saint-Exupéry', 1), -- Le Petit Prince
('978-2253170984', '1813-01-28', 'Jane Austen', 3),           -- Orgueil et Préjugés
('978-2070360530', '1925-01-01', 'Franz Kafka', 3),           -- Le Procès
('978-2253010211', '1897-01-01', 'Bram Stoker', 3),           -- Dracula
('978-2070368222', '1969-01-01', 'Maurice Sendak', 1);        -- Max et les Maximonstres

-- Insertion des tags pour les livres (en utilisant vos tags exacts)
INSERT INTO tagLivre (idLivre, idTag) VALUES
(1, 9),   -- Georges Perec - drame
(2, 9),   -- Albert Camus - drame
(2, 5),   -- Albert Camus - biographie (aspect autobiographique)
(3, 1),   -- J.R.R. Tolkien - fantasy
(3, 10),  -- J.R.R. Tolkien - aventure
(4, 1),   -- J.K. Rowling - fantasy
(4, 6),   -- J.K. Rowling - jeunesse
(4, 10),  -- J.K. Rowling - aventure
(5, 7),   -- Victor Hugo - historique
(5, 9),   -- Victor Hugo - drame
(6, 6),   -- Antoine de Saint-Exupéry - jeunesse
(6, 10),  -- Antoine de Saint-Exupéry - aventure
(7, 3),   -- Jane Austen - romance
(7, 7),   -- Jane Austen - historique
(8, 9),   -- Franz Kafka - drame
(9, 4),   -- Bram Stoker - thriller
(9, 7),   -- Bram Stoker - historique
(10, 6),  -- Maurice Sendak - jeunesse
(10, 10); -- Maurice Sendak - aventure

-- Insertion des exemplaires pour chaque livre
INSERT INTO exemplaire (idLivre, nbExemplaire) VALUES
(1, 5),   -- 5 exemplaires de "La Disparition"
(2, 8),   -- 8 exemplaires de "L'Étranger"
(3, 12),  -- 12 exemplaires de "Le Seigneur des Anneaux"
(4, 15),  -- 15 exemplaires de "Harry Potter"
(5, 7),   -- 7 exemplaires de "Les Misérables"
(6, 20),  -- 20 exemplaires de "Le Petit Prince"
(7, 6),   -- 6 exemplaires de "Orgueil et Préjugés"
(8, 4),   -- 4 exemplaires de "Le Procès"
(9, 3),   -- 3 exemplaires de "Dracula"
(10, 10); -- 10 exemplaires de "Max et les Maximonstres"

