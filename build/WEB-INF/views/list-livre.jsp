<%@ page import="entities.LivreEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.CategorieAgeEntity" %>

<% 
List<LivreEntity> livres = (List<LivreEntity>) request.getAttribute("listLivre"); 
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des livres</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>Liste des livres</h2>
    <table>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>ID Catégorie</th>
            <th>Tranche d'âge</th>
        </tr>
        <% for (LivreEntity livre : livres) { 
            CategorieAgeEntity categorie = livre.getCategorieAge();
            String trancheAge = "";
            if (categorie != null) {
                trancheAge = categorie.getAgeMin() + "-" + categorie.getAgeMax() + " ans";
                if (categorie.getAgeMax() == null) {
                    trancheAge = categorie.getAgeMin() + "+ ans";
                }
            }
        %>
            <tr>
                <td><%= livre.getTitre() != null ? livre.getTitre() : "" %></td>
                <td><%= livre.getAuteur() != null ? livre.getAuteur() : "" %></td>
                <td><%= categorie != null ? categorie.getIdCategorieAge() : "N/A" %></td>
                <td><%= trancheAge %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>