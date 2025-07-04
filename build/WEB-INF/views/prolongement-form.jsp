<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="entities.AdherentEntity, entities.PretEntity" %>
<%@ page import="java.time.LocalDate" %>

<%
    List<AdherentEntity> adherents  = (List<AdherentEntity>) request.getAttribute("listAdherent");
    List<PretEntity> prets          = (List<PretEntity>) request.getAttribute("pretsEnCours");
    Map<Integer, LocalDate> datesFin = (Map<Integer, LocalDate>) request.getAttribute("datesFinPret");
    Integer idSel                  = (Integer) request.getAttribute("idAdherentSelectionne");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Prolongement de prêt</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<nav class="navbar"> <!-- réutilise ta navbar --> … </nav>

<div class="main-container">
  <div class="card">
    <h2>📅 Prolongement de prêt</h2>

    <% if (request.getAttribute("messageSuccess") != null) { %>
        <div class="message success"><%= request.getAttribute("messageSuccess") %></div>
    <% } else if (request.getAttribute("messageError") != null) { %>
        <div class="message error"><%= request.getAttribute("messageError") %></div>
    <% } %>

    <!-- Choix adhérent -->
    <form action="prolongement" method="post" class="form-inline">
        <div class="form-group">
            <label>Adhérent :</label>
            <select name="idAdherent" class="form-control" required>
                <option value="">-- Sélectionner --</option>
                <% for (AdherentEntity a : adherents) { %>
                    <option value="<%= a.getIdAdherent() %>"
                       <%= (idSel!=null && idSel.equals(a.getIdAdherent())) ? "selected" : "" %>>
                        <%= a.getAdherentName() %>
                    </option>
                <% } %>
            </select>
        </div>
        <button class="btn btn-primary" type="submit">Afficher prêts</button>
    </form>
  </div>

  <% if (prets != null && !prets.isEmpty()) { %>
  <div class="card">
    <h3>Prêts en cours</h3>
    <form action="confirmerProlongement" method="post">
        <div class="form-group">
            <label>Prêt :</label>
            <select name="idPret" class="form-control" required>
                <% for (PretEntity p : prets) {
                       LocalDate fin = datesFin.get(p.getIdPret());
                %>
                    <option value="<%= p.getIdPret() %>">
                        📖 <%= p.getExemplaire().getLivre().getTitre() %> |
                        fin prévue : <%= fin %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label>Durée supplémentaire (jours) :</label>
            <input type="number" name="duree" class="form-control" min="1" max="30" required>
        </div>

        <button class="btn btn-success" type="submit">Prolonger</button>
    </form>
  </div>
  <% } %>
</div>
</body>
</html>
