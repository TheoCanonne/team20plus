<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team 20+ | Creer un Sujet</title>

<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.css" media="screen">
<link rel="stylesheet" href="css/main.css">
</head>

<body>

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
					<li><a href="Forum" class="active">Forum</a></li>
					<li><a href="Equipe">Equipe</a></li>
					<li><a href="Contact">Contact</a></li>
					<li><a class="btn active" href="Login">${empty utilisateur? "Connexion" : "Mon Compte" }</a>
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
			<li><a href="Sujet?forum=${forum.getFno()}">Sujet</a></li>
			<li>Cr�er un Sujet</li>
		</ol>
		<div class="row">
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Cr�er un sujet</h1>
					<span class="${empty erreurs ? 'text-success' : 'text-danger'}">${resultat}</span>
				</header>




				<div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">Formulaire de cr�ation de sujet</h3>
							<p class="text-center text-muted">Forum selectionn� :
								${forum.getTitre()}</p>
							<hr>

							<form action="CreerSujet" method="post">
								<span class="text-danger">${ erreurLog}</span>
								<div class="top-margin">

									<label>Titre du sujet <span class="text-danger">*</span></label>
									<span class="text-danger">${erreurs['titre']}</span> <input
										type="text" class="form-control" name="titre">
								</div>
								<div class="top-margin">

									<label>Description <span class="text-danger">*</span></label>
									<span class="text-danger">${erreurs['description']}</span>
									<textarea class="" name="description" class="form-control" style="width: 100%; height: 300px;"></textarea>
									<input type="hidden" name="forum" value="${forum.getFno() }" />
								</div>

								<hr>

								<div class="row">
									<div class="col-lg-12 text-right">
										<button class="btn btn-action" type="submit">Valid�</button>
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
								diverses probl�me n'hesitez pas � nous l'indiquez.</p>
							<p>Vous pouvez aussi contacter nos membre sur leur page
								Facebook o� les membres sont d'autant plus actif !</p>

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
								Developper par Th�o Canonne.</p>
						</div>
					</div>

				</div>
				<!-- /row of widgets -->
			</div>
		</div>
	</footer>

	<script type="text/javascript"
		src="http://js.nicedit.com/nicEdit-latest.js"></script>
	<script type="text/javascript">
        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
  </script>
</body>
</html>