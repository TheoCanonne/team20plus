<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20+ | Equipe</title>

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
					<li class="active"><a href="#">Equipe</a></li>
					<li><a href="Contact">Contact</a></li>
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
			<li>Equipe</li>
		</ol>

		<div class="row">
			<header class="page-header">
				<h1 class="page-title">Notre Equipe</h1>
				<h3 class="thin text-center">Membre de l'equipe</h3>
			</header>

			<c:set var="cpt" value="0"></c:set>
			<c:forEach var="i" begin="0" end="${listUtilisateur.size()%3 -1}">
				<div class="row">
					<c:forEach var="j" begin="0" end="3">
						<c:if test="${listUtilisateur.size() > cpt }">

							<div class="col-md-3 col-md-offset-1"
								style="border: solid lightgrey 1px;">

								<div class="row text-center">
									<h2 class="thin">${ listUtilisateur.get(cpt).getNom()}
										&nbsp ${listUtilisateur.get(cpt).getPrenom() }</h2>
								</div>

								<div class="row text-center">
									<img src="${listUtilisateur.get(cpt).getImage() }"
										width="250px" />
								</div>
								<div class="row">
									<div class="col-md-6  text-left">
										<h2 class="thin"
											style="color: darkgrey; font-style: italic; font-size: 20px;">${ listUtilisateur.get(cpt).getLogin()}
										</h2>
									</div>
									<div class="col-md-6 text-right">
										<h2 class="thin"
											style="color: darkgrey; font-style: italic; font-size: 20px;">
											${listUtilisateur.get(cpt).getEmail() }</h2>
									</div>

								</div>

							</div>
						</c:if>

						<c:set var="cpt" value="${cpt+1 }"></c:set>
					</c:forEach>
				</div>
				<br />
			</c:forEach>



			<c:out value="${listUtilisateur.size() }"></c:out>

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
									href="#">Equipe</a> | <a href="Contact">Contact</a> | <a
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