<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20 + | Enregistrement</title>

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
			<li><a href="Login">Connexion</a></li>
			<li class="active">Inscription</li>
		</ol>

		<div class="row">
			<article class="col-xs-12 maintcontent">
				<header class="page-header">
					<h1 class="page-title">Inscription</h1>
				</header>

				<div class="col-xs-12 col-sm-12">
					<div class="panel panel-default">
						<h3 class="thin text-center">Remplissez le formulaire
							si-dessous.</h3>
							<span class="${empty erreurs ? 'text-success' : 'text-danger'}">${resultat}</span>
						<hr>


						<form method="post" action="Enregistrement" enctype="multipart/form-data">

							<br>
							<div class="row">
								<div class="col-sm-4 col-md-offset-1">

									<label>Nom de compte<span class="text-danger">*</span></label>
									<span class="text-danger">${erreurs['login']}</span><input
										type="text" class="form-control" name="login" />
								</div>
								<div class="col-sm-4 col-md-offset-1">
									<label>Email <span class="text-danger">*</span>
									</label> <span class="text-danger">${erreurs['email']}</span><input
										class="form-control" type="text" name="email" />
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-4 col-md-offset-1">
									<label>Mot de passe <span class="text-danger">*</span>
									</label><span class="text-danger">${erreurs['pwd']}</span> <input
										type="password" class="form-control" name="pwd" />
								</div>
								<div class="col-md-4 col-md-offset-1">
									<label for="confirmation">Confirmation du mot de passe
										<span class="text-danger">*</span>
									</label> <span class="erreur">${erreurs['confirm']}</span> <input
										type="password" name="confirm" class="form-control" />
								</div>
							</div>
							<br/>

							<div class="row">

								<div class="col-md-4 col-md-offset-1">

									<label>Nom </label> <span class="text-danger">${erreurs['nom']}</span>
									<input type="text" name="nom" class="form-control">
								</div>
								<div class="col-md-4 col-md-offset-1">
									<label>Prénom </label> <span class="text-danger">${erreurs['prenom']}</span>
									<input type="text" name="prenom" class="form-control">
								</div>
							</div>
							<br />

							<div class="row">
								<div class="col-md-10 col-md-offset-1">
									<label>Logo </label> <span class="text-danger">${erreurs['image']}</span>
									<input type="file" name="image" accept="image/*" class="form-control-file" />
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