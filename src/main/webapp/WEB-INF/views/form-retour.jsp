<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entities.AdherentEntity, entities.PretEntity" %>
<%@ page import="java.util.List" %>

<%
    List<AdherentEntity> adherents = (List<AdherentEntity>) request.getAttribute("listAdherent");
    List<PretEntity> pretsEnCours = (List<PretEntity>) request.getAttribute("pretsEnCours");
    Integer idAdherentSelectionne = (Integer) request.getAttribute("idAdherentSelectionne");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Retour de prêt</title>
</head>
<body>

<h2>Retour de prêt</h2>

<% if (request.getAttribute("messageSuccess") != null) { %>
    <p style="color: green"><%= request.getAttribute("messageSuccess") %></p>
<% } else if (request.getAttribute("messageError") != null) { %>
    <p style="color: red"><%= request.getAttribute("messageError") %></p>
<% } %>

<!-- Formulaire pour choisir l'adhérent -->
<form action="retour" method="post">
    <label for="idAdherent">Adhérent :</label>
    <select name="idAdherent" id="idAdherent" required>
        <option value="">-- Sélectionner --</option>
        <% for (AdherentEntity a : adherents) { %>
            <option value="<%= a.getIdAdherent() %>"
                <%= (idAdherentSelectionne != null && idAdherentSelectionne.equals(a.getIdAdherent())) ? "selected" : "" %>>
                <%= a.getAdherentName() %>
            </option>
        <% } %>
    </select>
    <input type="submit" value="Afficher les prêts en cours">
</form>

<% if (pretsEnCours != null && !pretsEnCours.isEmpty()) { %>
    <h3>Prêts en cours</h3>
    <form action="confirmerRetour" method="post">
        <label for="idPret">Sélectionnez le prêt à retourner :</label>
        <select name="idPret" id="idPret" required>
            <option value="">-- Sélectionner --</option>
            <% for (PretEntity p : pretsEnCours) { %>
                <option value="<%= p.getIdPret() %>">
                    Livre : <%= p.getExemplaire().getLivre().getTitre() %> | Emprunté le : <%= p.getDatePret() %>
                </option>
            <% } %>
        </select>
        <br/><br/>
        <label for="dateRetour">Date de retour :</label>
        <input type="date" name="dateRetour" required>
        <br/><br/>
        <input type="submit" value="Confirmer le retour">
    </form>
<% } %>

</body>
</html>
