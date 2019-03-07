<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20+</title>

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
					<li class="active"><a href="#">Accueil</a></li>
					<li><a href="Forum">Forum</a></li>
					<li><a href="Equipe">Equipe</a></li>
					<li><a href="Contact">Contact</a></li>
					<li><a class="btn" href="Login">${empty utilisateur? "Connexion" : "Mon Compte" }</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- /Nav Bar -->

	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
				<h1 class="lead text-uppercase">
					BIENVENUE
					<c:choose>
						<c:when test="${empty utilisateur}">
							SUR LE SITE TEAM 20+
						</c:when>
						<c:otherwise>
							${utilisateur.getLogin()} !
						</c:otherwise>
					</c:choose>
				</h1>
				<p class="tagline">Le site des passionnés de pêche</p>
				<p>
					<a class="btn btn-default btn-lg" role="button" href="Forum">FORUM</a>
					<a class="btn btn-action btn-lg" role="button"
						href="https://www.facebook.com/team20plus/" target="blank">FACEBOOK</a>
				</p>
			</div>
		</div>
	</header>
	<!-- /Header -->

	<!-- Highlights - jumbotron -->
	<div class="jumbotron">
		<div class="container">

			<h3 class="text-center thin">Que faire sur notre site ?</h3>

			<div class="row">
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption">
						<h4>
							<i class="fa fa-cogs fa-5"></i>Parcourez nos forums
						</h4>
					</div>
					<div class="h-body text-center">
						<p>Vous avez à disposition plusieurs forums ouvert à tous pour
							discuter sur des sujets diverses. N'hesitez pas à vous connectez
							pour créer vos sujets.</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption">
						<h4>
							<i class="fa fa-flash fa-5"></i>Notre équipe
						</h4>
					</div>
					<div class="h-body text-center">
						<p>Vous ne nous connaissez pas encore ? vous avez à votre
							disposition une description de chacun des membres de notre équipe
							ainsi que les moyens disponible pour les contacter
							individuellement.</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption">
						<h4>
							<i class="fa fa-heart fa-5"></i>Nos coups de coeurs
						</h4>
					</div>
					<div class="h-body text-center">
						<p>Notre equipe est composé de passionés de pêche, et elle
							partage régulièrement ses dernières trouvaille dans le domaine
							ainsi que leur différents résultats durant les dernières sessions
							de pêche.</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption">
						<h4>
							<i class="fa fa-smile-o fa-5"></i>Contactez-nous
						</h4>
					</div>
					<div class="h-body text-center">
						<p>Vous avez une question ou vous souhaitez simplement entrer
							en contact avec nous ? Rendez-vous sur notre formulaire de
							contact.</p>
					</div>
				</div>
			</div>
			<!-- /row  -->

		</div>
	</div>
	<!-- /Highlights -->


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
								<a href="#">Acceuil</a> | <a href="Forum">Forum</a> | <a
									href="Equipe">Equipe</a> | <a href="Contact">Contact</a> | <a
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

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="js/headroom.min.js"></script>
	<script src="js/jQuery.headroom.min.js"></script>
	<script src="js/template.js"></script>
</body>
</html>