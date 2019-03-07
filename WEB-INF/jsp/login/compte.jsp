<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20 + | Mon Compte</title>

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
					<li><a href="Contact">Contact</a></li>
					<li><a class="btn active" href="#">${empty utilisateur? "Connexion" : "Mon Compte" }</a>
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
			<li class="active">Mon compte</li>
		</ol>
		<div class="row">
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Mon compte</h1>
				</header>
				<c:choose>
					<c:when test="${empty sessionScope.utilisateur})">
						<p>Vous n'êtes pas connecté, veuillez vous redirigez vers la
							page de connexion.</p>
					</c:when>
					<c:otherwise>
						<div class="col-md-10 col-md-offset-1">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-4"><label>Image</label></div>
										<div class="col-md-8">
											<img src="${utilisateur.getImage() }" alt="aucune image">
										</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-md-4"><label>Nom</label></div>
										<div class="col-md-8">${utilisateur.getNom() }</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-md-4">Prénom</div>
										<div class="col-md-8">${utilisateur.getPrenom() }</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-md-4">Email</div>
										<div class="col-md-8">${utilisateur.getEmail() }</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-md-4">Mot de passe</div>
										<div class="col-md-8">
											<p>${utilisateur.getPwd() }
										</div>
									</div>

									<div class="row">
										<div class="col-md-6 col-md-offset-6">
											<a href="Login?deco=true" class="btn btn-action">Déconnexion</a>
										</div>
									</div>

								</div>
							</div>
						</div>

					</c:otherwise>
				</c:choose>
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
									href="Equipe">Equipe</a> | <a href="Contact">Contact</a> | <a
									href="#">${empty utilisateur? "Connexion" : "Mon Compte" }</a>
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