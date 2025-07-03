<%@ page import="entities.LivreEntity" %>
<%@ page import="entities.TypePretEntity" %>
<%@ page import="entities.AdherentEntity" %>
<%@ page import="java.util.List" %>

<%
    List<LivreEntity> livres = (List<LivreEntity>) request.getAttribute("listLivre");
    List<TypePretEntity> typePrets = (List<TypePretEntity>) request.getAttribute("listTypePret");
    List<AdherentEntity> adherents = (List<AdherentEntity>) request.getAttribute("listAdherent");
%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Créer un prêt</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
        }

        label {
            display: inline-block;
            width: 170px;
            margin-bottom: 8px;
        }

        select,
        input[type="date"] {
            width: 260px;
            padding: 4px;
        }

        .msg {
            margin: 15px 0;
            padding: 8px 12px;
            border-radius: 4px;
        }

        .success {
            background: #d4edda;
            color: #155724;
        }

        .error {
            background: #f8d7da;
            color: #721c24;
        }
    </style>
</head>

<body>

    <h2>Création d’un prêt</h2>

    <form action="createPret" method="post">

        <label for="idAdherent">Adhérent :</label>
        <select name="idAdherent" id="idAdherent" required>
            <% for (AdherentEntity adherent : adherents) { %>
                <option value="<%= adherent.getIdAdherent() %>">
                    <%= adherent.getAdherentName() %>
                </option>
            <% } %>
        </select>
        <br />

        <label for="idLivre">Livre à prêter :</label>
        <select name="idLivre" id="idLivre" required>
            <% for (LivreEntity livre : livres) { %>
                <option value="<%= livre.getIdLivre() %>">
                    <%= livre.getTitre() %>
                </option>
            <% } %>
        </select>
        <br />

        <label for="idTypePret">Type de prêt :</label>
        <select name="idTypePret" id="idTypePret" required>
            <% for (TypePretEntity typePret : typePrets) { %>
                <option value="<%= typePret.getIdTypePret() %>">
                    <%= typePret.getTypePret() %>
                </option>
            <% } %>
        </select>
        <br />

        <label for="datePret">Date du prêt :</label>
        <input type="date" id="datePret" name="datePret" required>
        <br /><br />

        <input type="submit" value="Valider le prêt">
    </form>

</body>
</html>
