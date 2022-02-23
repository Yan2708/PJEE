<%--
  Created by IntelliJ IDEA.
  User: yniak
  Date: 23/02/2022
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AjoutDocument</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>

<div class="m-3">
    <h1 class="title"> Ajouter un document </h1>
    <form method="post" action="/create"  id="ajoutForm" class="form">
        <div class="field">
            <label class="label">Titre</label>
            <div class="control">
                <input class="input" type="text" placeholder="titre" name="titre">
            </div>
        </div>
        <div class="field">
            <label class="label">Auteur</label>
            <div class="control">
                <input class="input" type="text" placeholder="auteur" name="auteur">
            </div>
        </div>
        <div class="field">
            <label class="label">Type</label>
            <div class="control">
                <select name="type" form="ajoutForm">
                    <option value="1">Livre</option>
                    <option value="2">DVD</option>
                </select>
            </div>
        </div>
        <p class="has-text-success">${success}</p>
        <p class="has-text-danger">${error}</p>
        <button class="button has-background-link has-text-white-bis" type="submit">Soumettre</button>
    </form>


</div>
</body>
</html>