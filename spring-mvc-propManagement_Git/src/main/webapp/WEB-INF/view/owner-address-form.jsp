<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>Adresse Eigentümer</title>
<style>
.error {
	color: red
}
</style>
</head>

<body>
	<form:form action="updateOwnerAddress" modelAttribute="house">
	
		Adresse des Eigentümers: 


		<br>
		<br>
	Strasse: <form:input path="owner.ownerAddress.street" />
		<form:errors path="owner.ownerAddress.street" cssClass="error" />
		<br>
		<br>
	Hausnummer: <form:input path="owner.ownerAddress.houseNo" />
		<form:errors path="owner.ownerAddress.houseNo" cssClass="error" />
		<br>
		<br>
	Postal code: <form:input path="owner.ownerAddress.postalCode" />
		<form:errors path="owner.ownerAddress.postalCode" cssClass="error" />
		<br>
		<br>
	Stadt: <form:input path="owner.ownerAddress.city" />
		<form:errors path="owner.ownerAddress.city" cssClass="error" />
		<br>
		<br>

		<br>
		<br>
		<input type="submit" value="Haus anlegen" />
	</form:form>

</body>

</html>