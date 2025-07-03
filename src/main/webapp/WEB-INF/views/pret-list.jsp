<!-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des prêts</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 6px 10px; }
        th { background: #f2f2f2; }
    </style>
</head>
<body>

<h2>Tous les prêts</h2>

<table>
    <tr>
        <th>#</th>
        <th>Date</th>
        <th>Livre</th>
        <th>Exemplaire</th>
        <th>Adhérent</th>
        <th>Statut courant</th>
    </tr>
    <c:forEach var="pret" items="${prets}">
        <tr>
            <td>${pret.idPret}</td>
            <td>${pret.datePret}</td>
            <td>${pret.exemplaire.livre.titre}</td>
            <td>#${pret.exemplaire.idExemplaire}</td>
            <td>${pret.adherent.adherentName}</td>
            <td>
                <c:out value="${pret.historiques[pret.historiques.size()-1].statut.statut}"/>
            </td>
        </tr>
    </c:forEach>
</table>

<p style="margin-top:20px;">
    <a href="${pageContext.request.contextPath}/pret/form">Nouveau prêt</a>
</p>

</body>
</html> -->
