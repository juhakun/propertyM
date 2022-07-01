<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>
	<div>
		<h2>Hausverwaltung und Nebenkostenabrechnung</h2>




		<div id="navi">
			<div id="button" >
				<c:url var="toMainPage" value="/">
				</c:url>
				<a href="${toMainPage}" style="color: white"><b>Startseite</b></a>
			</div>
			<div id="button">
				<c:url var="toHouses" value="/house/listHouses">
				</c:url>
				<a href="${toHouses}" style="color: white">Meine Häuser</a>
			</div>
			<div id="button">
				<c:url var="toCalc" value="/">
				</c:url>
				<a href="${toCalc}" style="color: white">Jahresabrechnung</a>
			</div>
			<br> <br>
		</div>

		<div id="container" style="min-height: calc(3 * 90px + 100px)">
			<h3>Was möchten Sie tun?</h3>
			<div id="objects" >
				<a href="house/showForm">Neues Haus anlegen</a>
			</div>
			<div id="objects">
				<a href="${toHouses}">Häuser bearbeiten</a>
			</div>
			<div id="objects">
				<a href="${toCalc}">Jahresabrechnung erstellen</a>
			</div>
		
		</div>
		

		<nav>
			<!--<c:url var="toMainPage" value="/">
			</c:url>
			<a href="${toMainPage}">Zur Startseite</a>-->
		</nav>
		</div>
		
		
		
</body>
</html>