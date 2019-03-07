<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20+ | Creer un Forum</title>

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
			<li>Creer un Forum</li>
		</ol>


		<div class="row">
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Créer un forum</h1>
					<span class="${empty erreurs ? 'text-success' : 'text-danger'}">${resultatCreerForum}</span>
					<span class="${empty erreurs ? 'text-success' : 'text-danger'}">${erreurs['alreadyExisting']}</span>
				</header>




				<div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">Formulaire de création de forum</h3>
							<hr>

							<form action="CreerForum" method="post">
								<span class="text-danger">${ erreurLog}</span>
								<div class="top-margin">

									<label>Titre du forum <span class="text-danger">*</span></label>
									<span class="text-danger">${erreurs['titre']}</span> <input
										type="text" class="form-control" name="titre">
								</div>
								<div class="top-margin">

									<label>Description <span class="text-danger">*</span></label> <span
										class="text-danger">${erreurs['description']}</span>
									<textarea class="" name="description" class="form-control"
										style="width: 100%; height: 300px;"></textarea>
									<input type="hidden" name="forum" value="${forum.getFno() }" />
								</div>

								<hr>

								<div class="row">
									<div class="col-md-6">
										<label>Forum privé ?</label> <input type="checkbox"
											name="isPrivate" value="true" /> <span class="erreur">${erreurs['forum']}</span>
									</div>
									<div class="col-md-6 text-right">
										<button class="btn btn-action" type="submit">Validé</button>
									</div>
								</div>
							</form>
						</div>
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