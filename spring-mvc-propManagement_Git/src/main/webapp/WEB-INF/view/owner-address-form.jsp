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

</body>

</html>