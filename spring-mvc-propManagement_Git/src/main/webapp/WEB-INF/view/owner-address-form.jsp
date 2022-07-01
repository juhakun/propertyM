<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<div id="button">
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

		<div id="container">
			<form:form action="updateOwnerAddress" modelAttribute="newHouse">
	
		Adresse des Eigentümers: 


		<br>
				<br>
	Strasse: <form:input path="owner.address.street" />
				<br>
				<br>
	Hausnummer: <form:input path="owner.address.houseNo" />

				<br>
				<br>
	Postleitzahl: <form:input path="owner.address.postalCode" />

				<br>
				<br>
	Stadt: <form:input path="owner.address.city" />

				<br>
				<br>

				<br>
				<br>
				<input type="submit" value="Adresse hinzufügen" />
			</form:form>
		</div>
		<nav>

			<a href="/house/showFormForUpdate">Zurück</a>
		</nav>
	</div>



</body>

</html>