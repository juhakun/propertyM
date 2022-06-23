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
				<a href="${toMainPage}" style="color: white">Startseite</a>
			</div>
			<div id="button" >
				<a href="${deleteLink}" style="color: white"><b>Meine Häuser</b></a>
			</div>
			
			<div id="button" >
				<a href="${deleteLink}" style="color: white">Jahresabrechnung</a>
			</div>
			<br> <br>
		</div>

		<div id="container">
		<h3>Alle Häuser</h3>
			<c:forEach var="tempHouse" items="${ houses }">
				<!-- create an update link -->
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
				<c:url var="addCountersLink" value="/counter/showForm">
					<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>


				<div id="objects">
					<table>
						<tr>

							<td><h3>${ tempHouse.getObjectName().toUpperCase() }</h3></td>
							<td></td>

						</tr>
						<tr style="font-style:italic">

							<td style="padding-bottom: 5px">Adresse</td>
							<td style="padding-bottom: 5px">Eigentümer</td>

						</tr>
						<tr>
							<td>${ tempHouse.address.getStreet() }${ tempHouse.address.getHouseNo() }
								<br> ${ tempHouse.address.getPostalCode() } ${ tempHouse.address.getCity() }
							</td>

							<td>${ tempHouse.owner.getFirstName() }${ tempHouse.owner.getLastName() }
								<br> ${ tempHouse.owner.address.getStreet() } ${ tempHouse.owner.address.getHouseNo() }
								<br> ${ tempHouse.owner.address.getPostalCode() } ${ tempHouse.owner.address.getCity() }
							</td>
						</tr>
					</table>
				</div>

				<div id="links">
					<!-- display update link -->
					<a href="${updateLink}" >Details
						bearbeiten</a> <br>
					<!-- display add units link -->
					<a href="${showUnitsLink}" >Wohneinheiten
						anzeigen</a> <br>
					<!-- display add counters link -->
					<a href="${addCountersLink}" >Zähler
						anzeigen</a> <br>
					<!-- display delete link -->
					<a href="${deleteLink}">Löschen</a>
				</div>



			</c:forEach>

		</div>
		<nav>
			<c:url var="toMainPage" value="/">
			</c:url>
			<a href="${toMainPage}">Zurück</a>
		</nav>
	</div>


















</body>

</html>