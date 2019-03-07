<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20 + | Contact</title>
<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.css" media="screen">
<link rel="stylesheet" href="css/main.css">
</head>

<body class="home">
	<!-- Nav Bar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="Accueil">Accueil</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a href="Accueil">Accueil</a></li>
					<li><a href="Forum">Forum</a></li>
					<li><a href="Equipe">Equipe</a></li>
					<li class="active"><a href="#">Contact</a></li>
					<li><a class="btn" href="Login">${empty utilisateur? "Connexion" : "Mon Compte" }</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- /Nav Bar -->

	<header id="head" class="secondary"></header>

	<div class="container">
		<ol class="breadcrumb">
			<li><a href="Accueil">Accueil</a></li>
			<li class="active">Contact</li>
		</ol>

		<div class="row">
			<article class="col-xs-12 maintcontent">
				<header class="page-header">
					<h1 class="page-title">Contactez nous</h1>
				</header>

				<c:choose>
					<c:when test="${not empty resultat && empty erreurs}">
						<span class="text-success">${resultat}</span>
					</c:when>
					<c:when test="${not empty resultat && not empty erreurs}">
						<span class="text-danger">${resultat}</span>
					</c:when>
				</c:choose>

				<div class="col-xs-12 col-sm-12">
					<div class="panel panel-default">
						<h3 class="thin text-center">Remplissez le formulaire
							si-dessous.</h3>
						<hr>


						<form method="post" action="Contact">
							<div class="row">

								<div class="col-sm-3 col-sm-offset-1">

									<label>Civilité <span class="text-danger">*</span></label> <span
										class="text-danger">${erreurs['civilite']}</span>
								</div>
								<div class="col-sm-3">
									<input type="radio" name="civilite" value="M"> <label>Homme</label>
								</div>
								<div class="col-sm-3">
									<input type="radio" name="civilite" value="F"> <label>Femme</label>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-sm-3 col-sm-offset-1">

									<label>Nom<span class="text-danger">*</span></label> <span
										class="text-danger">${erreurs['nom']}</span><input type="text"
										class="form-control" name="nom"
										value="<c:if test="${not empty utilisateur }">${utilisateur.getNom()}</c:if>" />
								</div>
								<div class="col-sm-3">
									<label>Prénom <span class="text-danger">*</span>
									</label><span class="text-danger">${erreurs['prenom']}</span> <input
										type="text" class="form-control" name="prenom"
										value="<c:if test="${not empty utilisateur }">${utilisateur.getPrenom()}</c:if>" />
								</div>
								<div class="col-sm-3">
									<label>Email <span class="text-danger">*</span>
									</label> <span class="text-danger">${erreurs['email']}</span><input
										class="form-control" type="text" name="email"
										value="<c:if test="${not empty utilisateur }">${utilisateur.getEmail()}</c:if>" />
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-sm-10 col-sm-offset-1">
									<label>Message <span class="text-danger">*</span>
									</label> <span class="text-danger">${erreurs['question']}</span>
									<textarea placeholder="Ecrivez votre message ici."
										class="form-control" rows="9" name="question"></textarea>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-sm-offset-6 col-sm-5 text-right">
									<input type="submit" value="Envoyer" class="btn btn-action" />
								</div>
							</div>
							<br>
						</form>
					</div>


				</div>
			</article>
		</div>

	</div>


	<footer id="footer">

		<div class="footer1">
			<div class="container">
				<div class="row">

					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>
								email de contact:<br> <a href="mailto:#">team20plus.contact@gmail.com</a><br>
							</p>
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">Suivez nous !</h3>
						<div class="widget-body">
							<p class="follow-me-icons">
								<a href="https://www.facebook.com/team20plus/"><i
									class="fa fa-facebook fa-2"></i></a>
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<h3 class="widget-title">Notre site</h3>
						<div class="widget-body">
							<p>Notre site est actuellement en construction, si vous avez
								diverses problème n'hesitez pas à nous l'indiquez.</p>
							<p>Vous pouvez aussi contacter nos membre sur leur page
								Facebook où les membres sont d'autant plus actif !</p>

						</div>
					</div>

				</div>
				<!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="simplenav">
								<a href="Accueil">Acceuil</a> | <a href="Forum">Forum</a> | <a
									href="Equipe">Equipe</a> | <a href="#">Contact</a> | <a
									href="Login">${empty utilisateur? "Connexion" : "Mon Compte" }</a>
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">Copyright &copy; 2019, Team20+.
								Developper par Théo Canonne.</p>
						</div>
					</div>

				</div>
				<!-- /row of widgets -->
			</div>
		</div>
	</footer>

</body>
</html>