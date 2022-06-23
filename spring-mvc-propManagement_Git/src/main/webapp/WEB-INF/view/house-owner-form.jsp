<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>Objektangaben</title>
<style>
.error {
	color: red
}
</style>
</head>

<body>
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
	
		Anrede: <form:select path="owner.formOfAddress">
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
		<input type="submit" value="Änderungen speichern" />
	</form:form>
	<c:url var="toMainPage" value="/">
	</c:url>
	
	<a href="${toMainPage}">Zur Startseite</a>

	<c:url var="listHouses" value="/house/listHouses">
	</c:url>
	<a href="${listHouses}">Zurück</a>


</body>

</html>