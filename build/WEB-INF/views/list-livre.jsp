<%@ page import="entities.LivreEntity" %>
    <%@ page import="java.util.List" %>

        <% List<LivreEntity> tags = (List<LivreEntity>) request.getAttribute("listLivre");
                %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Listelivre</title>
                </head>

                <body>
                    <h2>Liste</h2>
                    <table>
                        <tr>
                            <th>Tithe</th>
                            <th>auteur</th>
                            <th>Catégorie Age</th>
                        </tr>
                        <% for (LivreEntity tag : tags) { %>
                            <tr>
                                <td><%= tag.getTitre() %>"></td>
                                <td><%= tag.getAuteur() %>"></td>
                                <td><%= tag.getCategorieAge() %>"></td>
                            </tr>
                            <% } %>
                    </table>

                </body>

                </html>