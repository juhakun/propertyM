<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
	<form:form action="processForm" modelAttribute="house">
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
	Postal code: <form:input path="address.postalCode" />
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
	Anrede: 
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
	Adresse: 
	<br><br>
	
		<br>
		<br>
		<input type="submit" value="Haus anlegen" />
	</form:form>

</body>

</html>