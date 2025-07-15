<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <style>
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
            font-family: 'Segoe UI', sans-serif;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background: white;
            padding: 2.5rem 3rem;
            border-radius: 15px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            width: 100%;
        }

        h2 {
            text-align: center;
            color: #667eea;
            margin-bottom: 2rem;
        }

        label, p {
            font-weight: 600;
            margin: 1rem 0 0.5rem;
        }

        input {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid #ccc;
            border-radius: 10px;
            margin-bottom: 1rem;
            font-size: 1rem;
        }

        input[type="submit"] {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            border: none;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background: linear-gradient(135deg, #5a67d8, #6b46c1);
        }

        .link {
            text-align: center;
            margin-top: 1rem;
        }

        .link a {
            color: #667eea;
            text-decoration: none;
            font-weight: bold;
        }

        .link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Connexion</h2>
    <form action="loginuser" method="post">
        <p>Nom d'utilisateur</p>
        <input type="text" name="username" required>

        <p>Mot de passe</p>
        <input type="password" name="motdepasse" required>

        <input type="submit" value="Se connecter">
    </form>

    <div class="link">
        Pas encore de compte ? <a href="inscription">Créer un compte</a>
    </div>
</div>
</body>
</html>
