<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>


<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<script>
	function setType() {
		if (document.getElementById("selectTypeUnit").checked === true) {
			document.getElementById("chooseUnit").style.visibility = "visible";
		} else  {
			document.getElementById("chooseUnit").style.visibility = "hidden";
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
			
		<h3>
		Bitte machen Sie folgende Angaben zum Zähler.</h3>
		<br>
			<form:form action="processForm" modelAttribute="newCounter">
			Zählernummer: <form:input path="counterNo" />
				<form:errors path="counterNo" cssClass="error" />
				<br>
				<br>
			Der Zähler ist ein Hauszähler.&emsp;<form:radiobutton id="selectTypeHouse" path="houseOrUnit"
					value="house" onchange="setType()"/>
				<br>
				<br>
				Der Zähler ist ein Wohnungszähler.&emsp;<form:radiobutton id="selectTypeUnit" path="houseOrUnit"
					value="unit" onchange="setType()" />&emsp;        
				
				<form:select id="chooseUnit" path="unitName" style="visibility:hidden">
					<form:options items="${unitNames}" />
				</form:select>
			
				
				<br>
				<br>
			 
			Art des Zählers:	<form:select  path="type">
					<form:option value="Zählerart auswählen"></form:option>
					<form:option value="Heizkostenverteiler"></form:option>
					<form:option value="Kaltwasser"></form:option>
					<form:option value="Strom"></form:option>
					<form:option value="Warmwasser"></form:option>
					
					
				</form:select>
				<br><br>
	Standort: <form:select  path="location">
	<form:option value="Standort auswählen"></form:option>
					<form:option value="Bad"></form:option>
					<form:option value="Flur"></form:option>
					<form:option value="Gästezimmer"></form:option>
					<form:option value="Kammer"></form:option>
					<form:option value="Keller"></form:option>
					<form:option value="Kinderzimmer"></form:option>
					<form:option value="Küche"></form:option>
					<form:option value="Wohnzimmer"></form:option>
				</form:select>
				<br>
				<br> 
				Zählerstand alt: <form:input path="countOld" />
				<form:errors path="countOld" cssClass="error" />
				&emsp;abgelesen am&emsp;
				<form:input type="date" path="dateCountOldString" />
			
				<br>
				<br>
				Zählerstand neu: <form:input path="countNew" />
				<form:errors path="countNew" cssClass="error" />
				&emsp;abgelesen am&emsp; 
				<form:input type="date" path="dateCountNewString" />
				<br>
				<br>


				<input type="submit" value="Zähler anlegen" />
			</form:form>
		</div>
		<nav>

			<a href="/unit/showUnits/${ newUnit.house.getIdHouse() }">Zurück</a>
		</nav>
	</div>



</body>

</html>