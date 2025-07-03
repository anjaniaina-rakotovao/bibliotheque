<%@ page import="entities.ProfilEntity" %>
    <%@ page import="java.util.List" %>

        <% List<ProfilEntity> tags = (List<ProfilEntity>) request.getAttribute("profils");
                %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Inscription Adhérent</title>
                </head>

                <body>
                    <h2>Page d'insertion d'adhérent</h2>
                    <form action="creationAdherent" method="post">
                        <p>Nom</p>
                        <input type="text" name="adherentname">
                        <select name="profilType" id="">
                            <% for (ProfilEntity tag : tags) { %>
                            <option value="<%= tag.getIdProfil() %>"><%= tag.getProfilType() %></option>
                            <% } %>
                        </select>
                        <p>Date de naissance</p>
                        <input type="date" name="datenaissance" required>
                        <p>Date de création du compte</p>
                        <input type="date" name="datecreation" required>
                        <input type="submit">
                    </form>
        
                </body>

                </html>