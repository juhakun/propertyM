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
		<c:url var="toMainPage" value="/">
			</c:url>
			<div id="button">
				<a href="${toMainPage}" style="color: white">Startseite</a>
			</div>
			<div id="button">
				<c:url var="toHouses" value="/house/listHouses">
				</c:url>
				<a href="${toHouses}" style="color: white">Meine Häuser</a>
			</div>
			<div id="button">
				<a href="${deleteLink}" style="color: white">Jahresabrechnung</a>
			</div>
			<br> <br>
		</div>

		<div id="container">
			<h3>Alle Wohneinheiten der Immobilie "${ house.objectName.toUpperCase() }"</h3>
			
			<c:url var="addUnitLink" value="/unit/showForm"> />
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
					<table>
						<tr>

							<td><h3>${ tempUnit.unitName }</h3></td>
							<td></td>
						</tr>
						<tr style="font-style:italic">

							<td style="padding-bottom: 5px">Wohneinheit</td>
							<td style="padding-bottom: 5px">Mieter</td>

						</tr>
						<tr>

							<td>${ tempUnit.sizeM2 } m2 <br>${ tempUnit.noOfRooms }
								Zimmer <br>Miete: ${ tempUnit.renter.rent } EUR <br>NK: ${ tempUnit.renter.monthlyNkInAdvance  } EUR</td>

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
			

			<c:url var="listHouses" value="/house/listHouses">
			</c:url>
			<a href="${listHouses}">Zurück</a>
		</nav>


	</div>

</body>

</html>