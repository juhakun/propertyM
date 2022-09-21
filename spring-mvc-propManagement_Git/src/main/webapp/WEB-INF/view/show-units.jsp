<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

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
		

		<div id="container" style="min-height: calc(${ unitsUsed } * 260px + 250px)">
			<h3>Alle Wohneinheiten der Immobilie "${ house.objectName.toUpperCase() }"</h3>

			<c:url var="addUnitLink" value="/unit/showForm">
				<c:param name="idHouse" value="${ house.idHouse }" />
			</c:url>
			<c:set var="MyHtml"
				value="Es wurden alle verfügbaren Wohneinheiten angelegt." />
			<c:if test="${ unitsUsed < noOfUnits }">
				<c:set var="MyHtml"
					value="<a href='${addUnitLink}'>Wohneinheit hinzufügen</a>" />
			</c:if>
			${ MyHtml } <br> <br>

			<c:forEach var="tempUnit" items="${ units }">
				<!-- create an update link -->
				<c:url var="updateLink" value="/unit/showFormForUpdate">
					<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
				</c:url>
				<!-- create an delete link -->
				<c:url var="deleteLink" value="/unit/deleteObject">
					<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
				</c:url>
				<!-- create an add units link -->
				<div id="objects">
					<table style="border-bottom: 0px">
						<tr>

							<td><h3>
									${ tempUnit.floor } <br> ${ tempUnit.unitName }
								</h3></td>
							<td></td>
						</tr>
						<tr style="font-style: italic">

							<td style="padding-bottom: 5px">Wohneinheit</td>
							<td style="padding-bottom: 5px">Mieter</td>

						</tr>
						<tr>

							<td>${ tempUnit.sizeM2 }m2 <br>${ tempUnit.noOfRooms }
								Zimmer <br>Miete: ${ tempUnit.renter.rent } EUR <br>NK:
								${ tempUnit.renter.monthlyNkInAdvance  } EUR
							</td>

							<td>${ tempUnit.renter.firstName }${ tempUnit.renter.lastName }
								<br>${ tempUnit.renter.noOfPeople } Personen <br>Einzug
								am ${ tempUnit.renter.sqlDateMoveIn  } <br>Auszug am ${ tempUnit.renter.sqlDateMoveOut  }

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

			<c:url var="showHouse" value="/house/showHouse/${ house.idHouse }">
			</c:url>
			<a href="${showHouse}">Zurück zum Haus</a>
			<c:url var="addCountersLink"
					value="/counter/showCounters/${ house.idHouse }">
				</c:url>
			<a href="${addCountersLink}">Weiter zu den Zählern</a>
		</nav>
		</div>


	

</body>

</html>