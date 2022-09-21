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

		<div id="container"
			style="min-height: calc(${ noOfHouses } * 240px + 200px)">
			<h3><c:set var="MyHtml"
				value="Alle Häuser" />
			<c:if test="${ noOfHouses == 1 }">
				<c:set var="MyHtml"
					value="" />
			</c:if>
			${ MyHtml } <br> <br></h3>
			<c:forEach var="tempHouse" items="${ houses }">
				<!-- create an update link 
				<c:url var="updateLink" value="/house/showFormForUpdate">
					<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an delete link -->
				<c:url var="deleteLink" value="/house/deleteObject">
					<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an add units link -->
				<c:url var="showUnitsLink"
					value="/unit/showUnits/${ tempHouse.getIdHouse() }">
				</c:url>
				<!-- create an add counters link -->
				<c:url var="addCountersLink"
					value="/counter/showCounters/${ tempHouse.getIdHouse() }">
				</c:url>


				<div id="objects">
					<table style="border-bottom: 0px">
						<tr>

							<td><h3>${ tempHouse.getObjectName().toUpperCase() }</h3></td>
							<td></td>

						</tr>
						<tr style="font-style: italic">

							<td style="padding-bottom: 5px">Adresse</td>
							<td style="padding-bottom: 5px">Eigentümer</td>

						</tr>
						<tr>
							<td>${ tempHouse.address.getStreet() } ${ tempHouse.address.getHouseNo() }
								<br> ${ tempHouse.address.getPostalCode() } ${ tempHouse.address.getCity() }
							</td>

							<td>${ tempHouse.owner.getFirstName() } ${ tempHouse.owner.getLastName() }
								<br> ${ tempHouse.owner.address.getStreet() } ${ tempHouse.owner.address.getHouseNo() }
								<br> ${ tempHouse.owner.address.getPostalCode() } ${ tempHouse.owner.address.getCity() }
							</td>
						</tr>
					</table>
				</div>

				<div id="links">
					<!-- display update link -->
					<a href="${updateLink}">Details bearbeiten</a> <br>
					<!-- display add units link -->
					<a href="${showUnitsLink}">Wohneinheiten anzeigen</a> <br>
					<!-- display add counters link -->
					<a href="${addCountersLink}">Zähler anzeigen</a> <br>
					<!-- display delete link -->
					<a href="${deleteLink}">Löschen</a>
				</div>



			</c:forEach>

		</div>
		<nav>
		<c:url var="toMainPage" value="/">
			</c:url>
			<c:url var="listHouses" value="/house/listHouses">
			</c:url>
			
			
		<c:set var="MyHtml"
				value="<a href='${toMainPage}'>Zurück zur Startseite</a>" />
			<c:if test="${ noOfHouses == 1 }">
				<c:set var="MyHtml"
					value="<a href='${listHouses}'>Zurück zu allen Häusern</a>" />
			</c:if>
			${ MyHtml } <br> <br>
			
		</nav>
</body>

</html>