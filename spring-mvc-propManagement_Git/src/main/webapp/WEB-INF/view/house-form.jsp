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
			<form:form action="processForm" modelAttribute="newHouse">
	Objektname: <form:input path="objectName" />
				<form:errors path="objectName" cssClass="error" />
				<br>
				<br>
	Strasse: <form:input path="address.street" />
				<form:errors path="address.street" cssClass="error" />
				<br>
				<br>
	Hausnummer: <form:input path="address.houseNo" />
				<form:errors path="address.houseNo" cssClass="error" />
				<br>
				<br>
	Postleitzahl: <form:input path="address.postalCode" />
				<form:errors path="address.postalCode" cssClass="error" />
				<br>
				<br>
	Stadt: <form:input path="address.city" />
				<form:errors path="address.city" cssClass="error" />
				<br>
				<br>
	Gesamtfläche: <form:input path="totalAreaM2" />
				<form:errors path="totalAreaM2" cssClass="error" />
				<br>
				<br>
	Anzahl Wohneinheiten: <form:input path="noOfUnits" />
				<form:errors path="noOfUnits" cssClass="error" />
				<br>
				<br>
		
		Eigentümer
	<br>
				<br>
		Anrede: <form:select path="owner.formOfAddress">
					<form:option value="" label="Auswählen" />
					<form:option value="Frau" label="Frau" />
					<form:option value="Herr" label="Herr" />
				</form:select>
				<form:errors path="owner.formOfAddress" cssClass="error" />
				<br>
				<br>

	Vorname: <form:input path="owner.firstName" />
				<form:errors path="owner.firstName" cssClass="error" />
				<br>
				<br>
	Nachname: <form:input path="owner.lastName" />
				<form:errors path="owner.lastName" cssClass="error" />
				<br>
				<br>
		Telefon: <form:input path="owner.telephone" />
				<form:errors path="owner.telephone" cssClass="error" />
				<br>
				<br>
		Mobil: <form:input path="owner.mobile" />
				<form:errors path="owner.mobile" cssClass="error" />
				<br>
				<br>
		E-Mail: <form:input path="owner.eMail" />
				<form:errors path="owner.eMail" cssClass="error" />
				<br>
				<br>
		Adresse: Neue Adresse eingeben <form:radiobutton
					path="owner.hasExtraAddress" value="true" />

				<br>
				<br>
				<input type="submit" value="Haus anlegen" />
			</form:form>
		</div>
		<nav>
			<c:url var="toMainPage" value="/">
			</c:url>
			<a href="${toMainPage}">Zurück</a>
		</nav>
		</div>



</body>

</html>