<%@ page import="persistance.User" %><%--
  Created by IntelliJ IDEA.
  User: yniak
  Date: 18/02/2022
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menu</title>
    <link rel="stylesheet" href="./js/style.css">
</head>
<body class="has-background-info-light">
<jsp:include page="./Navbar.jsp"/>
<div class="level ">
    <div class="level-item is-flex-direction-column">
        <%
            User user = (User) session.getAttribute("user");
        %>
        <h1 class="title m-3">Bienvenue <%=user.name()%></h1>
        <div>
            <a href="/emprunt" class="button has-background-link has-text-white-bis">Emprunter un document</a>
            <a href="/retour" class="button has-background-link has-text-white-bis">Retourner un document</a>
        </div>
    </div>
</div>
</body>
</html>
