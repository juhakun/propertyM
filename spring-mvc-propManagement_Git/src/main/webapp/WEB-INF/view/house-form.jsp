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
	Anrede: <form:select path="owner.p.formOfAddress">
			<form:option value="Frau" label="Frau" />
			<form:option value="Herr" label="Herr" />
		</form:select>
		<form:errors path="owner.formOfAddress" cssClass="error" />
		<br>
		<br>
	Vorname: <form:input path="owner.p.firstName" />
		<form:errors path="noOfUnits" cssClass="error" />
		<br>
		<br>
	Nachname: <form:input path="owner.p.lastName" />
		<form:errors path="noOfUnits" cssClass="error" />
		<br>
		<br>
	Adresse: 
	<br><br>
	Adresse des Hauses verwenden <form:radiobutton path="owner.p.address"
			value="house.address" />
		<form:errors path="owner.p.address" cssClass="error" />
		<br>
		<br>
		<input type="submit" value="Haus anlegen" />
	</form:form>

</body>

</html>