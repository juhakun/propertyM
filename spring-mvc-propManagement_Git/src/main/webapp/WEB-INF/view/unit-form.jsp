<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>


<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<script>
	function setOwnerAsRenter() {
		
		if (document.getElementById("selectOwnerIsRenter").checked === true) {
			document.getElementById("ownerIsRenter").disabled = true;
		} else if(document.getElementById("selectOwnerIsRenter").checked === false && ${ownerIsRenter = "true"}){
			let elements = document.getElementById("ownerIsRenter").getElementsByTagName("input");
		    [].forEach.call(elements, element => {
		        element.value = "";
		    });
		    document.getElementById("ownerIsRenter").disabled = false;
		} else  {
			document.getElementById("ownerIsRenter").disabled = false;
		}
	}
	
	function checkIfOwnerIsRenter() {
		if (${ownerIsRenter == "true"}) {
			document.getElementById("selectOwnerIsRenter").checked = true;
		} else (${ownerIsRenter == "false"}) {
			document.getElementById("selectOwnerIsRenter").checked = false;
		}
		
	}
</script>
</head>

<body onload="checkIfOwnerIsRenter()">
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
			<form:form action="processForm" modelAttribute="newUnit">
			Stockwerk: <form:select path="floor">
					<form:options items="${newUnit.floors}" />
				</form:select>
				<br>
				<br>
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
	
		
		Mieter
		<br>
				<br>
	Der Eigentümer ist der Mieter <form:checkbox id="selectOwnerIsRenter"
					path="ownerIsRenter" value="true"
					onchange="setOwnerAsRenter()" />
				<br>
				<br>
				<fieldset id="ownerIsRenter" style="border: 0">
					Anrede:
					<form:select path="renter.formOfAddress">
						<form:option value="" label="Auswählen" />
						<form:option value="Frau" label="Frau" />
						<form:option value="Herr" label="Herr" />
					</form:select>
					<form:errors path="renter.formOfAddress" cssClass="error" />
					<br> <br> Vorname:
					<form:input path="renter.firstName" />
					<form:errors path="renter.firstName" cssClass="error" />
					<br> <br> Nachname:
					<form:input path="renter.lastName" />
					<form:errors path="renter.lastName" cssClass="error" />
					<br> <br> Telefon:
					<form:input path="renter.telephone" />
					<form:errors path="renter.telephone" cssClass="error" />
					<br> <br> Mobil:
					<form:input path="renter.mobile" />
					<form:errors path="renter.mobile" cssClass="error" />
					<br> <br> E-Mail:
					<form:input path="renter.eMail" />
					<form:errors path="renter.eMail" cssClass="error" />
					<br> <br>
				</fieldset>
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

		Auszugsdatum: <form:input type="date" path="renter.moveOutString" />
				<br>
				<br>

				<input type="submit" value="Wohneinheit anlegen" />
			</form:form>
		</div>
		<nav>

			<a href="/unit/showUnits/${ newUnit.house.getIdHouse() }">Zurück</a>
		</nav>
	</div>



</body>

</html>