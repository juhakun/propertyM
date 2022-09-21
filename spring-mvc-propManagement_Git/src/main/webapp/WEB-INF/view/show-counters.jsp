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
		} else {
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

		<div id="container" style="min-height: calc(${ noOfCounters } * 100px + 250px)">
			<h3>Alle Zähler der Immobilie "${ house.objectName.toUpperCase() }"</h3>
			<c:url var="addCountersLink" value="/counter/showForm">
				<c:param name="idHouse" value="${ house.idHouse }" />
			</c:url>
			<a href='${addCountersLink}'>Zähler hinzufügen</a> <br> <br>
			<div id="objects" style="width:850px; padding-right:20px">
			<table style="border-bottom: 1px solid #084B8A">

				<tr style="border-bottom: 1px solid #084B8A">
					<td><b>Nummer</td>
					<td><b>Zählerart</td>
					<td><b>Verbrauchsart</td>
					<td><b>Standort</td>
					<td></td>
				</tr>



				<c:forEach var="tempCounter" items="${ counters }">
					<!-- create an update link -->
					<c:url var="updateLink" value="/counter/showFormForUpdate">
						<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
					</c:url>
					<!-- create an delete link -->
					<c:url var="deleteLink" value="/counter/deleteObject">
						<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
					</c:url>
					<!-- create an add units link -->
					<!-- <div id="objects"> -->

					<tr style="border-bottom: 1px solid #084B8A">

						<td>${ tempCounter.counterNo }</td>
						<td>${ tempCounter.houseOrUnit } <br> ${ tempCounter.unitName }</td>
						<td>${ tempCounter.type }</td>
						<td>${ tempCounter.location }</td>
						<td>
							<!-- display update link --> <a href="${updateLink}">Details
								bearbeiten</a> <br> <!-- display delete link --> <a
							href="${countLink}">Zählerstände bearbeiten</a> <br> <!-- display delete link -->
							<a href="${deleteLink}">Löschen</a>
					</tr>

				</c:forEach>
			</table>
			</div>
		</div>
		<nav>

			<c:url var="showHouse" value="/house/showHouse/${ house.idHouse }">
			</c:url>
			<a href="${showHouse}">Zurück zum Haus</a>
		</nav>
	</div>



</body>

</html>