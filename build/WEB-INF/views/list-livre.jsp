<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%@ page import="entities.LivreEntity" %>
        <%@ page import="java.util.List" %>
            <%@ page import="entities.CategorieAgeEntity" %>

                <% List<LivreEntity> livres = (List<LivreEntity>) request.getAttribute("listLivre");
                        %>

                        <!DOCTYPE html>
                        <html lang="fr">

                        <head>
                            <meta charset="UTF-8">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Liste des livres</title>
                            <style>
                                /* Reset et base */
                                * {
                                    margin: 0;
                                    padding: 0;
                                    box-sizing: border-box;
                                }

                                body {
                                    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                                    line-height: 1.6;
                                    color: #333;
                                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                    min-height: 100vh;
                                }

                                /* Navbar principale */
                                .navbar {
                                    background: rgba(255, 255, 255, 0.95);
                                    backdrop-filter: blur(10px);
                                    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                                    padding: 1rem 0;
                                    position: sticky;
                                    top: 0;
                                    z-index: 1000;
                                    border-bottom: 3px solid #667eea;
                                }

                                .nav-container {
                                    max-width: 1200px;
                                    margin: 0 auto;
                                    display: flex;
                                    justify-content: space-between;
                                    align-items: center;
                                    padding: 0 2rem;
                                }

                                .nav-logo {
                                    font-size: 1.8rem;
                                    font-weight: bold;
                                    color: #667eea;
                                    text-decoration: none;
                                    letter-spacing: 1px;
                                }

                                .nav-menu {
                                    display: flex;
                                    gap: 2rem;
                                    list-style: none;
                                }

                                .nav-link {
                                    text-decoration: none;
                                    color: #333;
                                    font-weight: 500;
                                    padding: 0.5rem 1rem;
                                    border-radius: 25px;
                                    transition: all 0.3s ease;
                                    position: relative;
                                    overflow: hidden;
                                }

                                .nav-link::before {
                                    content: '';
                                    position: absolute;
                                    top: 0;
                                    left: -100%;
                                    width: 100%;
                                    height: 100%;
                                    background: linear-gradient(90deg, transparent, rgba(102, 126, 234, 0.2), transparent);
                                    transition: left 0.5s;
                                }

                                .nav-link:hover::before {
                                    left: 100%;
                                }

                                .nav-link:hover {
                                    color: #667eea;
                                    background: rgba(102, 126, 234, 0.1);
                                    transform: translateY(-2px);
                                }

                                /* Container principal */
                                .main-container {
                                    max-width: 1200px;
                                    margin: 2rem auto;
                                    padding: 0 2rem;
                                }

                                /* Cards pour les formulaires */
                                .card {
                                    background: rgba(255, 255, 255, 0.95);
                                    backdrop-filter: blur(10px);
                                    border-radius: 20px;
                                    padding: 2rem;
                                    margin-bottom: 2rem;
                                    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                                    border: 1px solid rgba(255, 255, 255, 0.2);
                                }

                                .card h2 {
                                    color: #667eea;
                                    margin-bottom: 1.5rem;
                                    font-size: 1.8rem;
                                    text-align: center;
                                    position: relative;
                                }

                                .card h2::after {
                                    content: '';
                                    position: absolute;
                                    bottom: -10px;
                                    left: 50%;
                                    transform: translateX(-50%);
                                    width: 60px;
                                    height: 3px;
                                    background: linear-gradient(90deg, #667eea, #764ba2);
                                    border-radius: 2px;
                                }

                                /* Formulaires */
                                .form-group {
                                    margin-bottom: 1.5rem;
                                }

                                .form-group label {
                                    display: block;
                                    margin-bottom: 0.5rem;
                                    font-weight: 600;
                                    color: #555;
                                }

                                .form-control {
                                    width: 100%;
                                    padding: 0.8rem;
                                    border: 2px solid #e0e0e0;
                                    border-radius: 10px;
                                    font-size: 1rem;
                                    transition: all 0.3s ease;
                                    background: rgba(255, 255, 255, 0.9);
                                }

                                .form-control:focus {
                                    outline: none;
                                    border-color: #667eea;
                                    box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
                                    background: white;
                                }

                                .form-control:hover {
                                    border-color: #667eea;
                                }

                                /* Boutons */
                                .btn {
                                    padding: 0.8rem 2rem;
                                    border: none;
                                    border-radius: 25px;
                                    font-size: 1rem;
                                    font-weight: 600;
                                    cursor: pointer;
                                    transition: all 0.3s ease;
                                    text-transform: uppercase;
                                    letter-spacing: 1px;
                                    position: relative;
                                    overflow: hidden;
                                }

                                .btn-primary {
                                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                    color: white;
                                }

                                .btn-primary::before {
                                    content: '';
                                    position: absolute;
                                    top: 0;
                                    left: -100%;
                                    width: 100%;
                                    height: 100%;
                                    background: linear-gradient(90deg, transparent, rgba(255, 255, 255, 0.2), transparent);
                                    transition: left 0.5s;
                                }

                                .btn-primary:hover::before {
                                    left: 100%;
                                }

                                .btn-primary:hover {
                                    transform: translateY(-2px);
                                    box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
                                }

                                .btn-success {
                                    background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
                                    color: white;
                                }

                                .btn-success:hover {
                                    transform: translateY(-2px);
                                    box-shadow: 0 5px 15px rgba(40, 167, 69, 0.4);
                                }

                                /* Messages */
                                .message {
                                    padding: 1rem;
                                    border-radius: 10px;
                                    margin-bottom: 1rem;
                                    font-weight: 500;
                                    text-align: center;
                                }

                                .message.success {
                                    background: linear-gradient(135deg, #d4edda 0%, #c3e6cb 100%);
                                    color: #155724;
                                    border: 1px solid #c3e6cb;
                                }

                                .message.error {
                                    background: linear-gradient(135deg, #f8d7da 0%, #f1b0b7 100%);
                                    color: #721c24;
                                    border: 1px solid #f1b0b7;
                                }

                                /* Listes de prêts */
                                .pret-list {
                                    background: rgba(255, 255, 255, 0.1);
                                    border-radius: 15px;
                                    padding: 1.5rem;
                                    margin-top: 2rem;
                                    border: 1px solid rgba(255, 255, 255, 0.2);
                                }

                                .pret-list h3 {
                                    color: #667eea;
                                    margin-bottom: 1rem;
                                    font-size: 1.4rem;
                                }

                                /* Responsive design */
                                @media (max-width: 768px) {
                                    .nav-container {
                                        flex-direction: column;
                                        gap: 1rem;
                                        padding: 0 1rem;
                                    }

                                    .nav-menu {
                                        flex-direction: column;
                                        gap: 1rem;
                                        width: 100%;
                                        text-align: center;
                                    }

                                    .main-container {
                                        padding: 0 1rem;
                                    }

                                    .card {
                                        padding: 1.5rem;
                                    }

                                    .form-control {
                                        padding: 0.7rem;
                                    }
                                }

                                /* Animations */
                                @keyframes fadeIn {
                                    from {
                                        opacity: 0;
                                        transform: translateY(20px);
                                    }

                                    to {
                                        opacity: 1;
                                        transform: translateY(0);
                                    }
                                }

                                .card {
                                    animation: fadeIn 0.6s ease;
                                }

                                /* Styles spécifiques pour les selects */
                                select.form-control {
                                    cursor: pointer;
                                    background-image: linear-gradient(45deg, transparent 50%, #667eea 50%),
                                        linear-gradient(135deg, #667eea 50%, transparent 50%);
                                    background-position: calc(100% - 20px) calc(1em + 2px),
                                        calc(100% - 15px) calc(1em + 2px);
                                    background-size: 5px 5px, 5px 5px;
                                    background-repeat: no-repeat;
                                    appearance: none;
                                    -webkit-appearance: none;
                                    -moz-appearance: none;
                                }

                                select.form-control::-ms-expand {
                                    display: none;
                                }

                                /* Hover effects pour les options */
                                select.form-control option {
                                    background: white;
                                    color: #333;
                                    padding: 0.5rem;
                                }

                                select.form-control option:hover {
                                    background: #667eea;
                                    color: white;
                                }

                                /* Styles pour les dates */
                                input[type="date"].form-control {
                                    cursor: pointer;
                                }

                                input[type="date"].form-control::-webkit-calendar-picker-indicator {
                                    background: transparent;
                                    bottom: 0;
                                    color: transparent;
                                    cursor: pointer;
                                    height: auto;
                                    left: 0;
                                    position: absolute;
                                    right: 0;
                                    top: 0;
                                    width: auto;
                                }

                                /* Amélioration des formulaires en ligne */
                                .form-inline {
                                    display: flex;
                                    gap: 1rem;
                                    align-items: end;
                                    flex-wrap: wrap;
                                }

                                .form-inline .form-group {
                                    flex: 1;
                                    min-width: 200px;
                                }

                                .form-inline .btn {
                                    margin-top: 0;
                                }

                                /* Styles pour les tables si nécessaire */
                                .table-responsive {
                                    overflow-x: auto;
                                    border-radius: 15px;
                                    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
                                }

                                table {
                                    width: 100%;
                                    border-collapse: collapse;
                                    background: rgba(255, 255, 255, 0.95);
                                    border-radius: 15px;
                                    overflow: hidden;
                                }

                                th,
                                td {
                                    padding: 1rem;
                                    text-align: left;
                                    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
                                }

                                th {
                                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                    color: white;
                                    font-weight: 600;
                                }

                                tr:hover {
                                    background: rgba(102, 126, 234, 0.05);
                                }

                                /* Styles pour les liens de navigation principaux */
                                .nav-links-old {
                                    display: flex;
                                    flex-direction: column;
                                    gap: 1rem;
                                    align-items: center;
                                    margin-top: 2rem;
                                }

                                .nav-links-old a {
                                    display: inline-block;
                                    padding: 1rem 2rem;
                                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                    color: white;
                                    text-decoration: none;
                                    border-radius: 25px;
                                    font-weight: 600;
                                    transition: all 0.3s ease;
                                    text-transform: uppercase;
                                    letter-spacing: 1px;
                                    min-width: 200px;
                                    text-align: center;
                                }

                                .nav-links-old a:hover {
                                    transform: translateY(-3px);
                                    box-shadow: 0 8px 25px rgba(102, 126, 234, 0.4);
                                }

                                /* Effet de loading */
                                .loading {
                                    display: inline-block;
                                    width: 20px;
                                    height: 20px;
                                    border: 3px solid rgba(102, 126, 234, 0.3);
                                    border-radius: 50%;
                                    border-top-color: #667eea;
                                    animation: spin 1s ease-in-out infinite;
                                }

                                @keyframes spin {
                                    to {
                                        transform: rotate(360deg);
                                    }
                                }
                            </style>
                        </head>

                        <body>
                            <!-- ✅ Navbar -->
                            <nav class="navbar">
                                <div class="nav-container">
                                    <a href="accueil" class="nav-logo">📚 Bibliothèque</a>
                                    <ul class="nav-menu">
                                        <li><a href="accueil" class="nav-link">Accueil</a></li>
                                        <li><a href="creationAdherent" class="nav-link">Nouveau Adhérent</a></li>
                                        <li><a href="listelivre" class="nav-link">Liste des Livres</a></li>
                                        <li><a href="retour" class="nav-link">Retour de Livre</a></li>
                                                            <li><a href="prolongement" class="nav-link">Faire un prolongement</a></li>

                                    </ul>
                                </div>
                            </nav>

                            <div class="main-container">
                                <div class="card">
                                    <h2>📚 Liste des livres</h2>
                                    <div class="table-responsive">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>Titre</th>
                                                    <th>Auteur</th>
                                                    <th>ID Catégorie</th>
                                                    <th>Tranche d'âge</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (LivreEntity livre : livres) { CategorieAgeEntity
                                                    categorie=livre.getCategorieAge(); String trancheAge="" ; if
                                                    (categorie !=null) { trancheAge=(categorie.getAgeMax() !=null) ?
                                                    categorie.getAgeMin() + "-" + categorie.getAgeMax() + " ans" :
                                                    categorie.getAgeMin() + "+ ans" ; } %>
                                                    <tr>
                                                        <td>
                                                            <%= livre.getTitre() !=null ? livre.getTitre() : "" %>
                                                        </td>
                                                        <td>
                                                            <%= livre.getAuteur() !=null ? livre.getAuteur() : "" %>
                                                        </td>
                                                        <td>
                                                            <%= categorie !=null ? categorie.getIdCategorieAge() : "N/A"
                                                                %>
                                                        </td>
                                                        <td>
                                                            <%= trancheAge %>
                                                        </td>
                                                    </tr>
                                                    <% } %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </body>

                        </html>