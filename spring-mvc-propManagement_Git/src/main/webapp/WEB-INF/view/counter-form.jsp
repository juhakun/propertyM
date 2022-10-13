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
			document.getElementById("chooseUnit").disabled = false;
		} else  {
			document.getElementById("chooseUnit").disabled = true;
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
					value="Hauszähler" onchange="setType()"/>
					<form:errors path="houseOrUnit" cssClass="error" />
				<br>
				<br>
				Der Zähler ist ein Wohnungszähler.&emsp;<form:radiobutton id="selectTypeUnit" path="houseOrUnit"
					value="Wohnungszähler" onchange="setType()" unchecked="true" />
					<form:errors path="houseOrUnit" cssClass="error" />
					&emsp;        
				
				<form:select id="chooseUnit" path="unitName" >
					<form:options items="${unitNames}" />
					
				</form:select>
				&emsp;
				<form:errors path="unitName" cssClass="error" />
			
				
				<br>
				<br>
			 
			Art des Zählers:	<form:select  path="type">
					<form:option value="Zählerart auswählen"></form:option>
					<form:option value="Heizkostenverteiler"></form:option>
					<form:option value="Kaltwasser"></form:option>
					<form:option value="Strom"></form:option>
					<form:option value="Warmwasser"></form:option>	
				</form:select>
				&emsp;
				<form:errors path="type" cssClass="error" />
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
				&emsp;
				<form:errors path="location" cssClass="error" />
				<br>
				<br> 
				
				Zählerstand: <form:input path="count" />
				<form:errors path="count" cssClass="error" />
				&emsp;abgelesen am&emsp; 
				<form:input type="date" path="dateCountString" />
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