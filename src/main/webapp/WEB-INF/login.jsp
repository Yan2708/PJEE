<%--
  Created by IntelliJ IDEA.
  User: yniak
  Date: 18/02/2022
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="./js/style.css">
    <title>Mediatheque | login</title>
</head>
<body class="has-background-info-light">
<div class="level">
    <div class="level-item is-flex-direction-column">
    <h1 class="title">Médiathèque</h1>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="field">
            <label class="label">Identifiant</label>
            <div class="control">
                <input class="input" type="text" placeholder="user" name="username">
            </div>
        </div>
        <div class="field">
            <label class="label">mot de passe</label>
            <div class="control">
                <input class="input" type="text" placeholder="mdp" name="password">
            </div>
        </div>
        <p class="has-text-danger">${error}</p>
        <button class="button has-background-link has-text-white-bis" type="submit">Se connecter</button>
    </form>
</div>
</div>
</body>
</html>
