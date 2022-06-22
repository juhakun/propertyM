<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Hausverwaltung</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Objektname:</th>
					<th>Adresse:</th>
					<th>Eigentümer:</th>
					<th></th>

				</tr>
				<c:forEach var="tempHouse" items="${ houses }">
					<!-- create an update link -->
					<c:url var="updateLink" value="/unit/showFormForUpdate">
						<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
					</c:url>
					<!-- create an delete link -->
					<c:url var="deleteLink" value="/unit/deleteObject">
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

					<tr>
						<td>${ tempHouse.getObjectName() }</td>
						<td>${ tempHouse.address.getStreet() }<br> ${ tempHouse.address.getHouseNo() }
							<br> ${ tempHouse.address.getPostalCode() } <br> ${ tempHouse.address.getCity() }
						</td>

						<td>${ tempHouse.owner.getFirstName() }${ tempHouse.owner.getLastName() }
							<br> ${ tempHouse.owner.address.getStreet() } <br> ${ tempHouse.owner.address.getHouseNo() }
							<br> ${ tempHouse.owner.address.getPostalCode() } <br>
							${ tempHouse.owner.address.getCity() }
						</td>


						<td>
							<!-- display update link --> <a href="${updateLink}">Details
								bearbeiten</a> <br> <!-- display add units link --> <a
							href="${showUnitsLink}">Wohneinheiten anzeigen</a> <br> <!-- display add counters link -->
							<a href="${addCountersLink}">Zähler anzeigen</a> <br> <!-- display delete link -->
							<a href="${deleteLink}">Löschen</a>
				</c:forEach>
			</table>
		</div>
	</div>

</body>

</html>