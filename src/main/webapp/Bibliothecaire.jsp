<%@ page import="persistance.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
<div class="level">
<div class="level-item is-flex-direction-column">
    <%
        User user = (User) session.getAttribute("user");
    %>
    <h1 class="title m-3">Bienvenue <%=user.name()%></h1>
    <a href="/create" class="button has-background-link has-text-white-bis">Ajouter un document</a>
</div>
</div>
</body>
</html>
