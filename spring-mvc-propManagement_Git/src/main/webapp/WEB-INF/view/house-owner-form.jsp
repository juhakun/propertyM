<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<script>
	function setNewOwner() {
		if (document.getElementById("enterNewOwner").checked === true) {
			document.getElementById("oldOwner").style.visibility = "hidden";
			document.getElementById("newOwner").style.visibility = "visible";
			document.getElementById("oldOwner").disabled = true;
			document.getElementById("newOwner").disabled = false;
			    let elements = document.getElementById("newOwner").getElementsByTagName("input");
			    [].forEach.call(elements, element => {
			        element.value = "";
			    });
			    document.getElementById("auswaehlen").selected = true;
			  
		} else {
			document.getElementById("oldOwner").style.visibility = "visible";
			document.getElementById("newOwner").style.visibility = "hidden";
			document.getElementById("oldOwner").disabled = false;
			document.getElementById("newOwner").disabled = true;
			
		}
	}
</script>
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
			<form:form action="saveUpdate" modelAttribute="savedHouse">
				<form:hidden path="idHouse" />

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
				Der Eigentümer hat gewechselt <form:checkbox id="enterNewOwner"
					path="ownerHasChanged" value="true"
					onchange="setNewOwner()" />
					<br><br>
		<fieldset id="oldOwner" style="border: 0; position: absolute">
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
		Adresse: 
		
		<br>
				<br>
	Strasse: <form:input path="owner.address.street" />
				<form:errors path="owner.address.street" cssClass="error" />
				<br>
				<br>
	Hausnummer: <form:input path="owner.address.houseNo" />
				<form:errors path="owner.address.houseNo" cssClass="error" />
				<br>
				<br>
	Postal code: <form:input path="owner.address.postalCode" />
				<form:errors path="owner.address.postalCode" cssClass="error" />
				<br>
				<br>
	Stadt: <form:input path="owner.address.city" />
				<form:errors path="owner.address.city" cssClass="error" />

				<br>
				<br>
				</fieldset>
				
	<fieldset id="newOwner" style="border: 0; visibility:hidden; position: absolute " disabled>
		Anrede: <form:select path="owner.formOfAddress">
		<form:option id="auswaehlen" value="" label="Auswählen"/>
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
		Adresse: 
		<br>
				<br>
	Strasse: <form:input path="owner.address.street" />
				<form:errors path="owner.address.street" cssClass="error" />
				<br>
				<br>
	Hausnummer: <form:input path="owner.address.houseNo" />
				<form:errors path="owner.address.houseNo" cssClass="error" />
				<br>
				<br>
	Postal code: <form:input path="owner.address.postalCode" />
				<form:errors path="owner.address.postalCode" cssClass="error" />
				<br>
				<br>
	Stadt: <form:input path="owner.address.city" />
				<form:errors path="owner.address.city" cssClass="error" />

				<br>
				<br>
				</fieldset>
				<input type="submit" style=" position: relative; top:460px" value="Änderungen speichern" />
			</form:form>
		</div>
		<nav>

			<c:url var="listHouses" value="/house/listHouses">
			</c:url>
			<a href="${listHouses}">Zurück</a>
		</nav>
</div>


</body>

</html>