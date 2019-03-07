<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20+ | Sujet</title>
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
					<li class="active"><a href="#">Forum</a></li>
					<li><a href="Equipe">Equipe</a></li>
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
			<li><a href="Forum">Forum</a></li>
			<li>Sujet</li>
		</ol>

		<div class="row">
			<header class="page-header">
				<h1 class="page-title">Liste des Sujets</h1>
			</header>

			<c:if test="${not empty param.succesCreerForum }">
				<p class="text-success">Ton forum à bien était créé.</p>
			</c:if>

			<article class="col-md-10">
				<c:if test="${not empty listSujet }">
					<header class="page-header">
						<h1 class="page-title">${forum.getTitre() }</h1>
					</header>
					<div class="table-responsive">
						<table class="table table-bordered table-hover ">
							<thead>
								<tr>
									<th>Titre</th>
									<th>Description</th>
								</tr>
							</thead>
							<c:forEach items="${listSujet }" var="value">
								<tr
									onclick="window.location='Reponse?sujet=${value.getSno() }';">
									<th class="col-xs-4">
										<div class="row">
											<div class="col-md-12">
												<h4 class="text-center">${value.getTitre() }</h4>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 col-md-offset-3">
												<img
													src="<c:choose><c:when test="${not empty value.getUtilisateur().getImage()}">${value.getUtilisateur().getImage()}</c:when><c:otherwise>data/image/utilisateur/vide.jpg</c:otherwise></c:choose>">
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<span class="thin" style="color: grey;">${value.getDate() }</span>
											</div>
											<div class="col-md-6 text-right">
												<span class="thin" style="color: grey;">${value.getUtilisateur().getLogin() }</span>
											</div>
										</div>

									</th>
									<th class="col-xs-8"><span style="color: black;">${value.getContenu() }</span></th>
								</tr>
								<br />
							</c:forEach>
						</table>

					</div>
				</c:if>
			</article>
			<aside class="col-md-2 sidebar sidebar-right">
				<div class="row widget">
					<div class="col-xs-12">
						<div class="row">
							<div class="col-md-12">
								<h4>Recherche :</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<form action="Sujet" method="post">
									<input type="search" value="Rechercher un sujet ..."
										name="recherche" />
										<input type="hidden" name="forum" value="${forum.getFno() }"/>
								</form>
							</div>
						</div>

					</div>
				</div>
				<c:choose>
					<c:when test="${empty utilisateur }">
						<div class="row widget">
							<div class="col-xs-12">
								<a href="Login" class="btn btn-action">Se connecter</a>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="row widget">
							<div class="col-xs-12">
								<a href="CreerSujet?forum=${param['forum']}"
									class="btn btn-action">Creer un Sujet</a>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</aside>
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
								<a href="Accueil">Acceuil</a> | <a href="#">Forum</a> | <a
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
</body>
</html>