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

		<div id="container">
			<h3>Alle Zähler der Immobilie "${ house.objectName.toUpperCase() }"</h3>
			<c:url var="addCountersLink" value="/counter/showForm">
				<c:param name="idHouse" value="${ house.idHouse }" />
			</c:url>
			<a href='${addCountersLink}'>Zähler hinzufügen</a> <br>
			<br>
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
				<div id="objects">
					<table>
						<tr>

							<td><h3>
									${ tempCounter.counterNo } <br> ${ tempCounter.houseOrUnit }
								</h3></td>
							<td></td>
						</tr>
						<tr style="font-style: italic">

							<td style="padding-bottom: 5px">Zähler</td>
							<td style="padding-bottom: 5px">Letzte Ablesung</td>

						</tr>
						<tr>

							<td>${ tempCounter.type }<br>${ tempCounter.location }
								<br>${ tempCounter.countOld} <br> ${ tempCounter.dateCountOldString}
							</td>

							<td>${ tempCounter.countNew }<br>${ tempCounter.dateCountNewString }
								<br>

							</td>
						</tr>
					</table>
				</div>

				<div id="links">
					<!-- display update link -->
					<a href="${updateLink}">Details bearbeiten</a>

					<!-- display delete link -->
					<br> <a href="${deleteLink}">Löschen</a>

				</div>
			</c:forEach>




		</div>
		<nav>

			<a href="/unit/showUnits/${ newUnit.house.getIdHouse() }">Zurück</a>
		</nav>
	</div>



</body>

</html>