<%@ page import="entities.TypeAccountEntity" %>
    <%@ page import="java.util.List" %>

        <% List<TypeAccountEntity> tags = (List<TypeAccountEntity>) request.getAttribute("listAccountType");
                %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Inscription</title>
                </head>

                <body>
                    <h2>Page d'inscription</h2>
                    <form action="inscription" method="post">
                        <input type="text" name="username">
                        <input type="password" name ="motdepasse">
                        <select name="typeaccount" id="">
                            <% for (TypeAccountEntity tag : tags) { %>
                            <option value="<%= tag.getIdTypeAccount() %>"><%= tag.getAccountType() %></option>
                            <% } %>
                        </select>
                        <input type="submit">
                    </form>
                    <li>
                        <ul>
                            <% for (TypeAccountEntity tag : tags) { %>
                                <li>
                                    <%= tag.getAccountType() %>
                                </li>
                                <% } %>
                        </ul>
                    </li>
                </body>

                </html>