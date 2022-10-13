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
				<a href="${toHouses}" style="color: white">Meine H�user</a>
			</div>
			<div id="button">
				<c:url var="toCalc" value="/">
				</c:url>
				<a href="${toCalc}" style="color: white">Jahresabrechnung</a>
			</div>
			<br> <br>
		</div>


		<div id="container"
			style="min-height: calc(${ unitsUsed } * 260px + 250px)">
			<h3>Alle Wohneinheiten der Immobilie "${ house.objectName.toUpperCase() }"</h3>

			<c:url var="addUnitLink" value="/unit/showForm">
				<c:param name="idHouse" value="${ house.idHouse }" />
			</c:url>
			<c:set var="MyHtml"
				value="Es wurden alle verf�gbaren Wohneinheiten angelegt." />
			<c:if test="${ unitsUsed < noOfUnits }">
				<c:set var="MyHtml"
					value="<a href='${addUnitLink}'>Wohneinheit hinzuf�gen</a>" />
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
				<!-- create an add counters link -->
				<c:url var="showUnitCountersLink"
					value="/counter/showCountersOfUnit/${ tempUnit.getIdUnit() }">
				</c:url>
				<c:url var="showHouseCountersLink"
					value="/counter/showCounters/${ tempUnit.getIdUnit() }">
				</c:url>

				<div id="objects">
					<h3>
						${ tempUnit.unitName }  <br>
						(${ tempUnit.floor })  
					</h3>
					<table style="border-top: 1px solid; border-color: #084B8A;">

						<tr >
							<td style="font-style:italic;padding-bottom: 0px; padding-top: 20px; width: 22%">Gr��e:</td>
							<td style="padding-bottom: 0px; width: 28%">${ tempUnit.sizeM2 }m2
							</td>

							<td style="font-style:italic;padding-bottom: 0px; width: 19%">Name:</td>
							<td style="padding-bottom: 0px; width: 31%">${ tempUnit.renter.firstName }
								${ tempUnit.renter.lastName }</td>
						</tr>
						<tr>
							<td style="font-style:italic;padding-bottom: 0px">Zimmer:</td>
							<td style="padding-bottom: 0px">${ tempUnit.noOfRooms }</td>
							<td style="font-style:italic;padding-bottom: 0px">Personen:</td>
							<td style="padding-bottom: 0px">${ tempUnit.renter.noOfPeople }</td>
						</tr>
						<tr>
							<td style="font-style:italic;padding-bottom: 0px">Kaltmiete:</td>
							<td style="padding-bottom: 0px">${ tempUnit.renter.rent }
								EUR</td>
							<td style="font-style:italic;padding-bottom: 0px">Einzug am:</td>
							<td style="padding-bottom: 0px">${ tempUnit.renter.sqlDateMoveIn  }</td>
						</tr>
						<tr>
							<td style="font-style:italic;padding-bottom: 0px; width: 80px">Nebenkosten:</td>
							<td style="padding-bottom: 0px">${ tempUnit.renter.monthlyNkInAdvance  }
								EUR</td>
							<td style="font-style:italic;padding-bottom: 0px">Auszug am:</td>
							<td style="padding-bottom: 0px">${ tempUnit.renter.sqlDateMoveOut  }</td>
						</tr>
					</table>
				</div>

				<div id="links">
					<!-- display update link -->
					<a href="${updateLink}">Details bearbeiten</a>
					<!-- display unit counters link -->
					<br> <a href="${showUnitCountersLink}">Alle Z�hler
						anzeigen</a>
					<!-- display house counters link 
					<br> <a href="${showHouseCountersLink}">Alle Z�hler (Haus) anzeigen</a> -->
					<!-- display delete link -->
					<br> <a href="${deleteLink}">Wohneinheit l�schen</a>


				</div>
			</c:forEach>
		</div>
		<nav>

			<c:url var="showHouse" value="/house/showHouse/${ house.idHouse }">
			</c:url>
			<a href="${showHouse}">Zur�ck zum Haus</a>
			<c:url var="addCountersLink"
				value="/counter/showCounters/${ house.idHouse }">
			</c:url>
			<a href="${addCountersLink}">Weiter zu den Z�hlern</a>
		</nav>
	</div>




</body>

</html>