<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>


<head>
<title>Angaben Wohneinheit</title>
<style>
.error {
	color: red
}
</style>

</head>

<body>
	<form:form action="processForm" modelAttribute="newUnit">
	Name Wohneinheit: <form:input path="unitName" />
		<form:errors path="unitName" cssClass="error" />
		<br>
		<br>
	Wöhnungsgröße in m2: <form:input path="sizeM2" />
		<form:errors path="sizeM2" cssClass="error" />
		<br>
		<br>
	Anzahl Zimmer: <form:input path="noOfRooms" />
		<form:errors path="noOfRooms" cssClass="error" />
		<br>
		<br>
	Stockwerk: <form:select path="floor">
			<form:options items="${newUnit.floors}" />
		</form:select>
		<br>
		<br>
		
		Mieter
		<br>
		<br>
	Der Eigentümer ist Mieter <form:radiobutton
			path="house.owner.isRenter" value="true"   />
			<br>
		<br>
		Anrede: <form:select path="renter.formOfAddress">
			<form:option value="" label="Auswählen" />
			<form:option value="Frau" label="Frau" />
			<form:option value="Herr" label="Herr" />
		</form:select>
		<form:errors path="renter.formOfAddress" cssClass="error" />
		<br>
		<br>

	Vorname: <form:input path="renter.firstName" />
		<form:errors path="renter.firstName" cssClass="error" />
		<br>
		<br>
	Nachname: <form:input path="renter.lastName" />
		<form:errors path="renter.lastName" cssClass="error" />
		<br>
		<br>
		Telefon: <form:input path="renter.telephone" />
		<form:errors path="renter.telephone" cssClass="error" />
		<br>
		<br>
		Mobil: <form:input path="renter.mobile" />
		<form:errors path="renter.mobile" cssClass="error" />
		<br>
		<br>
		E-Mail: <form:input  path="renter.eMail" />
		<form:errors path="renter.eMail" cssClass="error" />
		<br>
		<br>
	Kaltmiete: <form:input path="renter.rent" />
		<form:errors path="renter.rent" cssClass="error" />
		<br>
		<br>
		monatliche Nebenkostenvorauszahlung: <form:input
			path="renter.monthlyNkInAdvance" />
		<form:errors path="renter.monthlyNkInAdvance" cssClass="error" />
		<br>
		<br>
		Anzahl der Personen: <form:input path="renter.noOfPeople" />
		<form:errors path="renter.noOfPeople" cssClass="error" />
		<br>
		<br>

		Einzugsdatum: 
		<form:input type="date" path="renter.moveInString" />
		<br>
		<br>

		Auszugsdatum: <form:input type="date" path="renter.moveOutString"
			 />
		<br>
		<br>

		<input type="submit" value="Wohneinheit anlegen" />
	</form:form>



</body>

</html>