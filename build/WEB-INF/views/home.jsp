<%@ page import="entities.TagEntity" %>
    <%@ page import="java.util.List" %>

        <% List<TagEntity> tags = (List<TagEntity>) request.getAttribute("listTag");
                %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Document</title>
                </head>

                <body>
                    <li>
                        <ul>
                            <% for (TagEntity tag : tags) { %>
                                <li>
                                    <%= tag.getTagName() %>
                                </li>
                                <% } %>
                        </ul>
                    </li>
                </body>

                </html>