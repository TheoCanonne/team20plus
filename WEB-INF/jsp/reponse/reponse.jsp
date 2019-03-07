<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team 20+ | ${sujet.getTitre() }</title>

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
			<li><a href="Sujet?forum=${sujet.getForum().getFno() }">Sujet</a></li>
			<li>Reponse</li>
		</ol>

		<div class="row">
			<header class="page-header">
				<h1 class="page-title">Sujet : ${sujet.getTitre()}</h1>
			</header>

			<article class="col-md-12">


				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table table-bordered table-hover ">
								<thead>
									<tr>
										<th>Utilisateur</th>
										<th>Description</th>
									</tr>
								</thead>
								<tr>
									<th class="col-xs-4">
										<div class="row">
											<div class="col-md-6 col-md-offset-3">
												<img
													src="<c:choose><c:when test="${not empty sujet.getUtilisateur().getImage()}"></c:when><c:otherwise>data/image/utilisateur/vide.jpg</c:otherwise></c:choose>">
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<span class="thin" style="color: grey;">${sujet.getDate() }</span>
											</div>
											<div class="col-md-6 text-right">
												<span class="thin" style="color: grey;">${sujet.getUtilisateur().getLogin() }</span>
											</div>
										</div>

									</th>
									<th class="col-xs-8"><span style="color: black;">${sujet.getContenu() }</span></th>
								</tr>
							</table>

						</div>
					</div>
				</div>

				<hr>
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table table-bordered table-hover ">
								<thead>
									<tr>
										<th>Utilisateur</th>
										<th>Reponse</th>
									</tr>
								</thead>
								<c:forEach items="${reponses }" var="value">
									<tr
										onclick="window.location='Reponse?sujet=${value.getRno() }';">
										<th class="col-xs-4">
											<div class="row">
												<div class="col-md-6 col-md-offset-3">
													<img
														src="<c:choose><c:when test="${not empty value.getUtilisateur().getImage()}"></c:when><c:otherwise>data/image/utilisateur/vide.jpg</c:otherwise></c:choose>">
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
								</c:forEach>
							</table>

						</div>
					</div>
				</div>

				<c:choose>
					<c:when test="${empty utilisateur }">
						<a href="Login" class="btn btn-action">Se connecter pour
							répondre.</a>

					</c:when>
					<c:otherwise>
						<form action="CreerReponse" method="post">

							<div class="row form-group">
								<div class="col-md-12 text-center">
									<label>Répondre : </label> ${erreurs['textReponse'] }
								</div>
								<div class="col-md-12 form-group">
									<textarea name="textReponse" class="form-control" rows="5"></textarea>
								</div>
								<div class="col-md-12 text-right">
									<input type="hidden" name="sujet" value="${param.sujet}" /> <input
										type="submit" value="Répondre" class="btn btn-action" />
								</div>
							</div>



						</form>
					</c:otherwise>
				</c:choose>

			</article>
		</div>
	</div>

	<br />

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
	<script type="text/javascript"
		src="http://js.nicedit.com/nicEdit-latest.js"></script>
	<script type="text/javascript">
		bkLib.onDomLoaded(function() {
			nicEditors.allTextAreas()
		});
	</script>
</body>
</html>